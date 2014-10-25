package info.michaelwittig.javaq.query.impl;

import info.michaelwittig.javaq.query.Table;
import info.michaelwittig.javaq.query.column.Column;

/**
 * Table implementation.
 * 
 * @author mwittig
 * 
 */
public final class TableImpl extends ATable {
	
	/**
	 * @param aName Name
	 */
	private TableImpl(final String aName) {
		super(aName);
	}
	
	
	/**
	 * Table builder implementation.
	 * 
	 * @author mwittig
	 * 
	 */
	public static final class TableBuilderImpl implements TableBuilder {
		
		/** Table. */
		private final TableImpl table;
		
		
		/**
		 * @param aName Name
		 */
		public TableBuilderImpl(final String aName) {
			this.table = new TableImpl(aName);
		}
		
		@Override
		public TableBuilderImpl column(final Column<?> column) {
			this.table.addColumn(column);
			return this;
		}
		
		@Override
		public Table build() {
			return this.table;
		}
	}
	
}
