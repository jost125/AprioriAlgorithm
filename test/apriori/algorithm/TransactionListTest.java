package apriori.algorithm;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

public class TransactionListTest {

	@Test
	public void testGetItemList() {
		TransactionList transactionList = new TransactionList();
		
		Transaction transaction1 = new Transaction();
		transaction1.add("foo");
		transaction1.add("bar");
		Transaction transaction2 = new Transaction();
		transaction1.add("baz");
		Transaction transaction3 = new Transaction();
		transaction1.add("bar");
		transaction1.add("yellow");

		transactionList.add(transaction1);
		transactionList.add(transaction2);
		transactionList.add(transaction3);

		Set<String> expResult = new HashSet<String>();
		expResult.add("foo");
		expResult.add("bar");
		expResult.add("baz");
		expResult.add("yellow");

		Set<String> result = transactionList.getItems();
		assertEquals(expResult, result);
	}
}
