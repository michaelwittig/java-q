package de.cinovo.q.query.column.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * ASimpleNominalColumn test.
 *
 * @author mwittig
 *
 */
public class ASimpleNominalColumnTest {

	/** */
	@Test
	public final void test() {
		final SymbolColumn col = new SymbolColumn("test");
		assertEquals("test", col.toQ());
	}

}
