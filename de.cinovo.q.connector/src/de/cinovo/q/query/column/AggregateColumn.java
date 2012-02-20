// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.column;

import de.cinovo.q.query.type.Type;

/**
 * Aggregate column.
 *
 * @author mwittig
 *
 * @param <T> Type
 */
public interface AggregateColumn<T extends Type<?>> extends Column<T>, Virtualling<T> {

	/**
	 * @return Aggregation
	 */
	Aggregation getAggregation();

}
