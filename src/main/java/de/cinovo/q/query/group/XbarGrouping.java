// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.group;

import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.value.Value;

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
