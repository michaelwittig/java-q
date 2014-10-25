package info.michaelwittig.javaq.query;

import info.michaelwittig.javaq.Builder;
import info.michaelwittig.javaq.Q;
import info.michaelwittig.javaq.query.value.Value;

import java.util.List;

/**
 * Select.
 * 
 * @author mwittig
 * 
 */
public interface Function extends Q {
	
	/**
	 * @return Name
	 */
	String getName();
	
	/**
	 * @return Parameters
	 */
	@SuppressWarnings("rawtypes")
	List<Value> getParams();
	
	
	/**
	 * Function builder.
	 * 
	 * @author mwittig
	 * 
	 */
	public interface FunctionBuilder extends Builder<Function> {
		
		/**
		 * @param value Value
		 * @return FunctionBuilder
		 */
		FunctionBuilder param(Value<?, ?> value);
		
	}
	
}
