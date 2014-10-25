package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.column.Column;
import info.michaelwittig.javaq.query.column.VirtualColumn;
import info.michaelwittig.javaq.query.type.Type;

/**
 * Virtual column implementation.
 * 
 * @author mwittig
 * 
 * @param <T> Type
 */
public final class VirtualColumnImpl<T extends Type<?>> implements VirtualColumn<T> {
	
	/** Virtual. */
	private final String virtual;
	
	/** Type. */
	private final Column<T> column;
	
	
	/**
	 * @param aVirtual Virtual
	 * @param aColumn Column
	 */
	protected VirtualColumnImpl(final String aVirtual, final Column<T> aColumn) {
		this.virtual = aVirtual;
		this.column = aColumn;
	}
	
	@Override
	public String toQ() {
		return this.virtual + ":" + this.column.toQ();
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
	public String getVirtual() {
		return this.virtual;
	}
	
}
