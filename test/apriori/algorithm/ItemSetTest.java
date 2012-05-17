package apriori.algorithm;

import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemSetTest {

	ItemSet itemSet;

	@Before
	public void setUp() {
		itemSet = new ItemSet();
		itemSet.add("a");
		itemSet.add("b");
		itemSet.add("c");
		itemSet.add("d");
	}

	@Test
	public void testGetSubSets() {
		Set<ItemSet> subSets = itemSet.getSubSets();
		assertEquals(14, subSets.size());
	}
}
