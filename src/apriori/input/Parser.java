package apriori.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Parser {
	public List<List<String>> parse(File file) throws FileNotFoundException, IOException;
}
