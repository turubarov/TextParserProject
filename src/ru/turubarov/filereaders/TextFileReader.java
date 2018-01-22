package ru.turubarov.filereaders;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextFileReader {
	public static String readFile(String fileName) throws IOException {
		return new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
	}

}
