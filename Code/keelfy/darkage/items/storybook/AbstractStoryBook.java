/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.items.storybook;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import keelfytools.KeelfyUtils;
import keelfytools.LocalizationUtils;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class AbstractStoryBook {

	public List<SBPage> pages = new ArrayList();
	public String title = "";
	public String author = "";
	public int id = 0;

	public static enum CursorDirection {
		UP, DOWN, LEFT, RIGHT
	}

	public int cursorPage = 0;
	public int cursorLine = 0;
	public int cursorPosChars = 0;
	private String dabFileName;

	public AbstractStoryBook() {}

	public int totalPages() {
		if (KeelfyUtils.isServerSide()) {
			return this.pages.size();
		}
		return 0;
	}

	public String getAuthor() {
		if (KeelfyUtils.isServerSide()) {
			return LocalizationUtils.withColors(author);
		}
		return StringUtils.EMPTY;
	}

	public String getTitle() {
		if (KeelfyUtils.isServerSide()) {
			return LocalizationUtils.withColors(title);
		}
		return StringUtils.EMPTY;
	}

	public static final NBTTagList writePagesToNBT(List<SBPage> pages) {
		if (KeelfyUtils.isServerSide()) {
			NBTTagList bookPages = new NBTTagList();
			List<NBTTagString> pagesL = new ArrayList(pages.size());

			for (SBPage page : pages) {
				StringBuilder sb = new StringBuilder();
				for (SBLine line : page.lines) {
					sb.append(LocalizationUtils.withColors(line.text));
				}
				pagesL.add(new NBTTagString(sb.toString()));
			}

			for (NBTTagString page : pagesL) {
				bookPages.appendTag(page);
			}
			return bookPages;
		}
		return new NBTTagList();
	}

	public void clear() {
		if (KeelfyUtils.isServerSide()) {
			this.pages.clear();
			this.title = "";
			this.author = "";
			this.cursorPage = 0;
			this.cursorLine = 0;
			this.cursorPosChars = 0;
		}
	}

	public void clone(AbstractStoryBook inBook) {
		if (KeelfyUtils.isServerSide()) {
			this.clear();
			this.title = inBook.title;
			this.author = inBook.author;
			SBPage currPage;
			SBLine currLine;
			for (SBPage inPage : inBook.pages) {
				this.pages.add(new SBPage());
				currPage = this.pages.get(this.pages.size() - 1);
				currPage.clear();
				for (SBLine inLine : inPage.lines) {
					currPage.lines.add(new SBLine());
					currLine = currPage.lines.get(currPage.lines.size() - 1);
					currLine.text = inLine.text;
					currLine.wrappedFormatting = inLine.wrappedFormatting;
				}
			}
		}
	}

	public void addText(int pageNum, int lineNum, int charPos, String strAdd, boolean setCursorAfterInsertedText) {
		if (KeelfyUtils.isServerSide()) {
			if (pageNum > this.totalPages()) {
				pageNum = this.totalPages();
			} else if (pageNum < 0) {
				pageNum = 0;
			}

			if (pageNum == this.totalPages()) {
				this.pages.add(new SBPage());
			}

			int charsBeforeCursor = charPos;
			SBPage currPage = this.pages.get(pageNum);
			for (int i = lineNum - 1; i >= 0; i--) {
				charsBeforeCursor += currPage.lines.get(i).text.length();
			}
			for (int i = pageNum - 1; i >= 0; i--) {
				charsBeforeCursor += this.pages.get(i).asString().length();
			}
			if (setCursorAfterInsertedText) {
				charsBeforeCursor += strAdd.length();
			}

			currPage = this.pages.get(pageNum);
			SBLine currLine = currPage.lines.get(lineNum);
			strAdd = currLine.text.substring(0, charPos) + strAdd + currLine.text.substring(charPos);
			if (lineNum > 0) {
				currPage.lines.remove(lineNum);
				lineNum -= 1;
				currLine = currPage.lines.get(lineNum);
				charPos = currLine.text.length();
			} else if (pageNum > 0) {
				currPage.lines.remove(lineNum);
				pageNum -= 1;
				currPage = this.pages.get(pageNum);
				lineNum = currPage.lines.size() - 1;
				currLine = currPage.lines.get(lineNum);
				charPos = currLine.text.length();
			} else {
				currLine.text = "";
				charPos = 0;
			}

			while (!strAdd.isEmpty()) {
				if (pageNum == this.totalPages()) {
					this.pages.add(new SBPage());
				}
				strAdd = this.pages.get(pageNum).addText(lineNum, charPos, strAdd);
				pageNum++;
				lineNum = 0;
				charPos = 0;
			}

			this.cursorPage = 0;
			currPage = this.pages.get(this.cursorPage);
			while (charsBeforeCursor > currPage.asString().length()) {
				charsBeforeCursor -= currPage.asString().length();
				this.cursorPage += 1;
				currPage = this.pages.get(this.cursorPage);
			}
			this.cursorLine = 0;
			currLine = currPage.lines.get(this.cursorLine);
			while (charsBeforeCursor > currLine.text.length()) {
				charsBeforeCursor -= currLine.text.length();
				this.cursorLine += 1;
				currLine = currPage.lines.get(this.cursorLine);
			}
			this.cursorPosChars = charsBeforeCursor;

			if (this.cursorPosChars > 0 && currLine.text.charAt(this.cursorPosChars - 1) == '\n') {
				if (this.cursorLine < currPage.lines.size() - 1) {
					this.cursorLine++;
					this.cursorPosChars = 0;
				} else if (this.cursorPage < this.totalPages() - 1) {
					this.cursorPage++;
					this.cursorLine = 0;
					this.cursorPosChars = 0;
				} else {
					this.cursorPosChars--;
				}
			}
		}
	}

	public void moveCursor(CursorDirection direction) {
		if (KeelfyUtils.isServerSide()) {
			SBPage currPage = this.pages.get(this.cursorPage);
			SBLine currLine = currPage.lines.get(this.cursorLine);
			int cursorPosPx = getCursorX();
			int cursorPosCharsWithFormat;
			switch (direction) {

			case UP:
				if (this.cursorLine == 0) {
					if (this.cursorPage > 0) {
						this.cursorPage--;
						currPage = this.pages.get(this.cursorPage);
						this.cursorLine = currPage.lines.size() - 1;
					} else {
						this.cursorPosChars = 0;
						return;
					}
				} else {
					if (cursorPosPx <= 0) {
						this.cursorPosChars = 0;
					}
					this.cursorLine--;
				}
				currLine = currPage.lines.get(this.cursorLine);
				cursorPosCharsWithFormat = SBLine
						.sizeStringToApproxWidthBlind(currLine.wrappedFormatting + currLine.text, cursorPosPx);
				this.cursorPosChars = cursorPosCharsWithFormat - currLine.wrappedFormatting.length();

				if (this.cursorPosChars > 0) {
					if (currLine.text.charAt(this.cursorPosChars - 1) == '\n') {
						this.cursorPosChars--;
					}
				}
				return;

			case DOWN:
				if (this.cursorLine == currPage.lines.size() - 1) {
					if (this.cursorPage < this.totalPages() - 1) {
						this.cursorPage++;
						this.cursorLine = 0;
					} else {
						currLine = currPage.lines.get(currPage.lines.size() - 1);
						this.cursorPosChars = currLine.text.length();
					}
				} else {
					if (cursorPosPx <= 0) {
						this.cursorPosChars = 0;
					}
					this.cursorLine++;
				}
				currLine = currPage.lines.get(this.cursorLine);
				currLine = this.pages.get(this.cursorPage).lines.get(this.cursorLine);
				cursorPosCharsWithFormat = SBLine
						.sizeStringToApproxWidthBlind(currLine.wrappedFormatting + currLine.text, cursorPosPx);
				this.cursorPosChars = cursorPosCharsWithFormat - currLine.wrappedFormatting.length();

				if (this.cursorPosChars > 0) {
					if (currLine.text.charAt(this.cursorPosChars - 1) == '\n') {
						this.cursorPosChars--;
					}
				}
				break;

			case LEFT:
				if (this.cursorPosChars > 0) {
					this.cursorPosChars--;
					if (this.cursorPosChars > 0 && currLine.text.charAt(this.cursorPosChars - 1) == '\u00a7') {
						this.cursorPosChars--;
					}
				} else {
					if (this.cursorLine > 0) {
						this.cursorLine--;
						this.cursorPosChars = getCurrLine().length() - 1;
					}
				}
				return;

			case RIGHT:
				int currLineLength = currLine.text.length();
				if (this.cursorPosChars < currLineLength && currLine.text.charAt(this.cursorPosChars) != '\n') {
					this.cursorPosChars++;
					if (currLine.text.charAt(this.cursorPosChars - 1) == '\u00a7'
							&& this.cursorPosChars < currLineLength - 1) {
						char nextChar = currLine.text.charAt(this.cursorPosChars);
						if (SBLine.isFormatSpecial(nextChar) || SBLine.isFormatColor(nextChar)) {
							this.cursorPosChars++;
						}
					}
				} else {
					if (this.cursorLine < this.pages.get(this.cursorPage).lines.size() - 1) {
						this.cursorPosChars = 0;
						this.cursorLine++;
					}
				}
				return;
			default:
				break;
			}
		}
	}

	public int getCursorX() {
		if (KeelfyUtils.isServerSide()) {
			if (this.pages.isEmpty()) {
				return 0;
			} else {
				if (this.cursorLine == 9) {
					boolean canary = true;
				}
				SBLine currLine = this.pages.get(this.cursorPage).lines.get(this.cursorLine);
				return SBLine.getStringWidth(currLine.getTextWithWrappedFormatting().substring(0,
						this.cursorPosChars + currLine.wrappedFormatting.length()));
			}
		}
		return 0;
	}

	public String getCurrLine() {
		if (KeelfyUtils.isServerSide()) {
			return this.pages.get(this.cursorPage).lines.get(this.cursorLine).text;
		}
		return StringUtils.EMPTY;
	}

	public String getCurrLineWithWrappedFormatting() {
		if (KeelfyUtils.isServerSide()) {
			return this.pages.get(this.cursorPage).lines.get(this.cursorLine).getTextWithWrappedFormatting();
		}
		return StringUtils.EMPTY;
	}

	public String getCurrPageAsMCString() {
		if (KeelfyUtils.isServerSide()) {
			if (this.cursorPage < 0) {
				this.cursorPage = 0;
				this.cursorLine = 0;
				this.cursorPosChars = 0;
			} else if (this.cursorPage >= totalPages()) {
				this.cursorPage = totalPages() - 1;
				if (this.cursorPage < 0) {
					this.cursorPage = 0;
				}
				SBPage lastPage = this.pages.get(this.cursorPage);
				this.cursorLine = lastPage.lines.size() - 1;
				if (this.cursorLine < 0) {
					this.cursorLine = 0;
				}
				SBLine lastLine = lastPage.lines.get(this.cursorLine);
				this.cursorPosChars = lastLine.text.length() - 1;
				if (this.cursorPosChars < 0) {
					this.cursorPosChars = 0;
				}
			}

			return this.pages.get(cursorPage).asString();
		}
		return StringUtils.EMPTY;
	}

	public boolean isEmpty() {
		if (KeelfyUtils.isServerSide()) {
			for (SBPage page : this.pages) {
				if (!page.isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}

	public static String removeFormatting(String strIn) {
		if (KeelfyUtils.isServerSide()) {
			return strIn.replaceAll("\u00a7[0-9a-fA-Fk-oK-OrR]?", "");
		}
		return StringUtils.EMPTY;
	}

	public String getDabFileName() {
		if (KeelfyUtils.isServerSide()) {
			return dabFileName;
		}
		return StringUtils.EMPTY;
	}

	public void setDabFileName(String dabFileName) {
		if (KeelfyUtils.isServerSide()) {
			this.dabFileName = dabFileName;
		}
	}
}
