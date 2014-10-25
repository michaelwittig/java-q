package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.column.AggregateColumn;
import info.michaelwittig.javaq.query.column.Aggregation;
import info.michaelwittig.javaq.query.column.Column;
import info.michaelwittig.javaq.query.column.VirtualColumn;
import info.michaelwittig.javaq.query.type.Type;

/**
 * Aggregate column implementation.
 * 
 * @author mwittig
 * 
 * @param <T> Type
 */
public final class AggregateColumnImpl<T extends Type<?>> implements AggregateColumn<T> {
	
	/** Aggregation. */
	private final Aggregation aggregation;
	
	/** Type. */
	private final Column<T> column;
	
	
	/**
	 * @param anAggregation Aggregation
	 * @param aColumn Column
	 */
	protected AggregateColumnImpl(final Aggregation anAggregation, final Column<T> aColumn) {
		this.aggregation = anAggregation;
		this.column = aColumn;
	}
	
	@Override
	public String getName() {
		return this.column.getName();
	}
	
	@Override
	public T getType() {
		return this.column.getType();
	}
	
	@Override
	public String toQ() {
		return this.aggregation.toQ() + " " + this.column.toQ();
	}
	
	@Override
	public Aggregation getAggregation() {
		return this.aggregation;
	}
	
	@Override
	public VirtualColumn<T> virtual(final String virtual) {
		return new VirtualColumnImpl<T>(virtual, this);
	}
	
}
