package de.cinovo.q.connector.demo;

import de.cinovo.q.connector.KXConnectorSync;
import de.cinovo.q.connector.KXTable;
import de.cinovo.q.connector.impl.KXConnectorFactory;
import de.cinovo.q.query.Select;
import de.cinovo.q.query.Select.Sort.Direction;
import de.cinovo.q.query.Table;
import de.cinovo.q.query.column.impl.FloatColumn;
import de.cinovo.q.query.column.impl.IntegerColumn;
import de.cinovo.q.query.column.impl.SymbolColumn;
import de.cinovo.q.query.column.impl.TimeColumn;
import de.cinovo.q.query.impl.TableImpl.TableBuilderImpl;
import de.cinovo.q.query.type.impl.TypeLong;
import de.cinovo.q.query.type.impl.TypeSymbol;

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
		// table definition
		final TimeColumn time = new TimeColumn("time");
		final SymbolColumn sym = new SymbolColumn("sym");
		final FloatColumn price = new FloatColumn("price");
		final IntegerColumn size = new IntegerColumn("size");
		final Table trade = new TableBuilderImpl("trade")
				.column(time)
				.column(sym)
				.column(price)
				.column(size)
				.build();

		// select query:
		// return all trades aggregated by symbol and per minute, sum size and calculate the average price where the symbol is AAA or BBB
		// select[>time] sum size,avg price by sym,60000j xbar time from trade where sym in (`AAA,`BBB)
		final Select select = trade.select()
			.column(size.sum())
			.column(price.avg())
			.group(sym.group())
			.group(time.xbar(TypeLong.from(60000L)))
			.filter(sym.filterIn(TypeSymbol.froms(new String[] {"AAA", "BBB"})))
			.order(Direction.descending, time)
			.build();
		System.out.println("select query: " + select.toQ());

		// connection to kdb+
		KXConnectorSync kx = KXConnectorFactory.create("localhost", 5011);
		kx.connect();
		final KXTable table = kx.select(select);
		System.out.println(table);
		kx.disconnect();
	}

	/** */
	private SelectQueryExample() {
		super();
	}
}
