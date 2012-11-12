// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import de.cinovo.q.query.type.NominalList;
import de.cinovo.q.query.type.NominalType;

/**
 * Nominal list implementation.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public final class NominalListImpl<J, T extends NominalType<J>> extends AListImpl<J, T> implements NominalList<J, T> {
	
	/**
	 * @param aValues Values
	 * @param aType Type
	 */
	NominalListImpl(final J[] aValues, final T aType) {
		super(aValues, aType);
	}
	
}
