package info.michaelwittig.javaq.query.group.impl;

import info.michaelwittig.javaq.query.column.Column;
import info.michaelwittig.javaq.query.group.Group;
import info.michaelwittig.javaq.query.type.OrdinalType;
import info.michaelwittig.javaq.query.value.Value;

/**
 * Xbar group implementation.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public final class XbarGroupImpl<J, T extends OrdinalType<J>> implements Group {
	
	/** Xbar. */
	private final Value<J, T> xbar;
	
	/** Column. */
	private final Column<T> column;
	
	
	/**
	 * @param aXbar Xbar
	 * @param aColumn Column
	 */
	public XbarGroupImpl(final Value<J, T> aXbar, final Column<T> aColumn) {
		super();
		this.column = aColumn;
		this.xbar = aXbar;
	}
	
	@Override
	public String toQ() {
		return this.xbar.toQ() + " xbar " + this.column.toQ();
	}
	
	@Override
	public Column<?> getColumn() {
		return this.column;
	}
	
}
