package apriori.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface TransactionCreater {
	public TransactionList createTransactions(File file) throws FileNotFoundException, IOException;
}
