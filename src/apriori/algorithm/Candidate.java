package apriori.algorithm;

public class Candidate {

	private ItemSet itemSet;
	private int support;

	public Candidate(ItemSet itemSet, int support) {
		this.itemSet = itemSet;
		this.support = support;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Candidate other = (Candidate) obj;
		if (this.itemSet != other.itemSet && (this.itemSet == null || !this.itemSet.equals(other.itemSet))) {
			return false;
		}
		if (this.support != other.support) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + (this.itemSet != null ? this.itemSet.hashCode() : 0);
		hash = 13 * hash + this.support;
		return hash;
	}

}
