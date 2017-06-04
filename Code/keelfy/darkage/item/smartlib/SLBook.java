package keelfy.darkage.item.smartlib;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class SLBook {
	
	public List<SLPage> pages = new ArrayList();
	public String title = "";
	public String author = "";
	public int id = 0;
	public static enum CursorDirection{ UP, DOWN, LEFT, RIGHT }
	public int cursorPage = 0;
	public int cursorLine = 0;
	public int cursorPosChars = 0;
	
	public SLBook(){}
	
	public int totalPages(){
		return this.pages.size();
	}
	
	/** Clears the page list, title and author, and resets the cursor position */
	public void clear(){
		this.pages.clear();
		this.title = "";
		this.author = "";
		this.cursorPage = 0;
		this.cursorLine = 0;
		this.cursorPosChars = 0;
	}
	
	/** Copies the contents of inBook into this book */
	public void clone(SLBook inBook){
		this.clear();
		this.title = inBook.title;
		this.author = inBook.author;
		SLPage currPage;
		SLLine currLine;
		for (SLPage inPage : inBook.pages){
			this.pages.add(new SLPage());
			currPage = this.pages.get(this.pages.size()-1);
			currPage.clear();
			for (SLLine inLine : inPage.lines){
				currPage.lines.add(new SLLine());
				currLine = currPage.lines.get(currPage.lines.size()-1);
				currLine.text = inLine.text;
				currLine.wrappedFormatting = inLine.wrappedFormatting;
			}
		}
	}
	
	
	/** Prints a representation of the book to stdout. Used for debugging */
	public void dump(){
		String separator = "###############################################\n";
		for (int i=0; i<totalPages(); i++){
			System.out.println("\n" + separator + separator + "Page " + i);
			this.pages.get(i).dump();
		}
	}
	
	
	/** Removes the range of pages from the book, then returns the removed pages as a list of strings */
	public List<String> cutPages(int firstPage, int lastPage){
		
		//Idiot proofing
		if (lastPage < firstPage){
			int temp = firstPage;
			firstPage = lastPage;
			lastPage = temp;
		}
		if (firstPage < 0){firstPage = 0;}
		if (lastPage >= this.pages.size()){lastPage = this.pages.size();}
		
		//Copy the text from the pages to be removed
		List<String> cutPages = copyPages(firstPage, lastPage);
		
		//Remove the pages
		for (int i=lastPage; i>=firstPage; i--){
			this.pages.remove(i);
		}
		if (this.pages.isEmpty()){this.pages.add(new SLPage());}
		
		//Make sure the cursor won't be somewhere invalid
		this.cursorLine = 0;
		this.cursorPosChars = 0;
		if (this.cursorPage > this.pages.size()-1){
			this.cursorPage = this.pages.size()-1;
		}
		
		return cutPages;
	}
		
	
	/** Returns the range of pages specified by firstPage and lastPage as a list of strings */
	public List<String> copyPages(int firstPage, int lastPage){
		
		//Idiot proofing
		if (lastPage < firstPage){
			int temp = firstPage;
			firstPage = lastPage;
			lastPage = temp;
		}
		if (firstPage < 0){firstPage = 0;}
		if (lastPage >= this.pages.size()){lastPage = this.pages.size();}
		
		//Copy the selected pages
		List<String> copiedPages = new ArrayList();
		for (int i=firstPage; i<=lastPage; i++){
			copiedPages.add(this.pages.get(i).asString());
		}
		
		return copiedPages;
	}
	
	
	/** Used for pasting pages into a book.
	 * Pads out the end of each page to be inserted with newlines, then inserts the group at the given index (provided it's sensible).
	 * Note that this will discard any excess if a string in newPages is too long to fit on a page
	 */
	public void insertPages(int index, List<String> newPages){
		//TODO: IF a string is too long, it should wrap to the next page (and then pad it out I guess) instead of just being discarded
		//TODO: This is kind of hacky. It should really be taking a list of GPages as a parameter, not strings.
		
		//Idiot proofing
		if (index < 0){index = 0;}
		if (index > this.pages.size()){index = this.pages.size();}
		
		//Pad and insert
		SLPage tempPage;
		int offset = 0;
		for (String pageText : newPages){
			tempPage = new SLPage();
			tempPage.addText(0, 0, pageText);
			this.pages.add(index+offset, SLPage.pad(tempPage));
			offset++;
		}
		
		//Make sure the cursor won't be somewhere invalid
		this.cursorLine = 0;
		this.cursorPosChars = 0;
	}
	
	
	/**
	 * Removes one character adjacent to the cursor. This is used for backspace & delete
	 * Note that this will remove a second character if the first one is part of a formatting symbol
	 * @param nextChar true to remove the character to the right of the cursor (delete), false to remove the one to the left (backspace)
	 */
	public void removeChar(boolean nextChar){
		SLLine currLine = this.pages.get(this.cursorPage).lines.get(this.cursorLine);
		if (nextChar){
			//Delete pressed
			if (this.cursorPosChars < currLine.text.length()){
				//There's at least one character after the cursor
				//Check if we're about to delete the first half of a formatting symbol (and remove the other half if we are)
				if (this.cursorPosChars+1 < currLine.text.length() && currLine.text.charAt(this.cursorPosChars) == '\u00a7' &&
						(SLLine.isFormatColor(currLine.text.charAt(this.cursorPosChars+1)) || 
								SLLine.isFormatSpecial(currLine.text.charAt(this.cursorPosChars+1)))){
					removeText(this.cursorPage, this.cursorLine, this.cursorPosChars, 
							   this.cursorPage, this.cursorLine, this.cursorPosChars+2);
				}
				else{
					removeText(this.cursorPage, this.cursorLine, this.cursorPosChars, 
							   this.cursorPage, this.cursorLine, this.cursorPosChars+1);
				}
			}
			else{
				//The cursor is at the end of the line. Try the next line
				if(this.cursorLine+1 < this.pages.get(this.cursorPage).lines.size()){
					int toLine = this.cursorLine + 1;
					if (this.pages.get(this.cursorPage).lines.get(toLine).text.length() >= 1){
						removeText(this.cursorPage, this.cursorLine, this.cursorPosChars, 
								   this.cursorPage, toLine, 1);
					}
				}
				//The line is at the end of the page. Try the next page
				else{
					if (this.cursorPage+1 < this.pages.size()){
						SLPage nextPage = this.pages.get(this.cursorPage+1);
						if (nextPage.asString().isEmpty()){
							this.pages.remove(this.cursorPage+1);
						}
						else{
							removeText(this.cursorPage, this.cursorLine, this.cursorPosChars, 
									   this.cursorPage+1, 0, 1);
						}
					}
				}
			}
		}
		else{
			//Backspace pressed
			if (this.cursorPosChars > 0){
				//There's at least one character before the cursor in this line.
				//Check if we're about to remove the second half of a formatting character (and remove the first half too if we are)
				char charToLeft = currLine.text.charAt(this.cursorPosChars-1);
				if (this.cursorPosChars > 1 && (SLLine.isFormatColor(charToLeft) || SLLine.isFormatSpecial(charToLeft)) &&
						currLine.text.charAt(this.cursorPosChars-2) == '\u00a7'){
					removeText(this.cursorPage, this.cursorLine, this.cursorPosChars-2, 
							   this.cursorPage, this.cursorLine, this.cursorPosChars);
				}
				else{
					removeText(this.cursorPage, this.cursorLine, this.cursorPosChars-1, 
							   this.cursorPage, this.cursorLine, this.cursorPosChars);
				}
			}
			else if (this.cursorLine > 0){
				//There's at least one line before the current one. Remove the last character in that line.
				currLine = this.pages.get(this.cursorPage).lines.get(this.cursorLine-1);
				removeText(this.cursorPage, this.cursorLine-1, currLine.text.length()-1, 
						   this.cursorPage, this.cursorLine, this.cursorPosChars);
			}
			else if (this.cursorPage > 0){
				//There's at least one page before this one. Remove the last character on it.
				SLPage currPage = this.pages.get(this.cursorPage-1);
				int lineNum = currPage.lines.size()-1;
				currLine = currPage.lines.get(lineNum);
				removeText(this.cursorPage-1, lineNum, currLine.text.length()-1, 
						   this.cursorPage, this.cursorLine, this.cursorPosChars);
			}
		}
	}
	
	
	/**
	 * Removes the text described by the to and from addresses, then shifts the rest of the text up/back (however you want to look at it)
	 * Essentially this is the opposite of addText(..)
	 */
	public void removeText(int fromPage, int fromLine, int fromChar, int toPage, int toLine, int toChar){
		
		//Idiot proofing
		if (fromPage < 0){fromPage = 0;}
		if (fromLine < 0){fromLine = 0;}
		if (fromChar < 0){fromChar = 0;}
		if (toPage >= this.pages.size()){toPage = this.pages.size()-1;}
		if (toPage < fromPage){toPage = fromPage;}
		SLPage currPage = this.pages.get(toPage);
		if (toLine >= currPage.lines.size()){toLine = currPage.lines.size()-1;}
		if (toLine < fromLine && toPage == fromPage){toLine = fromLine;}
		SLLine currLine = currPage.lines.get(toLine);
		if (toChar > currLine.text.length()){toChar = currLine.text.length();}
		if (toChar < fromChar && toLine == fromLine && toPage == fromPage){return;}
		
		//Get any text after the 'to' address
		String moveText = currLine.text.substring(toChar);
		int linePos = toLine;
		while (linePos++ < currPage.lines.size()-1){
			moveText = moveText + currPage.lines.get(linePos).text;
		}
		int pagePos = toPage;
		while (pagePos++ < this.pages.size()-1){
			moveText = moveText + this.pages.get(pagePos).asString();
		}
		
		//Remove everything after fromPage, fromLine, fromChar
		pagePos = this.pages.size()-1;
		while (pagePos > fromPage){
			this.pages.remove(pagePos);
			pagePos--;
		}
		currPage = this.pages.get(fromPage);
		linePos = currPage.lines.size()-1;
		while (linePos > fromLine){
			currPage.lines.remove(linePos);
			linePos--;
		}
		currLine = currPage.lines.get(fromLine);
		currLine.text = currLine.text.substring(0, fromChar);
		
		addText(fromPage, fromLine, fromChar, moveText, false);
	}
	
	
	/**
	 * Wrapper for addText(..) that adds text at the current cursor position
	 */
	public void addTextAtCursor(String strAdd){
		addText(this.cursorPage, this.cursorLine, this.cursorPosChars, strAdd, true);
	}
	
	
	/**
	 * Inserts text at the given position and pushes any text after it down the appropriate number of lines/pages
	 */
	public void addText(int pageNum, int lineNum, int charPos, String strAdd, boolean setCursorAfterInsertedText){
		
		//TODO: There's an error where inserting a formatting character and then typing causes the cursor position to go all screwy. 
		//I'm having trouble reproducing it though
		
		//Idiot proofing
		if (pageNum > this.totalPages()){
			pageNum = this.totalPages();
		}
		else if (pageNum < 0){
			pageNum = 0;
		}
		
		//Add a new page if necessary
		if (pageNum == this.totalPages()){
			this.pages.add(new SLPage());
		}
		
		//Figure out where the cursor will be set after the insert (in characters from the start of the book)
		int charsBeforeCursor = charPos;
		SLPage currPage = this.pages.get(pageNum);
		for (int i=lineNum-1; i>=0; i--){
			charsBeforeCursor += currPage.lines.get(i).text.length();
		}
		for (int i=pageNum-1; i>=0; i--){
			charsBeforeCursor += this.pages.get(i).asString().length();
		}
		if (setCursorAfterInsertedText){
			charsBeforeCursor += strAdd.length();
		}
		
		//Wrap the text from the line at lineNum around strAdd, then remove it from the page (the new strAdd will be added to the line
		//before the removed one in the next section so that line wrapping happens properly).
		//If lineNum is the first line of the first page, just blank it out and add everything there
		currPage = this.pages.get(pageNum);
		SLLine currLine = currPage.lines.get(lineNum);
		strAdd = currLine.text.substring(0, charPos) + strAdd + currLine.text.substring(charPos);
		if (lineNum > 0){
			currPage.lines.remove(lineNum);
			lineNum -= 1;
			currLine = currPage.lines.get(lineNum);
			charPos = currLine.text.length();
		}
		else if (pageNum > 0){
			currPage.lines.remove(lineNum);
			pageNum -= 1;
			currPage = this.pages.get(pageNum);
			lineNum = currPage.lines.size()-1;
			currLine = currPage.lines.get(lineNum);
			charPos = currLine.text.length();
		}
		else{
			currLine.text = "";
			charPos = 0;
		}
		
		//Add the new text and shift any subsequent text
		while (!strAdd.isEmpty()){
			if (pageNum == this.totalPages()){
				this.pages.add(new SLPage());
			}
			strAdd = this.pages.get(pageNum).addText(lineNum, charPos, strAdd);
			pageNum++;
			lineNum = 0;
			charPos = 0;
		}
		
		//Set the cursor position
		this.cursorPage = 0;
		currPage = this.pages.get(this.cursorPage);
		while (charsBeforeCursor > currPage.asString().length()){
			charsBeforeCursor -= currPage.asString().length();
			this.cursorPage += 1;
			currPage = this.pages.get(this.cursorPage);
		}
		this.cursorLine = 0;
		currLine = currPage.lines.get(this.cursorLine);
		while (charsBeforeCursor > currLine.text.length()){
			charsBeforeCursor -= currLine.text.length();
			this.cursorLine += 1;
			currLine = currPage.lines.get(this.cursorLine);
		}
		this.cursorPosChars = charsBeforeCursor;	
		
		//Prevent the cursor from getting on the outside of a newline character
		//Note that this will try to move the cursor down to the next line/page
		if (this.cursorPosChars > 0 && currLine.text.charAt(this.cursorPosChars-1) == '\n'){
			if (this.cursorLine < currPage.lines.size()-1){
				this.cursorLine++;
				this.cursorPosChars = 0;
			}
			else if (this.cursorPage < this.totalPages()-1){
				this.cursorPage++;
				this.cursorLine = 0;
				this.cursorPosChars = 0;
			}
			else{
				this.cursorPosChars--;
			}
		}
	}
	
	
	/**
	 * Moves the cursor one unit in the direction specified (if possible)
	 */
	public void moveCursor(CursorDirection direction){
		SLPage currPage = this.pages.get(this.cursorPage);
		SLLine currLine = currPage.lines.get(this.cursorLine);
		int cursorPosPx = getCursorX();
		int cursorPosCharsWithFormat;
		switch (direction){
			
			case UP:
				if (this.cursorLine == 0){
					if (this.cursorPage > 0){
						this.cursorPage--;
						currPage = this.pages.get(this.cursorPage); 
						this.cursorLine = currPage.lines.size()-1;
					}
					else{
						this.cursorPosChars = 0;
						return;
					}
				}
				else{
					if (cursorPosPx <= 0){this.cursorPosChars = 0;}
					this.cursorLine--;
				}
				currLine = currPage.lines.get(this.cursorLine);
				cursorPosCharsWithFormat = SLLine.sizeStringToApproxWidthBlind(currLine.wrappedFormatting + currLine.text, cursorPosPx);
				this.cursorPosChars = cursorPosCharsWithFormat - currLine.wrappedFormatting.length();
				
				//Prevent the cursor from getting on the outside of a newline character
				if (this.cursorPosChars > 0){
					if (currLine.text.charAt(this.cursorPosChars-1) == '\n'){
						this.cursorPosChars--;
					}
				}
				return;
				
			case DOWN:
				if (this.cursorLine == currPage.lines.size()-1){
					if (this.cursorPage < this.totalPages()-1){
						this.cursorPage++;
						this.cursorLine = 0;
					}
					else{
						currLine = currPage.lines.get(currPage.lines.size()-1);
						this.cursorPosChars = currLine.text.length();
					}
				}
				else{
					if (cursorPosPx <= 0){this.cursorPosChars = 0;}
					this.cursorLine++;
				}
				currLine = currPage.lines.get(this.cursorLine);
				currLine = this.pages.get(this.cursorPage).lines.get(this.cursorLine);
				cursorPosCharsWithFormat = SLLine.sizeStringToApproxWidthBlind(currLine.wrappedFormatting + currLine.text, cursorPosPx);
				this.cursorPosChars = cursorPosCharsWithFormat - currLine.wrappedFormatting.length();
				
				//Prevent the cursor from getting on the outside of a newline character
				if (this.cursorPosChars > 0){
					if (currLine.text.charAt(this.cursorPosChars-1) == '\n'){
						this.cursorPosChars--;
					}
				}
				break;
				
			case LEFT:
				if (this.cursorPosChars > 0){
					this.cursorPosChars--;
					//Avoid moving into the middle of a formatting symbol (i.e. we may need to move one more character to the left)
					if (this.cursorPosChars > 0 && currLine.text.charAt(this.cursorPosChars-1) == '\u00a7'){
						this.cursorPosChars--;
					}					
				}
				else{
					if (this.cursorLine > 0){
						this.cursorLine--;
						this.cursorPosChars = getCurrLine().length()-1;
					}
				}
				return;
				
			case RIGHT:
				int currLineLength = currLine.text.length();
				if (this.cursorPosChars < currLineLength && currLine.text.charAt(this.cursorPosChars) != '\n'){
					this.cursorPosChars++;
					//Skip forward an extra character if we're inside a formating symbol
					if (currLine.text.charAt(this.cursorPosChars-1) == '\u00a7' && this.cursorPosChars < currLineLength-1){
						char nextChar = currLine.text.charAt(this.cursorPosChars);
						if (SLLine.isFormatSpecial(nextChar) || SLLine.isFormatColor(nextChar)){
							this.cursorPosChars++;
						}
					}
				}
				else{
					if (this.cursorLine < this.pages.get(this.cursorPage).lines.size()-1){
						this.cursorPosChars = 0;
						this.cursorLine++;
					}
				}
				return;
			default:
				break;
		}
	}
	
	
	/** 
	 * Moves the page cursor by [numPages] pages (positive or negative) but stays within the limits of the book.
	 * It's mostly used for navigation within the book by the GUI 
	 */
	public void turnPage(int numPages){
		SLPage oldPage = this.pages.get(this.cursorPage);
		this.cursorPage = this.cursorPage+numPages;
		if (this.cursorPage < 0){
			this.cursorPage = 0;
		}
		else if (this.cursorPage >= totalPages()){
				this.cursorPage = totalPages();
				oldPage = SLPage.pad(oldPage);
				this.pages.add(new SLPage());
		}
		this.cursorLine = 0;
		this.cursorPosChars = 0;
	}
	
	
	/**
	 * Returns the cursor offset from the left side of the text (in pixels)
	 */
	public int getCursorX(){
		if (this.pages.isEmpty()){
			return 0;
		}
		else{
			if (this.cursorLine == 9){
				boolean canary=true;
			}
			SLLine currLine = this.pages.get(this.cursorPage).lines.get(this.cursorLine);
			return SLLine.getStringWidth(currLine.getTextWithWrappedFormatting().substring(0, this.cursorPosChars + currLine.wrappedFormatting.length()));
		}
	}
	
	
	/** Returns the current line without any wrapped formatting */
	public String getCurrLine(){
		return this.pages.get(this.cursorPage).lines.get(this.cursorLine).text;
	}
	
	
	/**
	 * Returns the current line as a string with any wrapped formatting
	 */
	public String getCurrLineWithWrappedFormatting(){
		return this.pages.get(this.cursorPage).lines.get(this.cursorLine).getTextWithWrappedFormatting();
	}
	
	
	/**
	 * Returns the current page as a string ready to be displayed in a Minecraft Book & Quill book.
	 */
	public String getCurrPageAsMCString(){
		//Idiot proofing
		if (this.cursorPage < 0){
			System.out.println("GBook.cursorPage was less than zero (" + this.cursorPage + "... This should never happen...");
			this.cursorPage = 0;
			this.cursorLine = 0;
			this.cursorPosChars = 0;
		}
		else if (this.cursorPage >= totalPages()){
			this.cursorPage = totalPages()-1;
			if (this.cursorPage < 0){this.cursorPage = 0;}
			SLPage lastPage = this.pages.get(this.cursorPage);
			this.cursorLine = lastPage.lines.size()-1;
			if (this.cursorLine < 0){this.cursorLine = 0;}
			SLLine lastLine = lastPage.lines.get(this.cursorLine);
			this.cursorPosChars = lastLine.text.length()-1;
			if (this.cursorPosChars < 0){this.cursorPosChars = 0;}
		}
		
		return this.pages.get(cursorPage).asString();
	}
	
	
	/**  Returns true if the book contains no printable characters or formatting symbols */ 
	public boolean isEmpty(){
		for (SLPage page : this.pages){
			if (!page.isEmpty()){
				return false;
			}
		}
		return true;
	}
	
	
	/** Assembles the current book as a Minecraft book object (max. 50 pages) and sends it to the server.
	 * @param signIt - true if the book should be signed (it will no longer be editable)
	 */
//	public void sendBookToServer(boolean signIt, int amount){
//		System.out.println("Отправка книги на сервер!");
//		
//		//Check that the held book is writable
//		ItemStack bookObj = Minecraft.getMinecraft().thePlayer.getHeldItem();
//    	if (bookObj.getItem().equals(SmartLibrary.inkwell)){
//	        if (this.totalPages() > 0)
//	        {
//	        	//Trim empty trailing pages
//	        	for(int i=this.totalPages()-1; i>=0; i--){
//	        		if (this.pages.get(i).asString().isEmpty()){
//	        			if (i==0){
//	        				//The whole book was empty. Give up.
//	        				System.out.println("Can't save an empty book! Aborting!");
//	        				return;
//	        			}
//	        			else{
//	        				this.pages.remove(i);
//	        				if (this.cursorPage >= this.totalPages()){
//	        					this.cursorPage = this.totalPages()-1;
//	        					this.cursorLine = 0;
//	        					this.cursorPosChars = 0;
//	        				}
//	        			}
//	        		}
//	        		else{
//	        			break;
//	        		}
//	        	}
//	        		        	
//	        	//Add the text from each page to the book object (maximum of 50 pages)
//	        	NBTTagList bookPages = new NBTTagList();
//	        	int pageCount = 0;
//	        	for (SLPage page : this.pages){
//	        		pageCount ++;
//	        		if (pageCount == 51){
//	        			Drawer.gamePrint(Drawer.DARK_RED + "Book is over 50 pages. Sending the first 50 to the server, and automatically saving the book...");
//	        			FileHandler fh = new FileHandler();
//	        			fh.saveBookToGHBFile(this);
//	        			break;
//	        		}
//	        		bookPages.appendTag(new NBTTagString(page.asString()));
//	        		
//	        	}
//	            if (bookObj.hasTagCompound()){
//	                NBTTagCompound nbttagcompound = bookObj.getTagCompound();
//	                nbttagcompound.setTag("pages", bookPages);
//	            }
//	            else{
//	                bookObj.setTagInfo("pages", bookPages);
//	            }
//	        	
//	            //Add title and author if necessary
//	            String sendMode = "MC|BEdit";
//	            if (signIt)
//	            {
//	            	sendMode = "MC|BSign";
//	                bookObj.setTagInfo("author", new NBTTagString(this.author));
//	                bookObj.setTagInfo("title", new NBTTagString(this.title));
//	                //bookObj.func_150998_b()
//	            }
//	
//	            //Send it
//	            ByteBuf bytebuf = Unpooled.buffer();
//	            try{
//	                (new PacketBuffer(bytebuf)).writeItemStackToBuffer(bookObj);
//	                Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C17PacketCustomPayload(sendMode, bytebuf));
//	            }
//	            catch (Exception exception){
//	                System.out.println("Couldn\'t send book info:\n" + exception.getMessage());
//	                Drawer.gamePrint(Drawer.RED + "Failed to send book to server.");
//	            }
//	            finally{
//	                bytebuf.release();
//	            }
//	        }
//	    }
//	}
	
	
	/**
	 * Truncates a string to the number of characters in maxChars
	 * The character count includes the substitute characters (which will only be added if the string is truncated)
	 */
	public static String truncateStringChars(String strIn, String substituteChars, int maxChars, boolean keepRightSide){
		if (strIn.length() <= maxChars){return strIn;}
		strIn = strIn.replaceAll(" ", "");
		if (strIn.length() <= maxChars){return strIn;}
		if (keepRightSide){
			strIn = substituteChars + strIn.substring(strIn.length()-(maxChars-substituteChars.length()), strIn.length());
		}
		else{
			strIn = strIn.substring(0, maxChars-substituteChars.length()) + substituteChars;
		}
		return strIn;
	}
	
	
	/**
	 * Truncates a string to the number of pixels in maxWidth
	 * Prefix and suffix are optional, but if they are set to anything other than a blank string, they will be counted
	 * as part of the length of the output string.
	 * KeepRightSide determines whether the right (end of the string) or left (start of the string) should be kept
	 * (i.e. that the opposite end should be removed).
	 */
	public static String truncateStringPixels(String strIn, String substituteChars, int maxWidth, boolean keepRightSide){
		FontRenderer f = Minecraft.getMinecraft().fontRenderer;
		if (f.getStringWidth(strIn) <= maxWidth){
			return strIn;
		}
		String strOut = "";
		int subCharsWidth = f.getStringWidth(substituteChars);
		
		int startPos = 0;
		int endPos = strIn.length()-1;
		int direction = 1;
		if (keepRightSide){
			startPos = strIn.length()-1;
			endPos = -1;
			direction = -1;
		}
		for (int i=startPos; i!=endPos; i+=direction){
			char c = strIn.charAt(i);
			if (f.getStringWidth(c + strOut) + subCharsWidth <= maxWidth){
				if (keepRightSide){
					strOut = c + strOut;
				}
				else{
					strOut = strOut + c;
				}
			}
			else{
				break;
			}
		}
		if (keepRightSide){
			return substituteChars + strOut;
		}
		else{
			return strOut + substituteChars;
		}
		
	}
	
	
	/** Returns the string without Minecraft formatting symbols */
	public static String removeFormatting(String strIn){
		return strIn.replaceAll("\u00a7[0-9a-fA-Fk-oK-OrR]?", "");
	}
	
}
