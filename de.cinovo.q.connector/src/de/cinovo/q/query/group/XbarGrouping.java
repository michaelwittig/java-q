package de.cinovo.q.query.group;

import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.impl.TypeInteger;

/**
 * Column can be grouped.
 *
 * @author mwittig
 *
 * @param <T> Tape
 *
 */
public interface XbarGrouping<T extends OrdinalType<?>> extends Grouping {

	/**
	 * @param xbar xbar
	 * @return Group
	 */
	Group xbar(TypeInteger xbar);

}
