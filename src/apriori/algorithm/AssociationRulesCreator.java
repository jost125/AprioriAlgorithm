package apriori.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public class AssociationRulesCreator {

	private TransactionCreater transactionCreater;
	private FrequentItemSetsCreator frequentItemSetsCreater;
	private Metric metric;

	public AssociationRulesCreator(TransactionCreater transactionCreater, FrequentItemSetsCreator frequentItemSetsCreater, Metric metric) {
		this.transactionCreater = transactionCreater;
		this.frequentItemSetsCreater = frequentItemSetsCreater;
		this.metric = metric;
	}

	public AssociationRuleSet createAssociationRuleSet(File file, double minSupport, double minMetricValue) throws FileNotFoundException, IOException {
		AssociationRuleSet associationRuleSet = new AssociationRuleSet();
		TransactionList transactions = transactionCreater.createTransactions(file);
		FrequentItemSets frequentItemSets = frequentItemSetsCreater.createItemSet(transactions, minSupport);

		for (ItemSetWithNumberOfOccurences frequentItemSet : frequentItemSets) {
			ItemSet superSet = frequentItemSet.getItemSet();
			Set<ItemSet> subSets = superSet.getSubSets();
			for (ItemSet subSet : subSets) {
				AssociationRule associationRule = metric.getAssociationRule(superSet, subSet, transactions, minMetricValue);
				if (associationRule != null) {
					associationRuleSet.add(associationRule);
				}
			}
		}

		return associationRuleSet;
	}
}
