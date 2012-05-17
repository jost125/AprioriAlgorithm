package apriori.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public class AssociationRulesCreator {

	private TransactionCreater transactionCreater;
	private FrequentItemSetsCreater frequentItemSetsCreater;
	private Metric metric;

	public AssociationRulesCreator(TransactionCreater transactionCreater, FrequentItemSetsCreater frequentItemSetsCreater, Metric metric) {
		this.transactionCreater = transactionCreater;
		this.frequentItemSetsCreater = frequentItemSetsCreater;
		this.metric = metric;
	}

	public AssociationRuleSet createAssociationRuleSet(File file, double minSupport, double minConfidence) throws FileNotFoundException, IOException {
		AssociationRuleSet associationRuleSet = new AssociationRuleSet();
		TransactionList transactions = transactionCreater.createTransactions(file);
		FrequentItemSets frequentItemSets = frequentItemSetsCreater.createItemSet(transactions, minSupport);

		int numberOfTransactions = transactions.size();

		for (ItemSetWithNumberOfOccurences frequentItemSet : frequentItemSets) {
			ItemSet superSet = frequentItemSet.getItemSet();
			Set<ItemSet> subSets = superSet.getSubSets();
			for (ItemSet subSet : subSets) {
				AssociationRule associationRule = metric.getAssociationRule(superSet, subSet, transactions, minConfidence);
				if (associationRule != null) {
					associationRuleSet.add(associationRule);
				}
			}
		}

		return associationRuleSet;
	}
}
