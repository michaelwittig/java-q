// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.filter;

import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.type.NominalType;

/**
 * Filter for equality.
 *
 * @author mwittig
 *
 * @param <T> Type
 */
public interface EqualityFiltering<T extends NominalType<?>> {

	/**
	 * @param value Value
	 * @return Filter(= value)
	 */
	Filter filterEqualTo(final T value);

	/**
	 * @param value Value
	 * @return Filter(<> value)
	 */
	Filter filterNotEqualTo(final T value);

	/**
	 * @param column Column
	 * @return Filter(= column)
	 */
	Filter filterEqualTo(final Column<T> column);

	/**
	 * @param column Column
	 * @return Filter(<> column)
	 */
	Filter filterNotEqualTo(final Column<T> column);

}
