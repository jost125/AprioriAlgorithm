package apriori.algorithm;

import apriori.input.CsvParser;
import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FrequentItemSetsCreaterTest {

	private FrequentItemSetsCreater frequentItemSetsCreater;
	private CsvTransactionListCreator csvTransactionListCreator;

	@Before
	public void setUp() {
		frequentItemSetsCreater = new FrequentItemSetsCreater(new CandidatesCreater());
		csvTransactionListCreator = new CsvTransactionListCreator(new CsvParser());
	}

	@Test
	public void testCreateItemSet() throws Exception {
		File file = getFile("stats_aggr_binom.csv");

		FrequentItemSets expResult = new FrequentItemSets();
		ItemSet itemSet1 = new ItemSet();
		itemSet1.add("Krasa");
		ItemSet itemSet2 = new ItemSet();
		itemSet2.add("Jidlo a piti");
		ItemSet itemSet3 = new ItemSet();
		itemSet3.add("Ostatni");

		expResult.add(new ItemSetWithSupport(itemSet1, 16));
		expResult.add(new ItemSetWithSupport(itemSet2, 19));
		expResult.add(new ItemSetWithSupport(itemSet3, 18));

		FrequentItemSets result = frequentItemSetsCreater.createItemSet(csvTransactionListCreator.createTransactions(file), 0.4);
		assertEquals(expResult, result);
	}
	
	private File getFile(String path) {
		return new File(getClass().getResource(path).getFile());
	}
}
