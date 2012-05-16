package apriori.algorithm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import apriori.input.CsvParser;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CsvTransactionListCreatorTest {

	private CsvTransactionListCreator csvTransactionListCreator;

	@Before
	public void setUp() {
		csvTransactionListCreator = new CsvTransactionListCreator(new CsvParser());
	}

	@Test
	public void testCreateTransactions() throws FileNotFoundException, IOException {
		File file = getFile("stats_aggr_binom.csv");
		TransactionList transactions = csvTransactionListCreator.createTransactions(file);
		Iterator<Transaction> iterator = transactions.iterator();

		String[] expected1 = {"Krasa"};
		String[] expected2 = {"Pobyty", "Jidlo a piti"};
		assertEquals(new HashSet(Arrays.asList(expected1)), iterator.next());
		assertEquals(new HashSet(Arrays.asList(expected2)), iterator.next());
		assertEquals(38, transactions.size());
	}

	private File getFile(String path) {
		return new File(getClass().getResource(path).getFile());
	}
}
