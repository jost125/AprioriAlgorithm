package apriori;

import apriori.algorithm.AssociationRule;
import apriori.algorithm.AssociationRuleSet;
import apriori.algorithm.AssociationRulesCreator;
import apriori.algorithm.CandidatesCreater;
import apriori.algorithm.ConfidenceMetric;
import apriori.algorithm.CsvTransactionListCreator;
import apriori.algorithm.FrequentItemSetsCreater;
import apriori.algorithm.LiftMetric;
import apriori.algorithm.Metric;
import apriori.input.CsvParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Main {

	public static void main(String[] args) {
		if (args.length != 3 && args.length != 4) {
			usage();
		}

		String metricName = "";
		if (args.length == 4) {
			metricName = args[3];
		} else {
			metricName = "conf";
		}

		Metric metric = null;
		if (metricName.equals("conf")) {
			metric = new ConfidenceMetric();
		} else if (metricName.equals("lift")) {
			metric = new LiftMetric();
		} else if (metricName.equals("conv")) {
			throw new NotImplementedException();
		} else {
			usage();
		}

		AssociationRulesCreator associationRulesCreator = new AssociationRulesCreator(
			new CsvTransactionListCreator(new CsvParser()),
			new FrequentItemSetsCreater(new CandidatesCreater()),
			metric
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
			System.out.println(
				associationRule.getFromItemSet().toString() + " => "
				+ associationRule.getToItemSet().toString()
				+ " " + metricName + ": " + String.format("%.3f", associationRule.getMatricValue())
				+ ", support:" + String.format("%.3f", associationRule.getSupport())
			);
		}
	}

	public static void usage() {
		System.err.println("Usage: apriori.jar <file> <minSupport> <minMatricValue> [conf|lift|conv]");
		System.exit(-1);
	}

}
