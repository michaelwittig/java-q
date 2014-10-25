package info.michaelwittig.javaq.query.impl;

import info.michaelwittig.javaq.query.Table;
import info.michaelwittig.javaq.query.column.impl.SymbolColumn;
import info.michaelwittig.javaq.query.column.impl.TimeColumn;
import info.michaelwittig.javaq.query.impl.TableImpl.TableBuilderImpl;

import org.junit.Assert;
import org.junit.Test;

/**
 * TableImpl test.
 * 
 * @author mwittig
 * 
 */
public class TableImplTest {
	
	/** */
	@Test
	public final void test() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test").column(utctime).column(sym).build();
		Assert.assertEquals("test", table.toQ());
	}
	
}
