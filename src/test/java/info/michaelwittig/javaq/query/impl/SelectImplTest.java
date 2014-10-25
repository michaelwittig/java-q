package info.michaelwittig.javaq.query.impl;

import info.michaelwittig.javaq.query.Select;
import info.michaelwittig.javaq.query.Table;
import info.michaelwittig.javaq.query.column.Column;
import info.michaelwittig.javaq.query.column.impl.SymbolColumn;
import info.michaelwittig.javaq.query.column.impl.TimeColumn;
import info.michaelwittig.javaq.query.impl.SelectImpl.SelectBuilderImpl;
import info.michaelwittig.javaq.query.impl.TableImpl.TableBuilderImpl;
import info.michaelwittig.javaq.query.type.impl.TypeSymbol;
import info.michaelwittig.javaq.query.type.impl.TypeTime;
import info.michaelwittig.javaq.query.value.impl.SymbolValue;
import info.michaelwittig.javaq.query.value.impl.TimeValue;

import org.junit.Assert;
import org.junit.Test;

/**
 * SelectImpl test.
 * 
 * @author mwittig
 * 
 */
public class SelectImplTest {
	
	/** */
	@Test
	public final void testPlain() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test").column(utctime).column(sym).build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder.build();
		Assert.assertEquals("select from test", select.toQ());
	}
	
	/** */
	@Test
	public final void testWithColumns() {
		final Column<TypeTime> utctime = new TimeColumn("utctime");
		final Column<TypeSymbol> sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test").column(utctime).column(sym).build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder.column(utctime).column(sym).build();
		Assert.assertEquals("select utctime,sym from test", select.toQ());
	}
	
	/** */
	@Test
	public final void testWithFilters() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test").column(utctime).column(sym).build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder.filter(sym.filterEqualTo(SymbolValue.from("TEST"))).build();
		Assert.assertEquals("select from test where sym=`TEST", select.toQ());
	}
	
	/** */
	@Test
	public final void testWithColumnsAndFilters() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test").column(utctime).column(sym).build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder.column(utctime).column(sym).filter(sym.filterEqualTo(SymbolValue.from("TEST"))).build();
		Assert.assertEquals("select utctime,sym from test where sym=`TEST", select.toQ());
	}
	
	/** */
	@Test
	public final void testWithColumnsGroupsAndFilter() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test").column(utctime).column(sym).build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder.column(utctime).group(sym.group()).filter(sym.filterEqualTo(SymbolValue.from("TEST"))).build();
		Assert.assertEquals("select utctime by sym from test where sym=`TEST", select.toQ());
	}
	
	/** */
	@Test
	public final void testWithColumnsGroupsAndFilters() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test").column(utctime).column(sym).build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder.column(utctime).group(sym.group()).group(utctime.xbar(TimeValue.from(0, 0, 0, 1))).filter(sym.filterEqualTo(SymbolValue.from("TEST"))).build();
		Assert.assertEquals("select utctime by sym,00:00:00.001 xbar utctime from test where sym=`TEST", select.toQ());
	}
	
	/** */
	@Test
	public final void testWithGroup() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test").column(utctime).column(sym).build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder.group(sym.group()).build();
		Assert.assertEquals("select by sym from test", select.toQ());
	}
	
	/** */
	@Test
	public final void testWithVirtualColumn() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test").column(utctime).column(sym).build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder.column(sym.virtual("sym2")).build();
		Assert.assertEquals("select sym2:sym from test", select.toQ());
	}
	
	/** */
	@Test
	public final void testWithGroupAndAggregation() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test").column(utctime).column(sym).build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder.column(sym.last().virtual("sym2")).column(utctime.min()).group(sym.group()).build();
		Assert.assertEquals("select sym2:last sym,min utctime by sym from test", select.toQ());
	}
	
}
