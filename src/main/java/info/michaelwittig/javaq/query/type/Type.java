package info.michaelwittig.javaq.query.type;

/**
 * Type.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 */
public abstract interface Type<J> {
	
	/**
	 * @return Value factory
	 */
	ValueFactory<J, Type<J>> geValueFactory();
	
}
