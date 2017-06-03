package keelfy.witcher.handler.client;

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
import keelfy.witcher.item.smartlib.SLBook;
import keelfy.witcher.item.smartlib.SLPage;
import keelfy.witcher.util.DAUtil;

public class FileHandler {
	
	private File defaultPath;
	private File bookSavePath;
	private File signaturePath;
	
	public File currentPath;
	private List<File> lastListing = new ArrayList();
	private String lastCheckedPath = "";
	
	public String dabFile = ".dab";
	public String daiFile = ".dai";
	
	private File configDir;
	
	public FileHandler(File configDir) {
		this.configDir = configDir;
		
		this.bookSavePath = new File(getDataFolderPath(), "Books");
	}
	
	public File getConfigurationDirectory() {
		return configDir;
	}
	
	public File getBookSavePath() {
		return bookSavePath;
	}
	
	public File getDataFolderPath() {
		String path = configDir.getParent() + File.separator + "mods";
		if(!DAUtil.SERVER) path += File.separator + "1.7.10";
		if (path.endsWith("."))
			path = path.substring(0, path.length() - 2);
		return new File(path, "DarkAge");
	}
	
	public File getItemDAIFile(String name) {
		String path = getDataFolderPath().getAbsolutePath();
		if (path.endsWith("."))
			path = path.substring(0, path.length() - 2);
		return new File(path + File.separator + "Items", name + daiFile);
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
	
	
	public File getDefaultPath(){
		return this.defaultPath;
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
		
		//Bookworm txt files are always at least 4 lines long
		//The first line is the ID number
		if (f.size() >= 4 && StringUtils.isNumeric(f.get(0))){
			SLBook loadedBook = new SLBook();
			loadedBook.clear();
			//There's a good chance this is a bookworm book
			loadedBook.title = SLBook.truncateStringChars(f.get(1), "..", 16, false);
			loadedBook.author = SLBook.truncateStringChars(f.get(2), "..", 16, false);
			String bookText = f.get(f.size()-1);
			
			//Split the string at any page break (two or more sets of two colons e.g. :: :: or :: :: :: ::)
			String[] largePages = bookText.split("(\\s::){2,}");
			
			//Add the text to the book, replacing any instances of the paragraph break (a single set of colons ::) 
			//with a newline character and two spaces
			for (String largePage : largePages){
				//Pad the last page before a page break with newlines
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
	
//	public boolean writeFile(List<String> toWrite, File filePath){
//		boolean failedFlag = false;
//	
//		File path = filePath.getParentFile();
//		if (!path.exists()){
//			if (!path.mkdirs()){
//				failedFlag = true;
//			}
//		}
//		
//		if (!failedFlag){
//			try {
//			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
//			    for (String s : toWrite){
//			    	out.println(s);
//			    }
//			    out.close();
//			} 
//			catch (IOException e) {
//				failedFlag = true;
//				System.out.println("Smart Library: Ошибка записи!");
//				System.out.println(e.getMessage());
//				return false;
//			}
//		}
//		
//		if (failedFlag){
//			Brush.gamePrint(Brush.RED + "ОШИБКА ЗАПИСИ НА ДИСК!");
//			return false;
//		}		
//		return true;
//	}
	
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
			List<File> books = this.listFiles(this.bookSavePath);
			Scanner sc;
			for(File book : books) {
				File bookFile = new File(bookSavePath + File.separator + book.getName());
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
