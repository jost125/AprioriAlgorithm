package apriori.algorithm;

import org.junit.Before;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

public class TransactionListTest {

	TransactionList transactionList;

	@Before
	public void setUp() {
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
	}

	@Test
	public void testGetItemList() {
		Set<String> expResult = new HashSet<String>();
		expResult.add("foo");
		expResult.add("bar");
		expResult.add("baz");
		expResult.add("yellow");

		Set<String> result = transactionList.getItems();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetSupportMoreThenOne() {
		ItemSet itemSet = new ItemSet();
		itemSet.add("bar");
		assertEquals(2, transactionList.getNumberOfOccurences(itemSet));
	}

	@Test
	public void testGetSupportExactlyOne() {
		ItemSet itemSet = new ItemSet();
		itemSet.add("baz");
		assertEquals(1, transactionList.getNumberOfOccurences(itemSet));
	}

	@Test
	public void testGetSupportNone() {
		ItemSet itemSet = new ItemSet();
		itemSet.add("bleh");
		assertEquals(0, transactionList.getNumberOfOccurences(itemSet));
	}

	@Test
	public void testGetSupportBiggerSet() {
		ItemSet itemSet = new ItemSet();
		itemSet.add("bar");
		itemSet.add("foo");
		assertEquals(1, transactionList.getNumberOfOccurences(itemSet));
	}

}
