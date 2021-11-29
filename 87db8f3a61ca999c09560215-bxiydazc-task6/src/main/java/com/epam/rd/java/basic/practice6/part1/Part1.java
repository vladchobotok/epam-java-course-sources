package com.epam.rd.java.basic.practice6.part1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;

public class Part1 {

	private Part1() {
	}

	public static void main(String[] args) {
		String string = "asd\n43\nasdf\nasd\n43\n434\nasdf\nkasdf\nasdf\nstop\nasdf\nstop";
		wordCounter(new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8)), System.out);
	}

	public static void wordCounter(InputStream in, PrintStream out) {
		Scanner scanner = new Scanner(in);
		WordContainer wordContainer = new WordContainer(new Word.CompareByWord());
		while (scanner.hasNext()) {
			String word = scanner.next();
			if ("stop".equals(word)) {
				break;
			}

			wordContainer.add(new Word(word));
		}

		Iterator<Word> iterator = wordContainer.frequencyIterator();
		while (iterator.hasNext()) {
			Word w = iterator.next();

			out.printf("%s : %s%n", w.getValue(), w.getFrequency());
		}
	}

}
