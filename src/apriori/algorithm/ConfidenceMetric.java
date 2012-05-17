package apriori.algorithm;

public class ConfidenceMetric extends Metric {

	@Override
	public AssociationRule getAssociationRule(ItemSet superSet, ItemSet subSet, TransactionList transactionList, double minConfidence) {
		ItemSet supplement = getSupplement(superSet, subSet);

		double superSetSupport = getSetSupport(superSet, transactionList);
		double supplementSetSupport = getSetSupport(supplement, transactionList);

		double confidence = superSetSupport / supplementSetSupport;

		AssociationRule associationRule = null;
		if (confidence >= minConfidence) {
			associationRule = new AssociationRule(supplement, subSet, confidence, superSetSupport);
		}

		return associationRule;

	}
	
}
