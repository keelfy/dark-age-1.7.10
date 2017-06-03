package keelfy.witcher.item.smartlib;

import java.util.ArrayList;
import java.util.List;

public class SLPage {
	
	public List<SLLine> lines = new ArrayList();
	
	
	public SLPage(){
		lines.add(new SLLine());
	}
	
	
	/**
	 * Adds text to at the specified position in the current page. 
	 * @return Overflow (any text that doesn't fit on this page)
	 */
	public String addText(int lineNum, int charPos, String strAdd){
		
		//Overflow instead of adding a 14th line (13 is the maximum most of the time)
		if (lineNum >= 13){
			return strAdd;
		}
		
		//Idiot proofing
		if (lineNum < 0){
			lineNum = 0;
		}
		else if (lineNum > this.lines.size()){
			lineNum = this.lines.size();
		}
		
		if (lineNum == this.lines.size()){
			this.lines.add(new SLLine());
		}
		
		SLLine currLine = this.lines.get(lineNum);
		
		//Get the wrapped formatting from the previous line
		if (lineNum > 0){
			currLine.wrappedFormatting = this.lines.get(lineNum-1).getActiveFormatting();
		}
		else{
			currLine.wrappedFormatting = "";
		}
		
		String lineOverflow = currLine.addText(charPos, strAdd);
		
		//Check if we are going to hit the 255 character page limit in this line (and deal with it)
		int prevLinesCharCount = 0;
		for (int i=0; i<lineNum; i++){
			prevLinesCharCount += this.lines.get(i).text.length();
		}
		if (prevLinesCharCount + currLine.text.length() > 255){
			//Get the text from any lines after the current one and remove them from the page
			String pageOverflow = "";
			for (int i=this.lines.size()-1; i>lineNum; i--){
				pageOverflow = this.lines.get(i).text + pageOverflow;
				this.lines.remove(i);
			}
			
			//Split the current line (either at the 255 character limit, or a space, whatever is first)
			int splitAt = 255 - prevLinesCharCount;
			if (currLine.text.charAt(splitAt) != ' '){
				int spacePos = currLine.text.lastIndexOf(' ', splitAt);
				if (spacePos == -1){
					splitAt = 0;
				}
				else{
					splitAt = spacePos;
				}
			}
			//If the line is being 'split' at the start, just remove it all together
			if (splitAt == 0){
				pageOverflow = currLine.text + lineOverflow + pageOverflow;
				this.lines.remove(lineNum);
			}
			else{
				pageOverflow = currLine.text.substring(splitAt) + lineOverflow + pageOverflow;
				currLine.text = currLine.text.substring(0, splitAt);
			}
			
			return pageOverflow;
		}
		
		if (!lineOverflow.isEmpty()){
			return this.addText(lineNum+1, 0, lineOverflow);
		}
		else{
			return "";
		}
	}
	
	
	/** Empties the page */
	public void clear(){
		this.lines.clear();
	}
	
	
	/** Adds newline characters to fill any empty space at the end of the page */
	public static SLPage pad(SLPage page){
		SLLine newLine;
		SLLine lastLine = page.lines.get(page.lines.size()-1);
		if (SLLine.getStringWidth(lastLine.wrappedFormatting + lastLine.text) < 116){
			lastLine.text += "\n";
		}
		while (page.lines.size() < 13){
			newLine = new SLLine();
			newLine.text = "\n";
			page.lines.add(newLine);
		}
		return page;
	}


	/** Returns all of the lines on this page concatenated into a single string. This is useful for displaying the page in Minecraft */
	public String asString(){
		String out = "";
		for (SLLine line : this.lines){
			out += line.text;
		}
		return out;
	}
	
	
	/** Returns the number of characters on the page. Note that non-printable characters also count towards this total */
	public int charCount(){
		int total = 0;
		for (SLLine line : this.lines){
			total += line.text.length();
		}
		return total;
	}
	
	
	/** Prints the lines of this page to stdout. Used for debugging */
	public void dump(){
		System.out.println("##############################################################################");
		for (SLLine line : this.lines){
			System.out.println("WF:|" + line.wrappedFormatting + "|TX:|" + line.text.replaceAll("\n", "\\\\n") + "|");
		}
		System.out.println("##############################################################################\n\n");
	}
	
	
	public boolean isEmpty(){
		for (SLLine line : this.lines){
			if (!line.text.isEmpty()){
				return false;
			}
		}
		return true;
	}
}
