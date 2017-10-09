/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.items.strorybook;

import java.util.ArrayList;
import java.util.List;

import keelfyutils.str.KString;

public final class SBPage {

	public List<SBLine> lines = new ArrayList();

	public SBPage() {
		lines.add(new SBLine());
	}

	public String addText(int lineNum, final int charPos, final String strAdd) {

		if (lineNum >= 13)
			return strAdd;

		if (lineNum < 0) {
			lineNum = 0;
		} else if (lineNum > this.lines.size()) {
			lineNum = this.lines.size();
		}

		if (lineNum == this.lines.size()) {
			this.lines.add(new SBLine());
		}

		SBLine currLine = this.lines.get(lineNum);

		if (lineNum > 0) {
			currLine.wrappedFormatting = this.lines.get(lineNum - 1).getActiveFormatting();
		} else {
			currLine.wrappedFormatting = KString.EMPTY;
		}

		String lineOverflow = currLine.addText(charPos, strAdd);

		int prevLinesCharCount = 0;
		for (int i = 0; i < lineNum; i++) {
			prevLinesCharCount += this.lines.get(i).text.length();
		}
		if (prevLinesCharCount + currLine.text.length() > 255) {
			String pageOverflow = KString.EMPTY;
			for (int i = this.lines.size() - 1; i > lineNum; i--) {
				pageOverflow = this.lines.get(i).text + pageOverflow;
				this.lines.remove(i);
			}

			int splitAt = 255 - prevLinesCharCount;
			if (currLine.text.charAt(splitAt) != ' ') {
				int spacePos = currLine.text.lastIndexOf(' ', splitAt);
				if (spacePos == -1) {
					splitAt = 0;
				} else {
					splitAt = spacePos;
				}
			}
			if (splitAt == 0) {
				pageOverflow = currLine.text + lineOverflow + pageOverflow;
				this.lines.remove(lineNum);
			} else {
				pageOverflow = currLine.text.substring(splitAt) + lineOverflow + pageOverflow;
				currLine.text = currLine.text.substring(0, splitAt);
			}

			return pageOverflow;
		}

		if (!lineOverflow.isEmpty())
			return this.addText(lineNum + 1, 0, lineOverflow);
		return KString.EMPTY;
	}

	public void clear() {
		this.lines.clear();
	}

	public static SBPage pad(final SBPage page) {
		SBLine newLine;
		SBLine lastLine = page.lines.get(page.lines.size() - 1);
		if (SBLine.getStringWidth(lastLine.wrappedFormatting + lastLine.text) < 116) {
			lastLine.text += "\n";
		}
		while (page.lines.size() < 13) {
			newLine = new SBLine();
			newLine.text = "\n";
			page.lines.add(newLine);
		}
		return page;
	}

	public String asString() {
		String out = KString.EMPTY;
		for (SBLine line : this.lines) {
			out += line.text;
		}
		return out;
	}

	public int charCount() {
		int total = 0;
		for (SBLine line : this.lines) {
			total += line.text.length();
		}
		return total;
	}

	public boolean isEmpty() {
		for (SBLine line : this.lines) {
			if (!line.text.isEmpty())
				return false;
		}
		return true;
	}
}
