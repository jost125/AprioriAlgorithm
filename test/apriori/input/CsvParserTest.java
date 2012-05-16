package apriori.input;

import java.io.File;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CsvParserTest {

	private CsvParser csvParser;

	@Before
	public void setUp() {
		csvParser = new CsvParser();
	}

	@Test
	public void testParse() throws Exception {
		File file = getFile("stats_aggr_binom.csv");
		List<List<String>> result = csvParser.parse(file);
		assertEquals(39, result.size());
		assertEquals(7, result.get(0).size());
	}

	private File getFile(String path) {
		return new File(getClass().getResource(path).getFile());
	}
}
