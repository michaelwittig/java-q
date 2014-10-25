package info.michaelwittig.javaq.query.impl;

import info.michaelwittig.javaq.query.TableResult;
import info.michaelwittig.javaq.query.TableRow;
import info.michaelwittig.javaq.query.column.Column;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.value.Value;

/**
 * Table row implementation.
 * 
 * @author mwittig
 * 
 */
public final class TableRowImpl implements TableRow {
	
	/** Table result. */
	private final TableResult table;
	
	/** Index. */
	private final int i;
	
	
	/**
	 * @param aTable Table result
	 * @param anI Index
	 */
	public TableRowImpl(final TableResult aTable, final int anI) {
		this.table = aTable;
		this.i = anI;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <J, T extends Type<J>> Value<J, T> get(final Column<T> column) {
		return (Value<J, T>) column.getType().geValueFactory().fromQ(this.table.getAt(column, this.i));
	}
	
}
