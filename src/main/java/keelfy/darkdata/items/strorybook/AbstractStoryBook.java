/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items.strorybook;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import keelfyutils.KUtils;
import keelfyutils.client.KLocalization;
import keelfyutils.str.KString;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public final class AbstractStoryBook {

	private final List<SBPage> pages = new ArrayList();
	private String title = KString.EMPTY;
	private String author = KString.EMPTY;
	private int id = 0;

	public static enum CursorDirection {
		UP,
		DOWN,
		LEFT,
		RIGHT
	}

	public int cursorPage = 0;
	public int cursorLine = 0;
	public int cursorPosChars = 0;
	private String dabFileName;

	public AbstractStoryBook() {}

	public int totalPages() {
		if (KUtils.PROTECT_SERVER)
			return this.pages.size();
		return 0;
	}

	public String getAuthor() {
		if (KUtils.PROTECT_SERVER)
			return KLocalization.withColors(author);
		return StringUtils.EMPTY;
	}

	public final void setAuthor(final String author) {
		if (KUtils.PROTECT_SERVER) {
			this.author = author;
		}
	}

	public String getTitle() {
		if (KUtils.PROTECT_SERVER)
			return KLocalization.withColors(title);
		return StringUtils.EMPTY;
	}

	public final void setTitle(final String title) {
		if (KUtils.PROTECT_SERVER) {
			this.title = title;
		}
	}

	public List<SBPage> getPages() {
		return pages;
	}

	public int getId() {
		return id;
	}

	public final void setId(final int id) {
		if (KUtils.PROTECT_SERVER) {
			this.id = id;
		}
	}

	public final void fillNBT(final NBTTagCompound compound) {
		if (KUtils.PROTECT_SERVER) {
			compound.setTag("pages", writePagesToNBT());
			compound.setString("author", author);
			compound.setString("title", title);
		}
	}

	public final NBTTagList writePagesToNBT() {
		if (KUtils.PROTECT_SERVER) {
			NBTTagList bookPages = new NBTTagList();

			for (SBPage page : pages) {
				bookPages.appendTag(new NBTTagString(page.asString()));
			}

			return bookPages;
		}
		return null;
	}

	public static final List<SBPage> readPagesFromNBT(final NBTTagCompound compound) {
		NBTTagList bookPages = (NBTTagList) compound.getTag("pages");
		List<SBPage> pages = new ArrayList();

		for (int i = 0; i < bookPages.tagCount(); i++) {
			final SBPage page = new SBPage();
			page.addText(0, 0, bookPages.getStringTagAt(i));
			if (!page.isEmpty()) {
				pages.add(page);
			}
		}

		return pages;
	}

	public void clear() {
		if (KUtils.PROTECT_SERVER) {
			this.pages.clear();
			this.title = KString.EMPTY;
			this.author = KString.EMPTY;
			this.cursorPage = 0;
			this.cursorLine = 0;
			this.cursorPosChars = 0;
		}
	}

	public void clone(final AbstractStoryBook inBook) {
		if (KUtils.PROTECT_SERVER) {
			this.clear();
			this.title = inBook.getTitle();
			this.author = inBook.getAuthor();
			SBPage currPage;
			SBLine currLine;

			for (SBPage inPage : inBook.getPages()) {
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

	public void addText(int pageNum, int lineNum, int charPos, String strAdd) {
		if (KUtils.PROTECT_SERVER) {
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
				currLine.text = KString.EMPTY;
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

	public boolean isEmpty() {
		if (KUtils.PROTECT_SERVER) {
			for (SBPage page : this.pages) {
				if (!page.isEmpty())
					return false;
			}
		}
		return true;
	}

	public static String removeFormatting(final String strIn) {
		if (KUtils.PROTECT_SERVER)
			return strIn.replaceAll("\u00a7[0-9a-fA-Fk-oK-OrR]?", KString.EMPTY);
		return StringUtils.EMPTY;
	}

	public String getDabFileName() {
		if (KUtils.PROTECT_SERVER)
			return dabFileName;
		return StringUtils.EMPTY;
	}

	public void setDabFileName(final String dabFileName) {
		if (KUtils.PROTECT_SERVER) {
			this.dabFileName = dabFileName;
		}
	}
}
