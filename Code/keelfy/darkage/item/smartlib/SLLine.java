package keelfy.darkage.item.smartlib;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class SLLine {
	
	public static final int BOOK_TEXT_WIDTH = 116;
	public static final char SPLIT_CHAR = '\u1337';
	
	public String wrappedFormatting = "";
	public String text = "";
	
	/** Adds text to strAdd at charPos and returns any text that wont fit on the this line */
	public String addText(int charPos, String strAdd){
		
		//Idiot proofing
		if (charPos < 0){
			charPos = 0;
		}
		else if (charPos > this.text.length()){
			charPos = this.text.length();
		}
		
		//Assemble the new (longer) line, then slice it down to size
		String newText = this.text.substring(0, charPos) + strAdd + this.text.substring(charPos);
		List<String> newLines = listFormattedStringToWidth(newText, this.wrappedFormatting);
		
		//Set text to the first line, and add the rest to the overflow
		this.text = newLines.get(0);
		String overflow = "";
		for (int i=1; i<newLines.size(); i++){
			overflow += newLines.get(i);
		}
		
		return overflow;		
	}
	
	
    /**
     * This is very similar to sizeStringToWidthBlind, but the length passed to it is just a guide rather than a hard limit.
     * This should really only be used for text navigation with the up and down arrow keys
     */
    public static int sizeStringToApproxWidthBlind(String str, int lenPixels){
    	FontRenderer f = Minecraft.getMinecraft().fontRenderer;
    	if (getStringWidth(str) <= lenPixels){
    		return str.length();
    	}
    	String outStr = str.substring(0, sizeStringToWidthBlind(str, lenPixels));
    	float partialCharWidth = lenPixels - getStringWidth(outStr);
    	if (partialCharWidth/f.getCharWidth(str.charAt(outStr.length())) > 0.5){
    		return outStr.length() + 1;
    	}
    	else{
    		return outStr.length();
    	}
    }
    
    
    /**
     * This is very similar to sizeStringToWidth, but it doesn't try to split on spaces
     */
    public static int sizeStringToWidthBlind(String str, int maxLenPixels){
    	if (getStringWidth(str) <= maxLenPixels){
    		return str.length();
    	}
    	String outStr = "";
    	for (int i=0; i<str.length(); i++){
    		if (getStringWidth(outStr + str.charAt(i)) > maxLenPixels){
    			return outStr.length();
    		}
    		outStr = outStr + str.charAt(i);
    	}
    	return outStr.length();
    }
	
	
	public String getTextWithWrappedFormatting(){
		return this.wrappedFormatting + this.text;
	}
	

    /**
     * Digests a string for nonprinting formatting characters then returns a string containing only that formatting.
     */
    private static String getFormatFromString(String par0Str){
        String s1 = "";
        int i = -1;
        int j = par0Str.length();

        while ((i = par0Str.indexOf(167, i + 1)) != -1){
            if (i < j - 1){
                char c0 = par0Str.charAt(i + 1);

                if (isFormatColor(c0)){
                    s1 = "\u00a7" + c0;
                }
                else if (isFormatSpecial(c0)){
                    s1 = s1 + "\u00a7" + c0;
                }
            }
        }
        return s1;
    }
    
    
    /**
     * Checks if the char code is O-K...lLrRk-o... used to set special formatting.
     */
    protected static boolean isFormatSpecial(char par0){
        return par0 >= 107 && par0 <= 111 || par0 >= 75 && par0 <= 79 || par0 == 114 || par0 == 82;
    }

	
	/**
     * Checks if the char code is a hexadecimal character, used to set colour.
     */
    protected static boolean isFormatColor(char par0){
        return par0 >= 48 && par0 <= 57 || par0 >= 97 && par0 <= 102 || par0 >= 65 && par0 <= 70;
    }
    
	
	/**
     * Determines how many characters from the string will fit into the specified width.
     * Note that this will try to split on newlines
     */
    public static int sizeStringToWidth(String par1Str, int par2){
    	int j = par1Str.length();
        int k = 0;
        int l = 0;
        int i1 = -1;

        for (boolean flag = false; l < j; ++l){
            char c0 = par1Str.charAt(l);

            switch (c0){
                case 10:
                    --l;
                    break;
                case 167:
                    if (l < j - 1){
                        ++l;
                        char c1 = par1Str.charAt(l);

                        if (c1 != 108 && c1 != 76){
                            if (c1 == 114 || c1 == 82 || isFormatColor(c1)){
                                flag = false;
                            }
                        }
                        else{
                            flag = true;
                        }
                    }

                    break;
                case 32:
                    i1 = l;
                default:
                    k += Minecraft.getMinecraft().fontRenderer.getCharWidth(c0);

                    if (flag){
                        ++k;
                    }
            }

            if (c0 == 10){
                ++l;
                i1 = l;
                break;
            }

            if (k > par2){
                break;
            }
        }

        return l != j && i1 != -1 && i1 < l ? i1 : l;
    }
	
	
    /**
     * Inserts SPLIT_CHAR into a string to wrap it within the specified width.
     * This preserves trailing whitespace (unlike FontRenderer.wrapStringToWidth())
     */
    private static String wrapStringToWidth(String strIn, int maxWidth, String wrappedFormatting){
        int maxCharsInWidth = sizeStringToWidth(wrappedFormatting + strIn, maxWidth) - wrappedFormatting.length();

        if (strIn.length() <= maxCharsInWidth){
            return strIn;
        }
        else{
        	//grab the most characters you can fit into maxWidth and put it in s1
            String s1 = strIn.substring(0, maxCharsInWidth);
            //grab the very next character after that string and put it in c0
            char c0 = strIn.charAt(maxCharsInWidth);
            boolean newlineOrSpace = c0 == 32 || c0 == 10; //Check if it's a newline character or a space
            String s2 = strIn.substring(maxCharsInWidth + (newlineOrSpace ? 1 : 0));
            if (newlineOrSpace){s1 += c0;}
            wrappedFormatting = getActiveFormatting(wrappedFormatting + s1);
            return s1 + SPLIT_CHAR + wrapStringToWidth(s2, maxWidth, wrappedFormatting);
        }
    }
    
    
	/**
	 * Splits the string into a list
	 * of 116 pixel wide strings (since that's the width of a book).
	 * Note 2: this preserves trailing whitespace (unlike FontRenderer.listFormattedStringToWidth())
	 */
	public static List<String> listFormattedStringToWidth(String str, String wrappedFormatting){
		return Arrays.asList(wrapStringToWidth(str, BOOK_TEXT_WIDTH, wrappedFormatting).split("" + SPLIT_CHAR));
	}
	
	
	/** Wrapper function that returns the formatting in effect at the end of this line */
	public String getActiveFormatting(){
		return getActiveFormatting(this.wrappedFormatting + this.text);
	}
	
	
	/** 
	 * Returns the formatting symbols that are in effect at the end of the line
	 * For example, if wrappedFormatting is "<bold>" and text is "abv<reset><green> def<italic>",
	 * this function will return "<green><italic>".
	 */
	private static String getActiveFormatting(String strIn){
		String activeFormatCode = "";
		String activeColorCode = ""; 
		for (int i=0; i<strIn.length()-1; i++){
			if (strIn.charAt(i) == '\u00a7'){
				char formatChar = strIn.charAt(i+1);
				//Check if it's the reset character
				if (formatChar == 'r' || formatChar == 'R'){
					activeFormatCode = "";
					activeColorCode = ""; 
				}
				//Check if it's a color code
				//Note that in a book, a color code cancels any active format codes
				else if ((formatChar >= 30 && formatChar <= 39) ||	//0-9
						(formatChar >= 65 && formatChar <= 70) ||	//A-F
						(formatChar >= 97 && formatChar <= 102)){	//a-f
					activeFormatCode = "";
					activeColorCode = "\u00a7" + formatChar;
				}
				//Check if it's a format character
				else if ((formatChar >= 75 && formatChar <= 79) || //K-O
						(formatChar >= 107 && formatChar <= 111)){
					activeFormatCode = "\u00a7" + formatChar;
				}
			}
		}
		return activeColorCode + activeFormatCode;
	}
	
	
    /**
     * Returns the width of this string. 
     * This is a wrapper for the same function name in FontRenderer. I had to do this because its 
     * behavior is inconsistent with the book display.
     * When typing in a book, the formatting characters are cancelled by any subsequent color codes.
     * This function inserts a reset before each color code to fix that.
     */
    public static int getStringWidth(String strIn)
    {
    	String unfucked = strIn.replaceAll("\u00a7[A-Fa-f0-9]", "\u00a7r$0");
    	return Minecraft.getMinecraft().fontRenderer.getStringWidth(unfucked);
    }
}
