package apriori.algorithm;

import java.io.File;
import java.util.Set;

public class ItemSetCreater {

	private TransactionCreater transactionCreater;

	public ItemSetCreater(TransactionCreater transactionCreater) {
		this.transactionCreater = transactionCreater;
	}

	public Set<Set<String>> createItemSet(File file) {
		return null;
	}
}
