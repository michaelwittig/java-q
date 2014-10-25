package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.type.impl.TypeReal;

import java.math.BigDecimal;

/**
 * Real column.
 * 
 * @author mwittig
 * 
 */
public class RealColumn extends ASimpleOrdinalColumn<BigDecimal, TypeReal> {
	
	/**
	 * @param name Name
	 */
	public RealColumn(final String name) {
		super(name, TypeReal.get());
	}
	
}
