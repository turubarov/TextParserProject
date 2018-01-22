package ru.turubarov;

import ru.turubarov.filereaders.TextFileReader;
import ru.turubarov.filewriters.PdfFileWriter;
import ru.turubarov.filewriters.TextFileWriter;
import ru.turubarov.scanner.LexemTypes;
import ru.turubarov.scanner.Scanner;

public class TextParser {

	public static void main(String[] args) throws Exception {
		String input = TextFileReader.readFile("input/input.txt");
		StringBuilder garbageString = new StringBuilder();
		int pdfFileCount = 0;
		int textFileCount = 0;
		Scanner scanner = new Scanner(input);
		LexemTypes lexem = null;
		while (lexem != LexemTypes.END_OF_FILE) {
			lexem = scanner.scan();
			if (lexem == LexemTypes.XML_NODE) {
				TextFileWriter.writeFile("output/xmlFile" + textFileCount++ + ".txt", scanner.getLexem());
			} else if (lexem == LexemTypes.HTML_HODE) {
				PdfFileWriter.writeFile("output/htmlFile" + pdfFileCount++ + ".pdf", scanner.getLexem());
			} else {
				garbageString.append(scanner.getLexem());
			}
		}
		TextFileWriter.writeFile("output/garbage.txt", garbageString.toString());
	}

}
