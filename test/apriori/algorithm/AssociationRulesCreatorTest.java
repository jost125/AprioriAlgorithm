package apriori.algorithm;

import apriori.input.CsvParser;
import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AssociationRulesCreatorTest {

	AssociationRulesCreator associationRulesCreator;

	@Before
	public void setUp() {
		associationRulesCreator = new AssociationRulesCreator(
			new CsvTransactionListCreator(
				new CsvParser()
			),
			new FrequentItemSetsCreator(
				new CandidatesCreator()
			),
			new ConfidenceMetric()
		);
	}

	@Test
	public void testCreateAssociationRuleSet() throws Exception {
		File file = getFile("stats_aggr_binom.csv");
		double minSupport = 0.1;
		double minConfidence = 0.4;
		AssociationRuleSet result = associationRulesCreator.createAssociationRuleSet(file, minSupport, minConfidence);
		assertTrue(result.size() > 0);
	}

	private File getFile(String path) {
		return new File(getClass().getResource(path).getFile());
	}
}
