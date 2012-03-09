package de.cinovo.q.connector.demo;

import de.cinovo.q.connector.KXConnectorSync;
import de.cinovo.q.connector.impl.KXConnectorFactory;
import de.cinovo.q.query.Result;
import de.cinovo.q.query.Select;
import de.cinovo.q.query.Select.Sort.Direction;
import de.cinovo.q.query.Table;
import de.cinovo.q.query.TableRow;
import de.cinovo.q.query.column.impl.FloatColumn;
import de.cinovo.q.query.column.impl.IntegerColumn;
import de.cinovo.q.query.column.impl.SymbolColumn;
import de.cinovo.q.query.column.impl.TimeColumn;
import de.cinovo.q.query.impl.TableImpl.TableBuilderImpl;
import de.cinovo.q.query.value.impl.LongValue;
import de.cinovo.q.query.value.impl.SymbolValue;

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
		final Table trade = new TableBuilderImpl("trade")
				.column(time)
				.column(sym)
				.column(price)
				.column(size)
				.build();

		// create select
		final Select select = trade.select()
			.column(size.sum())
			.column(price.avg())
			.group(sym.group())
			.group(time.xbar(LongValue.from(60000L)))
			.filter(sym.filterIn(SymbolValue.froms(new String[] {"AAA", "BBB"})))
			.order(Direction.descending, time)
			.build();
		System.out.println("Q: " + select.toQ());

		// connect to kdb+
		final KXConnectorSync kx = KXConnectorFactory.create("localhost", 5011);
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
