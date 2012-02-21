// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.group;

import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.impl.TypeLong;

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
	Group xbar(TypeLong xbar);

}
