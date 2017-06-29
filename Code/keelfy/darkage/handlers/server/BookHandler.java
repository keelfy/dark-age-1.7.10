/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import keelfy.darkage.DarkAge;
import keelfy.darkage.handlers.FileHandler;
import keelfy.darkage.handlers.FileHandler.EnumDAFileType;
import keelfy.darkage.items.storybook.AbstractStoryBook;
import keelfy.darkage.items.storybook.SBPage;
import keelfytools.KeelfyUtils;
import net.minecraft.item.ItemStack;

public class BookHandler {

	private FileHandler fileHandler;
	private File signaturePath;

	public File currentPath;
	private List<File> lastListing = new ArrayList();
	private String lastCheckedPath = "";

	public String dabFile = ".dab";

	public BookHandler() {
		if (KeelfyUtils.isServerSide()) {
			this.fileHandler = DarkAge.instance.fileHandler;
			this.currentPath = fileHandler.getDataFolderPath();
		}
	}

	public List<File> getValidRoots() {
		if (KeelfyUtils.isServerSide()) {
			List<File> outList = new ArrayList();
			for (File root : File.listRoots()) {
				if (root.listFiles() != null) {
					outList.add(root);
				}
			}
			return outList;
		}
		return null;
	}

	public void navigateUp() {
		if (KeelfyUtils.isServerSide()) {
			for (File root : File.listRoots()) {
				if (this.currentPath.equals(root)) {
					return;
				}
			}
			this.currentPath = this.currentPath.getParentFile();
		}
	}

	public List<File> listFiles(File path) {
		if (KeelfyUtils.isServerSide()) {
			if (!path.getAbsolutePath().equals(this.lastCheckedPath)) {
				this.lastCheckedPath = path.getAbsolutePath();
				this.lastListing.clear();
				File[] newList = path.listFiles();
				List<File> files = new ArrayList();
				for (File f : newList) {
					if (f.isDirectory()) {
						this.lastListing.add(f);
					} else {
						files.add(f);
					}
				}
				this.lastListing.addAll(files);
			}
			return this.lastListing;
		}
		return null;
	}

	public File getSignaturePath() {
		if (KeelfyUtils.isServerSide()) {
			return this.signaturePath;
		}
		return null;
	}

	public static String cleanGHBString(String strIn) {
		if (KeelfyUtils.isServerSide()) {
			strIn = strIn.replaceAll("(?s)//.*?((\\n)|(\\r\\n)|(\\Z))", "\n");
			strIn = strIn.replaceAll("(?s)((/\\*).*?((\\*/)|(\\Z)))|(((/\\*)|(\\A)).*?(\\*/))", "");
			strIn = strIn.replaceAll("[\\t\\r\\n ]+(##|>>>>)", "$1");
			strIn = strIn.replaceAll("[\\r\\n]", "");
			return strIn;
		}
		return StringUtils.EMPTY;
	}

	public AbstractStoryBook getBookByID(int id) {
		if (KeelfyUtils.isServerSide()) {
			AbstractStoryBook slBook = null;
			try {
				List<File> books = this.listFiles(fileHandler.getDABFolderPath());
				Scanner sc;
				for (File book : books) {
					File bookFile = new File(fileHandler.getDABFolderPath(), book.getName());
					sc = new Scanner(bookFile);
					while (sc.hasNextLine()) {
						String str = sc.nextLine();
						if (str.contains("id:") && str.substring(3).equals(String.valueOf(id))) {
							slBook = this.loadBookFromDABFile(FilenameUtils.removeExtension(book.getName()));
						}
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return slBook;
		}
		return null;
	}

	public AbstractStoryBook loadBookFromDABFile(String bookName) {
		if (KeelfyUtils.isServerSide()) {
			AbstractStoryBook loadedBook = new AbstractStoryBook();
			loadedBook.clear();

			List<String> rawFile = fileHandler.readFile(EnumDAFileType.BOOK, bookName);
			if (rawFile == null || rawFile.isEmpty()) {
				return null;
			}

			String concatFile = "";
			for (String line : rawFile) {
				if (line.toLowerCase().startsWith("title:") && loadedBook.title.isEmpty()) {
					if (line.length() >= 7) {
						loadedBook.title = line.substring(6);
						if (line.contains("/*")) {
							concatFile += line.substring(line.indexOf("/*")) + "\n";
						}
					}
				} else if (line.toLowerCase().startsWith("author:") && loadedBook.author.isEmpty()) {
					if (line.length() >= 8) {
						loadedBook.author = line.substring(7);
						if (line.contains("/*")) {
							concatFile += line.substring(line.indexOf("/*")) + "\n";
						}
					}
				} else if (line.toLowerCase().startsWith("id:") && loadedBook.id == 0) {
					if (line.length() >= 4) {
						loadedBook.id = Integer.parseInt(line.substring(3));
					}
				} else {
					concatFile += line + "\n";
				}
			}

			concatFile = cleanGHBString(concatFile);
			concatFile = concatFile.replaceAll("##", "\n");

			String[] pageBroken = concatFile.split(">>>>");
			for (String largePage : pageBroken) {
				if (loadedBook.totalPages() > 0) {
					SBPage currPage = loadedBook.pages.get(loadedBook.totalPages() - 1);
					if (!currPage.asString().isEmpty()) {
						currPage = SBPage.pad(currPage);
					}
				}
				loadedBook.addText(loadedBook.totalPages(), 0, 0, largePage, true);
			}
			return loadedBook;
		}
		return null;
	}

	public AbstractStoryBook getContent(ItemStack stack) {
		if (KeelfyUtils.isServerSide()) {
			if (stack.hasTagCompound() && stack.getTagCompound().hasKey("id")) {
				return DarkAge.instance.bookHandler.getBookByID(stack.getTagCompound().getInteger("id"));
			}
		}
		return null;
	}
}
