package de.cinovo.q.query.type.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * TypeSymbol test.
 *
 * @author mwittig
 *
 */
public class TypeSymbolTest {

	/** */
	@Test
	public final void testNullQ() {
		assertEquals("`", TypeSymbol.from(null).toQ());
	}

	/** */
	@Test
	public final void testQ() {
		assertEquals("`FGBL032012", TypeSymbol.from("FGBL032012").toQ());
	}

}
