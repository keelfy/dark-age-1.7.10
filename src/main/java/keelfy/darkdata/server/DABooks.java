/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.compress.utils.CharsetNames;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import keelfy.darkdata.constants.EnumDAFileType;
import keelfy.darkdata.handlers.DACommonFiles;
import keelfy.darkdata.items.StoryBook;
import keelfy.darkdata.items.strorybook.AbstractStoryBook;
import keelfy.darkdata.items.strorybook.SBPage;
import keelfyutils.KUtils;
import keelfyutils.str.KString;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public enum DABooks {
	Instance;

	private DAServerFiles sFileHandler;
	private File signaturePath;

	public File currentPath;
	private final List<File> lastListing = new ArrayList();
	private String lastCheckedPath = KString.EMPTY;

	public String dabFile = ".dab";

	public final void init() {
		if (KUtils.PROTECT_SERVER) {
			this.sFileHandler = DAServerFiles.Instance;
			this.currentPath = DACommonFiles.Instance.getDataFolderPath();
		}
	}

	public List<File> getValidRoots() {
		if (KUtils.PROTECT_SERVER) {
			final List<File> outList = new ArrayList();
			for (final File root : File.listRoots()) {
				if (root.listFiles() != null) {
					outList.add(root);
				}
			}
			return outList;
		}
		return null;
	}

	public void navigateUp() {
		if (KUtils.PROTECT_SERVER) {
			for (final File root : File.listRoots()) {
				if (this.currentPath.equals(root))
					return;
			}
			this.currentPath = this.currentPath.getParentFile();
		}
	}

	public List<File> listFiles() {
		if (KUtils.PROTECT_SERVER) {
			final File path = sFileHandler.getDABFolderPath();

			if (!path.getAbsolutePath().equals(this.lastCheckedPath)) {
				this.lastCheckedPath = path.getAbsolutePath();
				this.lastListing.clear();
				final File[] newList = path.listFiles();
				final List<File> files = new ArrayList();
				for (final File f : newList) {
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
		if (KUtils.PROTECT_SERVER)
			return this.signaturePath;
		return null;
	}

	public static String cleanGHBString(String strIn) {
		if (KUtils.PROTECT_SERVER) {
			strIn = strIn.replaceAll("(?s)//.*?((\\n)|(\\r\\n)|(\\Z))", "\n");
			strIn = strIn.replaceAll("(?s)((/\\*).*?((\\*/)|(\\Z)))|(((/\\*)|(\\A)).*?(\\*/))", KString.EMPTY);
			strIn = strIn.replaceAll("[\\t\\r\\n ]+(##|>>>>)", "$1");
			strIn = strIn.replaceAll("[\\r\\n]", KString.EMPTY);
			return strIn;
		}
		return StringUtils.EMPTY;
	}

	@SuppressWarnings("resource")
	public AbstractStoryBook getBookByID(final int id) {
		if (KUtils.PROTECT_SERVER) {
			AbstractStoryBook slBook = null;
			try {
				final List<File> books = this.listFiles();
				Scanner sc;
				for (final File book : books) {
					final File bookFile = new File(sFileHandler.getDABFolderPath(), book.getName());
					sc = new Scanner(bookFile, CharsetNames.UTF_8);
					while (sc.hasNextLine()) {
						final String str = sc.nextLine();
						if (str.contains("id:") && str.substring(3).equals(String.valueOf(id)))
							return slBook = this.loadBookFromDABFile(FilenameUtils.removeExtension(book.getName()));
					}
				}
			} catch (final FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public final boolean updateBook(final ItemStack stack) {
		if (KUtils.PROTECT_SERVER) {
			if (stack != null && stack.getItem() instanceof StoryBook) {
				final AbstractStoryBook slBook = getContent(stack);

				if (slBook != null) {
					stack.getTagCompound().setTag("pages", slBook.writePagesToNBT());
					stack.getTagCompound().setString("title", slBook.getTitle());
					stack.getTagCompound().setString("author", slBook.getAuthor());
					return true;
				}
			}
		}
		return false;
	}

	public AbstractStoryBook loadBookFromDABFile(final String bookName) {
		if (KUtils.PROTECT_SERVER) {
			final AbstractStoryBook loadedBook = new AbstractStoryBook();
			loadedBook.clear();

			final List<String> rawFile = sFileHandler.readFile(EnumDAFileType.DAB, bookName);
			if (rawFile == null || rawFile.isEmpty())
				return null;

			String concatFile = KString.EMPTY;
			for (final String line : rawFile) {
				if (line.toLowerCase().startsWith("title:")) {
					if (line.length() >= 7) {
						loadedBook.setTitle(line.substring(6));
						if (line.contains("/*")) {
							concatFile += line.substring(line.indexOf("/*")) + "\n";
						}
					}
				} else if (line.toLowerCase().startsWith("author:")) {
					if (line.length() >= 8) {
						loadedBook.setAuthor(line.substring(7));
						if (line.contains("/*")) {
							concatFile += line.substring(line.indexOf("/*")) + "\n";
						}
					}
				} else if (line.toLowerCase().startsWith("id:")) {
					if (line.length() >= 4) {
						loadedBook.setId(Integer.parseInt(line.substring(3)));
					}
				} else {
					concatFile += line + "\n";
				}
			}

			concatFile = cleanGHBString(concatFile);
			concatFile = concatFile.replaceAll("##", "\n");

			final String[] pageBroken = concatFile.split(">>>>");
			for (final String largePage : pageBroken) {
				if (loadedBook.totalPages() > 0) {
					SBPage currPage = loadedBook.getPages().get(loadedBook.totalPages() - 1);
					if (!currPage.asString().isEmpty()) {
						currPage = SBPage.pad(currPage);
					}
				}
				loadedBook.addText(loadedBook.totalPages(), 0, 0, largePage);
			}
			return loadedBook;
		}
		return null;
	}

	public AbstractStoryBook getContent(final ItemStack stack) {
		if (KUtils.PROTECT_SERVER) {
			if (stack != null && stack.getItem() instanceof StoryBook && stack.hasTagCompound()
					&& stack.getTagCompound().hasKey("id")) {
				final int id = stack.getTagCompound().getInteger("id");
				return getBookByID(id);
			}
		}
		return null;
	}
}
