package apriori.algorithm;

import java.util.HashSet;
import java.util.Set;

public class FrequentItemSets extends HashSet<ItemSetWithNumberOfOccurences> {

	public Set<ItemSet> getSetOfItemSet() {
		Set<ItemSet> converted = new HashSet<ItemSet>();
		for (ItemSetWithNumberOfOccurences itemSetWithNumberOfOccurences : this) {
			converted.add(itemSetWithNumberOfOccurences.getItemSet());
		}

		return converted;
	}
}
