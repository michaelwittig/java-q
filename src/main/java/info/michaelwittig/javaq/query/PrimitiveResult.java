package info.michaelwittig.javaq.query;

/**
 * Primitive result.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 */
public final class PrimitiveResult<J> implements Result {
	
	/** Primitive. */
	private final J primitive;
	
	
	/**
	 * @param aPrimitive Primitive
	 */
	public PrimitiveResult(final J aPrimitive) {
		this.primitive = aPrimitive;
	}
	
	/**
	 * @return Primitive
	 */
	public J getPrimitive() {
		return this.primitive;
	}
	
	@Override
	public String toString() {
		return "PrimitiveResult [primitive=" + this.primitive + "]";
	}
	
}
