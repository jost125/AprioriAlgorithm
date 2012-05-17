package apriori.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public class AssociationRulesCreator {

	private TransactionCreater transactionCreater;
	private FrequentItemSetsCreater frequentItemSetsCreater;

	public AssociationRulesCreator(TransactionCreater transactionCreater, FrequentItemSetsCreater frequentItemSetsCreater) {
		this.transactionCreater = transactionCreater;
		this.frequentItemSetsCreater = frequentItemSetsCreater;
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
				ItemSet supplement = new ItemSet();
				supplement.addAll(superSet);
				supplement.removeAll(subSet);

				int numberOfSuperSetOccurences =  frequentItemSet.getNumberOfOccurences();
				int numberOfSupplementOccurences = transactions.getNumberOfOccurences(supplement);

				double superSetSupport = (double)numberOfSuperSetOccurences / (double)numberOfTransactions;
				double supplementSupport = (double)numberOfSupplementOccurences / (double)numberOfTransactions;
				double confidence = superSetSupport / supplementSupport;

				if (confidence >= minConfidence) {
					AssociationRule associationRule = new AssociationRule(supplement, subSet, confidence, superSetSupport);
					associationRuleSet.add(associationRule);
				}
			}
		}

		return associationRuleSet;
	}
}
