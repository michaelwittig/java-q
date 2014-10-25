package info.michaelwittig.javaq.query;

import info.michaelwittig.javaq.query.column.Column;

/**
 * Table result.
 * 
 * @author mwittig
 * 
 */
public interface TableResult extends Result {
	
	/**
	 * @return Number of rows
	 */
	int getRows();
	
	/**
	 * @return Number of columns
	 */
	int getCols();
	
	/**
	 * @param col Column
	 * @param row Row index
	 * @return Object or null
	 */
	Object getAt(final Column<?> col, final int row);
	
	/**
	 * @param colName Column name
	 * @param row Row index
	 * @return Object or null
	 */
	Object getAt(final String colName, final int row);
	
	/**
	 * @param colIndex Column index
	 * @param row Row index
	 * @return Object or null
	 */
	Object getAt(final int colIndex, final int row);
	
	/**
	 * @return List of all column names
	 */
	String[] getColNames();
	
}
