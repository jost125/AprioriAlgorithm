package apriori.algorithm;

import java.util.ArrayList;
import java.util.Iterator;

public class TransactionList extends ArrayList<Transaction> {
	public ItemSet getItemList() {
		ItemSet itemSet = new ItemSet();
		
		Iterator<Transaction> iterator = iterator();
		while (iterator.hasNext()) {
			Transaction transaction = iterator.next();
			itemSet.addAll(transaction);
		}

		return itemSet;
	}
}
