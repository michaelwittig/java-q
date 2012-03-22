// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.filter;

import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.type.List;
import de.cinovo.q.query.type.NominalType;
import de.cinovo.q.query.value.Value;

/**
 * Filter for equality.
 * 
 * @author mwittig
 * 
 * @param <J>
 *            Java type
 * @param <T>
 *            Type
 */
public interface EqualityFiltering<J, T extends NominalType<J>> {

	/**
	 * @param value
	 *            Value
	 * @return Filter(= value)
	 */
	Filter filterEqualTo(final Value<J, T> value);

	/**
	 * @param value
	 *            Value
	 * @return Filter(<> value)
	 */
	Filter filterNotEqualTo(final Value<J, T> value);

	/**
	 * @param column
	 *            Column
	 * @return Filter(= column)
	 */
	Filter filterEqualTo(final Column<T> column);

	/**
	 * @param column
	 *            Column
	 * @return Filter(<> column)
	 */
	Filter filterNotEqualTo(final Column<T> column);

	/**
	 * @param list
	 *            List
	 * @return Filter(in list)
	 */
	Filter filterIn(final List<J, T> list);

}
