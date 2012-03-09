// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type;


/**
 * List with nominal types.
 *
 * @author mwittig
 *
 * @param <J> Java type
 * @param <T> Type
 */
public interface NominalList<J, T extends NominalType<J>> extends List<J, T>, NominalType<J> {

}
