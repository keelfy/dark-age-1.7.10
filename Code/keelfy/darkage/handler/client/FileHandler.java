package keelfy.darkage.handler.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import keelfy.api.Brush;
import keelfy.api.KUtil;
import keelfy.darkage.item.smartlib.SLBook;
import keelfy.darkage.item.smartlib.SLPage;
import keelfy.darkage.util.DAUtil;

public class FileHandler {
	
	private File daiFolderPath;
	private File dabFolderPath;
	private File signaturePath;
	
	public File currentPath;
	private List<File> lastListing = new ArrayList();
	private String lastCheckedPath = "";
	
	public String dabFile = ".dab";
	public String daiFile = ".dai";
	
	private File configDir;
	
	public FileHandler(File configDir) {
		this.configDir = configDir;
		
		this.currentPath = getDataFolderPath();
		
		this.daiFolderPath = new File(getDataFolderPath(), "Items");
		this.dabFolderPath = new File(getDataFolderPath(), "Books");
	}
	
	public File getConfigurationDirectory() {
		return configDir;
	}
	
	public File getBookSavePath() {
		return dabFolderPath;
	}
	
	public File getDataFolderPath() {
		String path = configDir.getParent() + File.separator + "mods";
		if(!DAUtil.SERVER) path += File.separator + "1.7.10";
		if (path.endsWith("."))
			path = path.substring(0, path.length() - 2);
		return new File(path, "DarkAge");
	}
	
	public File getItemDAIFile(String name) {
		return new File(daiFolderPath, name + daiFile);
	}
	
	public List<File> getValidRoots(){
		List<File> outList = new ArrayList();
		for (File root : File.listRoots()){
			if (root.listFiles() != null){
				outList.add(root);
			}
		}
		return outList;
	}
	
	public void navigateUp(){
		for (File root : File.listRoots()){
			if (this.currentPath.equals(root)){
				return;
			}
		}
		this.currentPath = this.currentPath.getParentFile();
	}
	
	public List<File> listFiles(File path){
		if (!path.getAbsolutePath().equals(this.lastCheckedPath)){
			this.lastCheckedPath = path.getAbsolutePath();
			this.lastListing.clear();
			File[] newList = path.listFiles();
			List<File> files = new ArrayList();
			for (File f : newList){
				if (f.isDirectory()){
					this.lastListing.add(f);
				}
				else{
					files.add(f);
				}
			}
			this.lastListing.addAll(files);
		}
		return this.lastListing;
	}
	
	public File getSignaturePath(){
		return this.signaturePath;
	}
	
	public SLBook loadBook(File filePath){
		if (filePath.getName().endsWith(".txt")){
			return loadBookwormBook(filePath);
		}
		else if (filePath.getName().endsWith(dabFile)){
			return loadBookFromWBFile(filePath);
		}
		return null;
	}

	private SLBook loadBookwormBook(File filePath){
		List<String> f = readFile(filePath);
		
		if (f.size() >= 4 && StringUtils.isNumeric(f.get(0))){
			SLBook loadedBook = new SLBook();
			loadedBook.clear();
			
			loadedBook.title = SLBook.truncateStringChars(f.get(1), "..", 16, false);
			loadedBook.author = SLBook.truncateStringChars(f.get(2), "..", 16, false);
			String bookText = f.get(f.size()-1);
			
			String[] largePages = bookText.split("(\\s::){2,}");
			
			for (String largePage : largePages){
				if (loadedBook.totalPages() > 0){
					SLPage currPage = loadedBook.pages.get(loadedBook.totalPages()-1);
					if (!currPage.asString().isEmpty()){
						currPage = SLPage.pad(currPage);
					}
				}
				loadedBook.addText(loadedBook.totalPages(), 0, 0, largePage.replaceAll("\\s*::\\s*", "\n  "), true);
			}
			
			return loadedBook;
		}
		return null;
	}
	
	
	public List<String> readFile(File path){
		List<String> out = new ArrayList();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			KUtil.print(Brush.RED + "Файл не найден! " + path.getAbsolutePath());
			return null;
		}
	    try {
	        String line = br.readLine();
	        while (line != null) {
	        	out.add(line.replace("\u00C2\u00A7", "\u00A7"));
	            line = br.readLine();
	        }
	    } catch (IOException e) {
			e.printStackTrace();
			KUtil.print(Brush.RED + "Ошибка чтения файла! " + path.getAbsolutePath());
			return null;
		} finally {
	        try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		return out;
	}
	
	public static String cleanGHBString(String strIn){
		strIn = strIn.replaceAll("(?s)//.*?((\\n)|(\\r\\n)|(\\Z))","\n");
		strIn = strIn.replaceAll("(?s)((/\\*).*?((\\*/)|(\\Z)))|(((/\\*)|(\\A)).*?(\\*/))", "");
		strIn = strIn.replaceAll("[\\t\\r\\n ]+(##|>>>>)", "$1");
		strIn = strIn.replaceAll("[\\r\\n]", "");
		return strIn;
	}
	
	public SLBook getBookByID(int id) {
		SLBook slBook = null;
		try {
			List<File> books = this.listFiles(this.dabFolderPath);
			Scanner sc;
			for(File book : books) {
				File bookFile = new File(dabFolderPath, book.getName());
				sc = new Scanner(bookFile);
				while(sc.hasNextLine()){
				    String str = sc.nextLine();
				    if(str.contains("id:")) {
				    	slBook = this.loadBookFromWBFile(book);
				    }
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return slBook;
	}
	
	private SLBook loadBookFromWBFile(File filePath){
		SLBook loadedBook = new SLBook();
		loadedBook.clear();
		
		List<String> rawFile = readFile(filePath);
		if (rawFile == null || rawFile.isEmpty()){
			return null;
		}
		
		String concatFile = "";
		for (String line : rawFile) {
			if (line.toLowerCase().startsWith("title:") && loadedBook.title.isEmpty()){
				if (line.length() >= 7){
					loadedBook.title = line.substring(6);
					if (line.contains("/*")){
						concatFile += line.substring(line.indexOf("/*")) + "\n";
					}
				}
			}
			else if (line.toLowerCase().startsWith("author:") && loadedBook.author.isEmpty()){
				if (line.length() >= 8){
					loadedBook.author = line.substring(7);
					if (line.contains("/*")){
						concatFile += line.substring(line.indexOf("/*")) + "\n";
					}
				}
			}
			else if (line.toLowerCase().startsWith("id:") && loadedBook.id == 0){
				if (line.length() >= 4){
					loadedBook.id = Integer.parseInt(line.substring(3));
				}
			}
			else{
				concatFile += line + "\n";
			}
		}
		
		concatFile = cleanGHBString(concatFile);
		concatFile = concatFile.replaceAll("##", "\n");
		
		String[] pageBroken = concatFile.split(">>>>");
		for (String largePage : pageBroken){
			if (loadedBook.totalPages() > 0){
				SLPage currPage = loadedBook.pages.get(loadedBook.totalPages()-1);
				if (!currPage.asString().isEmpty()){
					currPage = SLPage.pad(currPage);
				}
			}
			loadedBook.addText(loadedBook.totalPages(), 0, 0, largePage, true);
		}
		return loadedBook;
	}
}
