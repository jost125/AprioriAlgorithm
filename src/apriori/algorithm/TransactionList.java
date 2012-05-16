package apriori.algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class TransactionList extends ArrayList<Transaction> {
	public Set<String> getItems() {
		Set<String> items = new ItemSet();
		
		Iterator<Transaction> iterator = iterator();
		while (iterator.hasNext()) {
			Transaction transaction = iterator.next();
			items.addAll(transaction);
		}

		return items;
	}

	public int getNumberOfOccurences(ItemSet itemSet) {
		int support = 0;
		Iterator<Transaction> iterator = iterator();
		while (iterator.hasNext()) {
			Transaction transaction = iterator.next();
			if (transaction.containsAll(itemSet)) {
				support++;
			}
		}

		return support;
	}
}
