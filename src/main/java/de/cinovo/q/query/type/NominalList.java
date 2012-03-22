// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type;

/**
 * List with nominal types.
 * 
 * @author mwittig
 * 
 * @param <J>
 *            Java type
 * @param <T>
 *            Type
 */
public interface NominalList<J, T extends NominalType<J>> extends List<J, T>, NominalType<J> {

}
