package info.michaelwittig.javaq.query;

import info.michaelwittig.javaq.Builder;
import info.michaelwittig.javaq.Q;
import info.michaelwittig.javaq.query.Select.SelectBuilder;
import info.michaelwittig.javaq.query.column.Column;

import java.util.Collection;

/**
 * Table.
 * 
 * @author mwittig
 * 
 */
public interface Table extends Q {
	
	/**
	 * @return Name
	 */
	String getName();
	
	/**
	 * @return Columns
	 */
	Collection<Column<?>> getColumns();
	
	/**
	 * @return SelectBuilder
	 */
	SelectBuilder select();
	
	/**
	 * @param result Result
	 * @return Readable result
	 */
	Iterable<TableRow> read(Result result);
	
	
	/**
	 * Table builder.
	 * 
	 * @author mwittig
	 * 
	 */
	public interface TableBuilder extends Builder<Table> {
		
		/**
		 * @param column Column
		 * @return TableBuilder
		 */
		TableBuilder column(final Column<?> column);
		
	}
	
}
