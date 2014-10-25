package info.michaelwittig.javaq.query.impl;

import info.michaelwittig.javaq.query.Result;
import info.michaelwittig.javaq.query.Select.SelectBuilder;
import info.michaelwittig.javaq.query.Table;
import info.michaelwittig.javaq.query.TableResult;
import info.michaelwittig.javaq.query.TableRow;
import info.michaelwittig.javaq.query.column.Column;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Abstract table.
 * 
 * @author mwittig
 * 
 */
public abstract class ATable implements Table {
	
	/** Name. */
	private final String name;
	
	/** Columns. */
	private final Map<String, Column<?>> columns = new HashMap<String, Column<?>>();
	
	
	/**
	 * @param aName Name
	 */
	public ATable(final String aName) {
		this.name = aName;
	}
	
	@Override
	public final String getName() {
		return this.name;
	}
	
	/**
	 * @param column Column to add
	 */
	protected final void addColumn(final Column<?> column) {
		this.columns.put(column.getName(), column);
	}
	
	@Override
	public final Collection<Column<?>> getColumns() {
		return this.columns.values();
	}
	
	@Override
	public final String toQ() {
		return this.name;
	}
	
	@Override
	public final SelectBuilder select() {
		return new SelectImpl.SelectBuilderImpl(this);
	}
	
	@Override
	public final Iterable<TableRow> read(final Result result) {
		if (result instanceof TableResult) {
			return new Iterable<TableRow>() {
				
				@Override
				public Iterator<TableRow> iterator() {
					return new TableIterator((TableResult) result);
				}
			};
		}
		throw new IllegalArgumentException("Your result is not a table, its of type: " + result.getClass().getSimpleName());
	}
	
}
