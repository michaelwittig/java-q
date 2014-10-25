package info.michaelwittig.javaq.query.impl;

import info.michaelwittig.javaq.query.TableResult;
import info.michaelwittig.javaq.query.TableRow;

import java.util.Iterator;

/**
 * Table reader.
 * 
 * @author mwittig
 * 
 */
public final class TableIterator implements Iterator<TableRow> {
	
	/** Flip result. */
	private final TableResult table;
	
	/** Current index. (-1 := not yet started) */
	private int currentI = -1;
	
	
	/**
	 * @param aTable Table
	 */
	public TableIterator(final TableResult aTable) {
		this.table = aTable;
	}
	
	@Override
	public boolean hasNext() {
		if ((this.currentI + 1) < this.table.getRows()) {
			return true;
		}
		return false;
	}
	
	@Override
	public TableRow next() {
		this.currentI++;
		return new TableRowImpl(this.table, this.currentI);
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
}
