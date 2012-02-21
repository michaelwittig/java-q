// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type;

import de.cinovo.q.Q;

/**
 * List.
 *
 * @author mwittig
 *
 * @param <E> Class of type
 * @param <T> Type
 */
public abstract interface List<E, T extends Type<E>> extends Type<E>, Q {

}
