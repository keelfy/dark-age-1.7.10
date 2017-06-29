/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.items.storybook;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import keelfytools.KeelfyUtils;

public class SBPage {

	public List<SBLine> lines = new ArrayList();

	public SBPage() {
		if (KeelfyUtils.isServerSide()) {
			lines.add(new SBLine());
		}
	}

	public String addText(int lineNum, int charPos, String strAdd) {
		if (KeelfyUtils.isServerSide()) {

			if (lineNum >= 13) {
				return strAdd;
			}

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
				currLine.wrappedFormatting = "";
			}

			String lineOverflow = currLine.addText(charPos, strAdd);

			int prevLinesCharCount = 0;
			for (int i = 0; i < lineNum; i++) {
				prevLinesCharCount += this.lines.get(i).text.length();
			}
			if (prevLinesCharCount + currLine.text.length() > 255) {
				String pageOverflow = "";
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

			if (!lineOverflow.isEmpty()) {
				return this.addText(lineNum + 1, 0, lineOverflow);
			} else {
				return "";
			}
		}
		return StringUtils.EMPTY;
	}

	public void clear() {
		if (KeelfyUtils.isServerSide()) {
			this.lines.clear();
		}
	}

	public static SBPage pad(SBPage page) {
		if (KeelfyUtils.isServerSide()) {
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
		return null;
	}

	public String asString() {
		if (KeelfyUtils.isServerSide()) {
			String out = "";
			for (SBLine line : this.lines) {
				out += line.text;
			}
			return out;
		}
		return StringUtils.EMPTY;
	}

	public int charCount() {
		if (KeelfyUtils.isServerSide()) {
			int total = 0;
			for (SBLine line : this.lines) {
				total += line.text.length();
			}
			return total;
		}
		return 0;
	}

	public boolean isEmpty() {
		if (KeelfyUtils.isServerSide()) {
			for (SBLine line : this.lines) {
				if (!line.text.isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
}
