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
		CandidateSets expResult = new CandidateSets();
		ItemSet itemSet1 = new ItemSet();
		itemSet1.add("foo");
		ItemSet itemSet2 = new ItemSet();
		itemSet2.add("bar");
		ItemSet itemSet3 = new ItemSet();
		itemSet3.add("baz");
		ItemSet itemSet4 = new ItemSet();
		itemSet4.add("yellow");

		expResult.add(new ItemSetWithSupport(itemSet1, 1));
		expResult.add(new ItemSetWithSupport(itemSet2, 2));
		expResult.add(new ItemSetWithSupport(itemSet3, 1));
		expResult.add(new ItemSetWithSupport(itemSet4, 1));

		CandidateSets result = candidatesCreater.createCandidateSetsFromItems(transactionList, items);
		assertEquals(expResult, result);
	}

	@Test
	public void testCreateCandidateSetFromFrequentItemSets() {
		FrequentItemSets expResult = new FrequentItemSets();
		ItemSet expectedItemSet1 = new ItemSet();
		expectedItemSet1.add("foo");
		expectedItemSet1.add("bar");
		ItemSet expectedItemSet2 = new ItemSet();
		expectedItemSet2.add("foo");
		expectedItemSet2.add("baz");
		ItemSet expectedItemSet3 = new ItemSet();
		expectedItemSet3.add("foo");
		expectedItemSet3.add("yellow");
		ItemSet expectedItemSet4 = new ItemSet();
		expectedItemSet4.add("bar");
		expectedItemSet4.add("baz");
		ItemSet expectedItemSet5 = new ItemSet();
		expectedItemSet5.add("bar");
		expectedItemSet5.add("yellow");
		ItemSet expectedItemSet6 = new ItemSet();
		expectedItemSet6.add("baz");
		expectedItemSet6.add("yellow");

		expResult.add(new ItemSetWithSupport(expectedItemSet1, 1));
		expResult.add(new ItemSetWithSupport(expectedItemSet2, 0));
		expResult.add(new ItemSetWithSupport(expectedItemSet3, 0));
		expResult.add(new ItemSetWithSupport(expectedItemSet4, 0));
		expResult.add(new ItemSetWithSupport(expectedItemSet5, 1));
		expResult.add(new ItemSetWithSupport(expectedItemSet6, 0));

		FrequentItemSets frequentItemSets = new FrequentItemSets();
		ItemSet itemSet1 = new ItemSet();
		itemSet1.add("foo");
		ItemSet itemSet2 = new ItemSet();
		itemSet2.add("bar");
		ItemSet itemSet3 = new ItemSet();
		itemSet3.add("baz");
		ItemSet itemSet4 = new ItemSet();
		itemSet4.add("yellow");

		frequentItemSets.add(new ItemSetWithSupport(itemSet1, 1));
		frequentItemSets.add(new ItemSetWithSupport(itemSet2, 2));
		frequentItemSets.add(new ItemSetWithSupport(itemSet3, 1));
		frequentItemSets.add(new ItemSetWithSupport(itemSet4, 1));

		CandidateSets result = candidatesCreater.createCandidateSetsFromFrequentItemSets(transactionList, frequentItemSets);
		assertEquals(expResult, result);
	}
}
