package info.michaelwittig.javaq.demo;

import info.michaelwittig.javaq.connector.KXConnectorSync;
import info.michaelwittig.javaq.connector.impl.KXConnectorFactory;
import info.michaelwittig.javaq.query.Result;
import info.michaelwittig.javaq.query.Select;
import info.michaelwittig.javaq.query.Select.Sort.Direction;
import info.michaelwittig.javaq.query.Table;
import info.michaelwittig.javaq.query.TableRow;
import info.michaelwittig.javaq.query.column.impl.FloatColumn;
import info.michaelwittig.javaq.query.column.impl.IntegerColumn;
import info.michaelwittig.javaq.query.column.impl.SymbolColumn;
import info.michaelwittig.javaq.query.column.impl.TimeColumn;
import info.michaelwittig.javaq.query.impl.TableImpl.TableBuilderImpl;
import info.michaelwittig.javaq.query.value.impl.SymbolValue;
import info.michaelwittig.javaq.query.value.impl.TimeValue;

/**
 * Select query example.
 * 
 * @author mwittig
 * 
 */
public final class SelectQueryExample {
	
	/**
	 * @param args Arguments
	 * @throws Exception Of something went wrong
	 */
	public static void main(final String[] args) throws Exception {
		// dynamic table definition
		final TimeColumn time = new TimeColumn("time");
		final SymbolColumn sym = new SymbolColumn("sym");
		final FloatColumn price = new FloatColumn("price");
		final IntegerColumn size = new IntegerColumn("size");
		final Table trade = new TableBuilderImpl("trade").column(time).column(sym).column(price).column(size).build();
		
		// create select
		final Select select = trade.select().column(size.sum()).column(price.avg()).group(sym.group()).group(time.xbar(TimeValue.from(0, 1, 0, 0))).filter(sym.filterIn(SymbolValue.froms(new String[] {"AAA", "BBB"}))).order(Direction.descending, time).build();
		System.out.println("Q: " + select.toQ());
		
		// connect to kdb+
		final KXConnectorSync kx = KXConnectorFactory.create("localhost", 5011, true, false);
		kx.connect();
		
		// execute select and print the result
		final Result result = kx.select(select);
		for (final TableRow row : trade.read(result)) {
			System.out.println(row.get(time));
			System.out.println(row.get(sym));
			System.out.println(row.get(price));
			System.out.println(row.get(size));
		}
		
		// close connection
		kx.disconnect();
	}
	
	/** */
	private SelectQueryExample() {
		super();
	}
}
