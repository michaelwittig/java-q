// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.group;

import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.value.impl.LongValue;

/**
 * Column can be grouped.
 * 
 * @author mwittig
 * 
 * @param <T>
 *            Tape
 * 
 */
public interface XbarGrouping<T extends OrdinalType<?>> extends Grouping {

	/**
	 * @param xbar
	 *            xbar
	 * @return Group
	 */
	Group xbar(LongValue xbar);

}
