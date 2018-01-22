package ru.turubarov.filewriters;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfFileWriter {
	public static void writeFile(String fileName, String text) throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document,
				new FileOutputStream(new File(fileName)));
		document.open();
		document.add(new Paragraph(text));
		document.close();
	}

}
