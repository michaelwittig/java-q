package info.michaelwittig.javaq.demo.tabledefinition;

import info.michaelwittig.javaq.query.column.impl.FloatColumn;
import info.michaelwittig.javaq.query.column.impl.IntegerColumn;
import info.michaelwittig.javaq.query.column.impl.SymbolColumn;
import info.michaelwittig.javaq.query.column.impl.TimeColumn;
import info.michaelwittig.javaq.query.impl.ATable;

/**
 * MyTable definition.
 * 
 * @author mwittig
 * 
 */
public final class MyTable extends ATable {
	
	/** Instance. */
	private static final MyTable INSTANCE = new MyTable();
	
	
	/**
	 * @return Instance
	 */
	public static MyTable get() {
		return MyTable.INSTANCE;
	}
	
	
	/** Time. */
	private final TimeColumn time = new TimeColumn("time");
	
	/** Sym. */
	private final SymbolColumn sym = new SymbolColumn("sym");
	
	/** Price. */
	private final FloatColumn price = new FloatColumn("price");
	
	/** Size. */
	private final IntegerColumn size = new IntegerColumn("size");
	
	
	/** */
	private MyTable() {
		super("mytable");
		this.addColumn(this.time);
		this.addColumn(this.sym);
		this.addColumn(this.price);
		this.addColumn(this.size);
	}
	
	/**
	 * @return Time column
	 */
	public TimeColumn time() {
		return this.time;
	}
	
	/**
	 * @return Symbol column
	 */
	public SymbolColumn sym() {
		return this.sym;
	}
	
	/**
	 * @return Price column
	 */
	public FloatColumn price() {
		return this.price;
	}
	
	/**
	 * @return Size column
	 */
	public IntegerColumn size() {
		return this.size;
	}
	
}
