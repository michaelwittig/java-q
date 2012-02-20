package de.cinovo.q.query.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.cinovo.q.query.Select;
import de.cinovo.q.query.Table;
import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.column.impl.SymbolColumn;
import de.cinovo.q.query.column.impl.TimeColumn;
import de.cinovo.q.query.impl.SelectImpl.SelectBuilderImpl;
import de.cinovo.q.query.impl.TableImpl.TableBuilderImpl;
import de.cinovo.q.query.type.impl.TypeInteger;
import de.cinovo.q.query.type.impl.TypeSymbol;
import de.cinovo.q.query.type.impl.TypeTime;

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
		final Table table = new TableBuilderImpl("test")
			.column(utctime)
			.column(sym)
			.build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder
				.build();
		assertEquals("select from test", select.toQ());
	}

	/** */
	@Test
	public final void testWithColumns() {
		final Column<TypeTime> utctime = new TimeColumn("utctime");
		final Column<TypeSymbol> sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test")
			.column(utctime)
			.column(sym)
			.build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder
				.column(utctime)
				.column(sym)
				.build();
		assertEquals("select utctime,sym from test", select.toQ());
	}

	/** */
	@Test
	public final void testWithFilters() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test")
			.column(utctime)
			.column(sym)
			.build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder
				.filter(sym.filterEqualTo(TypeSymbol.from("TEST")))
				.build();
		assertEquals("select from test where sym=`TEST", select.toQ());
	}

	/** */
	@Test
	public final void testWithColumnsAndFilters() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test")
			.column(utctime)
			.column(sym)
			.build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder
				.column(utctime)
				.column(sym)
				.filter(sym.filterEqualTo(TypeSymbol.from("TEST")))
				.build();
		assertEquals("select utctime,sym from test where sym=`TEST", select.toQ());
	}

	/** */
	@Test
	public final void testWithColumnsGroupsAndFilter() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test")
			.column(utctime)
			.column(sym)
			.build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder
				.column(utctime)
				.group(sym.group())
				.filter(sym.filterEqualTo(TypeSymbol.from("TEST")))
				.build();
		assertEquals("select utctime by sym from test where sym=`TEST", select.toQ());
	}

	/** */
	@Test
	public final void testWithColumnsGroupsAndFilters() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test")
			.column(utctime)
			.column(sym)
			.build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder
				.column(utctime)
				.group(sym.group())
				.group(utctime.xbar(TypeInteger.from(1)))
				.filter(sym.filterEqualTo(TypeSymbol.from("TEST")))
				.build();
		assertEquals("select utctime by sym,1 xbar utctime from test where sym=`TEST", select.toQ());
	}

	/** */
	@Test
	public final void testWithGroup() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test")
			.column(utctime)
			.column(sym)
			.build();
		final SelectBuilderImpl builder = new SelectBuilderImpl(table);
		final Select select = builder
				.group(sym.group())
				.build();
		assertEquals("select by sym from test", select.toQ());
	}

}
