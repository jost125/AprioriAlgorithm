package apriori.algorithm;

public class AssociationRule {
	private ItemSet fromItemSet;
	private ItemSet toItemSet;
	private double confidence;
	private double support;

	public AssociationRule(ItemSet fromItemSet, ItemSet toItemSet, double confidence, double support) {
		this.fromItemSet = fromItemSet;
		this.toItemSet = toItemSet;
		this.confidence = confidence;
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
		final AssociationRule other = (AssociationRule) obj;
		if (this.fromItemSet != other.fromItemSet && (this.fromItemSet == null || !this.fromItemSet.equals(other.fromItemSet))) {
			return false;
		}
		if (this.toItemSet != other.toItemSet && (this.toItemSet == null || !this.toItemSet.equals(other.toItemSet))) {
			return false;
		}
		if (Double.doubleToLongBits(this.confidence) != Double.doubleToLongBits(other.confidence)) {
			return false;
		}
		if (Double.doubleToLongBits(this.support) != Double.doubleToLongBits(other.support)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 47 * hash + (this.fromItemSet != null ? this.fromItemSet.hashCode() : 0);
		hash = 47 * hash + (this.toItemSet != null ? this.toItemSet.hashCode() : 0);
		hash = 47 * hash + (int) (Double.doubleToLongBits(this.confidence) ^ (Double.doubleToLongBits(this.confidence) >>> 32));
		hash = 47 * hash + (int) (Double.doubleToLongBits(this.support) ^ (Double.doubleToLongBits(this.support) >>> 32));
		return hash;
	}

	@Override
	public String toString() {
		return fromItemSet.toString() + " => " + toItemSet.toString() + "(confidence:" + String.format("%.3f", confidence) + ", support:" + String.format("%.3f", support) + ")";
	}

}
