// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.group;

import de.cinovo.q.Q;
import de.cinovo.q.query.column.Column;

/**
 * Group.
 *
 * @author mwittig
 *
 */
public interface Group extends Q {

	/**
	 * @return Column
	 */
	Column<?> getColumn();

}
