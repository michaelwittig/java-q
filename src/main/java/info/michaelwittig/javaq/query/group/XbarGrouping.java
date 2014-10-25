package info.michaelwittig.javaq.query.group;

import info.michaelwittig.javaq.query.type.OrdinalType;
import info.michaelwittig.javaq.query.value.Value;

/**
 * Column can be grouped.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Tape
 * 
 */
public interface XbarGrouping<J, T extends OrdinalType<J>> extends Grouping {
	
	/**
	 * @param xbar xbar
	 * @return Group
	 */
	Group xbar(Value<J, T> xbar);
	
}
