package apriori;

import apriori.algorithm.AssociationRule;
import apriori.algorithm.AssociationRuleSet;
import apriori.algorithm.AssociationRulesCreator;
import apriori.algorithm.CandidatesCreater;
import apriori.algorithm.ConfidenceMetric;
import apriori.algorithm.CsvTransactionListCreator;
import apriori.algorithm.FrequentItemSetsCreater;
import apriori.input.CsvParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.println("Usage: apriori.jar <file> <minSupport> <minConfidence>");
			System.exit(-1);
		}

		AssociationRulesCreator associationRulesCreator = new AssociationRulesCreator(
			new CsvTransactionListCreator(new CsvParser()),
			new FrequentItemSetsCreater(new CandidatesCreater()),
			new ConfidenceMetric()
		);
		AssociationRuleSet associationRuleSet = null;
		try {
			associationRuleSet = associationRulesCreator.createAssociationRuleSet(new File(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
		} catch (FileNotFoundException ex) {
			System.err.println("File not found.");
			System.exit(-1);
		} catch (IOException ex) {
			System.err.println("I/O exception.");
			System.exit(-1);
		} catch (NumberFormatException ex) {
			System.err.println("Cannot parse input.");
			System.exit(-1);
		}

		for (AssociationRule associationRule : associationRuleSet) {
			System.out.println(associationRule);
		}
	}

}
