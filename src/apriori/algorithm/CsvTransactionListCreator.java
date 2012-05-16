package apriori.algorithm;

import apriori.input.CsvParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvTransactionListCreator implements TransactionCreater {

	private CsvParser parser;

	public CsvTransactionListCreator(CsvParser parser) {
		this.parser = parser;
	}

	public TransactionList createTransactions(File file) throws FileNotFoundException, IOException {
		List<List<String>> lines = parser.parse(file);
		TransactionList transactions = new TransactionList();

		Iterator<List<String>> iterator = lines.iterator();
		List<String> head = iterator.next();

		while (iterator.hasNext()) {
			List<String> values = iterator.next();
			Transaction transaction = new Transaction();
			
			for (int i = 0; i < values.size(); i++) {
				String value = values.get(i);
				if (Integer.parseInt(value) == 1) {
					transaction.add(head.get(i));
				}
			}

			transactions.add(transaction);
		}

		return transactions;
	}
	
}
