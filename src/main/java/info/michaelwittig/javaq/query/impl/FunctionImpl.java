package info.michaelwittig.javaq.query.impl;

import info.michaelwittig.javaq.query.Function;
import info.michaelwittig.javaq.query.value.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Function implementation.
 * 
 * @author mwittig
 * 
 */
public final class FunctionImpl implements Function {
	
	/** Table. */
	private final String name;
	
	/** Columns. */
	@SuppressWarnings("rawtypes")
	private final List<Value> params = new ArrayList<Value>();
	
	
	/**
	 * @param aName Name
	 */
	public FunctionImpl(final String aName) {
		super();
		this.name = aName;
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public String toQ() {
		final StringBuilder sb = new StringBuilder(this.name);
		sb.append("[");
		boolean first = true;
		for (final Value param : this.params) {
			if (!first) {
				sb.append(";");
			}
			first = false;
			sb.append(param.toQ());
		}
		sb.append("]");
		return sb.toString();
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public List<Value> getParams() {
		return this.params;
	}
	
	
	/**
	 * Select builder implementation.
	 * 
	 * @author mwittig
	 * 
	 */
	public static final class FunctionBuilderImpl implements FunctionBuilder {
		
		/** Function. */
		private final FunctionImpl function;
		
		
		/**
		 * @param name Function
		 */
		public FunctionBuilderImpl(final String name) {
			this.function = new FunctionImpl(name);
		}
		
		@Override
		public FunctionBuilder param(final Value<?, ?> param) {
			this.function.params.add(param);
			return this;
		}
		
		@Override
		public Function build() {
			return this.function;
		}
		
	}
	
}
