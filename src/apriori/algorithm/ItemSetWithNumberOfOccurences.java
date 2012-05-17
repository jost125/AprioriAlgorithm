package apriori.algorithm;

public class ItemSetWithNumberOfOccurences {

	private ItemSet itemSet;
	private int numberOfOccurences;

	public ItemSetWithNumberOfOccurences(ItemSet itemSet, int numberOfOccurences) {
		this.itemSet = itemSet;
		this.numberOfOccurences = numberOfOccurences;
	}

	public int getNumberOfOccurences() {
		return numberOfOccurences;
	}

	public ItemSet getItemSet() {
		return itemSet;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ItemSetWithNumberOfOccurences other = (ItemSetWithNumberOfOccurences) obj;
		if (this.itemSet != other.itemSet && (this.itemSet == null || !this.itemSet.equals(other.itemSet))) {
			return false;
		}
		if (this.numberOfOccurences != other.numberOfOccurences) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + (this.itemSet != null ? this.itemSet.hashCode() : 0);
		hash = 13 * hash + this.numberOfOccurences;
		return hash;
	}

	@Override
	public String toString() {
		return itemSet.toString() + "(" + numberOfOccurences + ")";
	}

}
