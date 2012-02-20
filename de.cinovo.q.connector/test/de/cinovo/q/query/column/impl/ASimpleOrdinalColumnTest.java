package de.cinovo.q.query.column.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * ASimpleOrdinalColumn test.
 *
 * @author mwittig
 *
 */
public class ASimpleOrdinalColumnTest {

	/** */
	@Test
	public final void test() {
		final IntegerColumn col = new IntegerColumn("test");
		assertEquals("test", col.toQ());
	}

}
