package de.cinovo.q.query.impl;

import de.cinovo.q.query.TableResult;
import de.cinovo.q.query.TableRow;
import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.value.Value;

/**
 * Table row implementation.
 *
 * @author mwittig
 *
 */
public final class TableRowImpl implements TableRow {

	/** Table result. */
	private final TableResult table;

	/** Index.  */
	private final int i;

	/**
	 * @param aTable Table result
	 * @param anI Index
	 */
	public TableRowImpl(final TableResult aTable, final int anI) {
		this.table = aTable;
		this.i = anI;
	}

	@Override
	public <J, T extends Type<J>> Value<J, T> get(final Column<T> column) {
		 return (Value<J, T>) column.getType().geValueFactory().fromQ(this.table.getAt(column, this.i));
	}

}
