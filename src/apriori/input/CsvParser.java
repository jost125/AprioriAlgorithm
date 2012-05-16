package apriori.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvParser implements Parser {
	public List<List<String>> parse(File file) throws FileNotFoundException, IOException {
		BufferedReader bufferedReader = null;
		List<List<String>> lines = new ArrayList<List<String>>();

		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			for (String line = null; (line = bufferedReader.readLine()) != null; ) {
				lines.add(Arrays.asList(line.split(";")));
			}
		} finally {
			bufferedReader.close();
		}

		return lines;
	}
}
