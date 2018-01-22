package ru.turubarov.filewriters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFileWriter {

	public static void writeFile(String fileName, String text) throws Exception {
		Path path = Paths.get(fileName);
		Files.write(path, text.getBytes());
	}

}
