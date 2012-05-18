package apriori.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ItemSet extends TreeSet<String> {
	public Set<ItemSet> getSubSets() {
		Set<ItemSet> subsets = powerSet(this);
		subsets.remove(new ItemSet());
		subsets.remove(this);
		return subsets;
	}

	public Set<ItemSet> getSubSetsOfSize(int subSetSize) {
		Set<ItemSet> subsets = powerSet(this);
	
		Iterator<ItemSet> iterator = subsets.iterator();
		while (iterator.hasNext()) {
			ItemSet current = iterator.next();
			if (current.size() != subSetSize) {
				iterator.remove();
			}
		}

		return subsets;
	}

	private Set<ItemSet> powerSet(Set<String> originalSet) {
		Set<ItemSet> subsets = new HashSet<ItemSet>();
		if (originalSet.isEmpty()) {
			subsets.add(new ItemSet());
			return subsets;
		}
		List<String> list = new ArrayList<String>(originalSet);
		String head = list.get(0);
		Set<String> rest = new HashSet<String>(list.subList(1, list.size()));
		for (ItemSet set : powerSet(rest)) {
			ItemSet newSet = new ItemSet();
			newSet.add(head);
			newSet.addAll(set);
			subsets.add(newSet);
			subsets.add(set);
		}
		return subsets;
	}
}
