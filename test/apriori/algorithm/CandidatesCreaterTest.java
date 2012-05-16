package apriori.algorithm;

import org.junit.Test;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import static org.junit.Assert.*;

public class CandidatesCreaterTest {

	private CandidatesCreater candidatesCreater;
	private TransactionList transactionList;
	private Set<String> items;

	@Before
	public void setUp() {
		candidatesCreater = new CandidatesCreater();
		
		transactionList = new TransactionList();
		Transaction transaction1 = new Transaction();
		transaction1.add("foo");
		transaction1.add("bar");
		Transaction transaction2 = new Transaction();
		transaction2.add("baz");
		Transaction transaction3 = new Transaction();
		transaction3.add("bar");
		transaction3.add("yellow");

		transactionList.add(transaction1);
		transactionList.add(transaction2);
		transactionList.add(transaction3);

		items = new HashSet<String>();

		items.add("foo");
		items.add("bar");
		items.add("baz");
		items.add("yellow");
	}

	@Test
	public void testCreateCandidateSetFromItems() {
		CandidateSet expResult = new CandidateSet();
		ItemSet itemSet1 = new ItemSet();
		itemSet1.add("foo");
		ItemSet itemSet2 = new ItemSet();
		itemSet2.add("bar");
		ItemSet itemSet3 = new ItemSet();
		itemSet3.add("baz");
		ItemSet itemSet4 = new ItemSet();
		itemSet4.add("yellow");

		expResult.add(new Candidate(itemSet1, 1));
		expResult.add(new Candidate(itemSet2, 2));
		expResult.add(new Candidate(itemSet3, 1));
		expResult.add(new Candidate(itemSet4, 1));

		CandidateSet result = candidatesCreater.createCandidateSetFromItems(transactionList, items);
		assertEquals(expResult, result);
	}
}
