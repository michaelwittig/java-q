package info.michaelwittig.javaq.query.value.impl;

import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.value.Value;

/**
 * Abstract value.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
abstract class AValue<J, T extends Type<J>> implements Value<J, T> {
	
	/** Value. */
	private final J value;
	
	/** Type. */
	private final T type;
	
	
	/**
	 * @param aValue Value
	 * @param aType Type
	 */
	AValue(final J aValue, final T aType) {
		super();
		this.value = aValue;
		this.type = aType;
	}
	
	@Override
	public final J get() {
		return this.value;
	}
	
	@Override
	public final T getType() {
		return this.type;
	}
	
}
