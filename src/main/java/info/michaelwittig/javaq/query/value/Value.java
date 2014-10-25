package info.michaelwittig.javaq.query.value;

import info.michaelwittig.javaq.Q;
import info.michaelwittig.javaq.query.type.Type;

/**
 * Value.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public interface Value<J, T extends Type<J>> extends Q {
	
	/**
	 * @return Value
	 */
	J get();
	
	/**
	 * @return Type
	 */
	T getType();
	
}
