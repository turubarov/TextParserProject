package ru.turubarov.scanner;

public class Scanner {
	private LexemTypes type;
	private StringBuilder lexem;
	private String inputString;
	private int curIndex;
	private int inputStringLenght;

	private final String HTML_TAG = "html";
	private final String XML_TAG = "xml";

	public Scanner(String inputString) {
		this.inputString = inputString;
		inputStringLenght = this.inputString.length();
	}

	public LexemTypes scan() {
		lexem = new StringBuilder();
		skipInvisibleSymbols();
		if (curIndex >= inputStringLenght)
			return LexemTypes.END_OF_FILE;
		if (inputString.startsWith("<" + HTML_TAG + ">", curIndex)) {
			scanTag(HTML_TAG);
		} else if (inputString.startsWith("<" + XML_TAG + ">", curIndex)) {
			scanTag(XML_TAG);
		} else {
			scanGarbage();
		}
		return type;
	}

	private void skipInvisibleSymbols() {
		while (curIndex < inputStringLenght
				&& (inputString.charAt(curIndex) == '\n'
						|| inputString.charAt(curIndex) == '\t' || inputString
						.charAt(curIndex) == '\r'))
			curIndex++;
	}

	private boolean scanGarbage() {
		String openHtmlTag = "<" + HTML_TAG + ">";
		String openXmlTag = "<" + XML_TAG + ">";
		while (!inputString.startsWith(openHtmlTag, curIndex)
				&& !inputString.startsWith(openXmlTag, curIndex)
				&& curIndex < inputStringLenght) {
			lexem.append(inputString.charAt(curIndex++));
		}
		type = LexemTypes.GARBAGE;
		return curIndex == inputStringLenght;
	}

	private boolean scanTag(String tag) {
		String openTag = "<" + tag + ">";
		String closeTag = "</" + tag + ">";
		int stackIndex = 0;
		if (inputString.startsWith(openTag, curIndex)) {
			stackIndex++;
			lexem.append(openTag);
			curIndex += openTag.length();
			while (stackIndex != 0 && curIndex < inputStringLenght) {
				if (inputString.startsWith(openTag, curIndex)) {
					lexem.append(openTag);
					curIndex += openTag.length();
					stackIndex++;
				} else if (inputString.startsWith(closeTag, curIndex)) {
					lexem.append(closeTag);
					curIndex += closeTag.length();
					stackIndex--;
				} else {
					lexem.append(inputString.charAt(curIndex++));
				}
			}
		}
		if (stackIndex != 0) {
			type = LexemTypes.GARBAGE;
		} else {
			if (HTML_TAG.equals(tag)) {
				type = LexemTypes.HTML_HODE;
			} else if (XML_TAG.equals(tag)) {
				type = LexemTypes.XML_NODE;
			} else {
				type = LexemTypes.GARBAGE;
			}
		}
		return curIndex == inputStringLenght;
	}

	public LexemTypes getType() {
		return type;
	}

	public String getLexem() {
		return lexem.toString();
	}

	public void setInputString(String inputString) {
		this.inputString = inputString;
	}
}
