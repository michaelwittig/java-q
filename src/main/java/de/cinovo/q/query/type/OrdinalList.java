// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type;

/**
 * List with ordinal types.
 * 
 * @author mwittig
 * 
 * @param <J>
 *            Java type
 * @param <T>
 *            Type
 */
public interface OrdinalList<J, T extends OrdinalType<J>> extends NominalList<J, T>, OrdinalType<J> {

	// interface body intentionally left blank

}
