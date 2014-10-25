package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.type.impl.TypeFloat;

import java.math.BigDecimal;

/**
 * Float column.
 * 
 * @author mwittig
 * 
 */
public class FloatColumn extends ASimpleOrdinalColumn<BigDecimal, TypeFloat> {
	
	/**
	 * @param name Name
	 */
	public FloatColumn(final String name) {
		super(name, TypeFloat.get());
	}
	
}
