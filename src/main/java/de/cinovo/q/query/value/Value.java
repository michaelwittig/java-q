// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value;

import de.cinovo.q.Q;
import de.cinovo.q.query.type.Type;

/**
 * Value.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public interface Value<J, T extends Type<J>> extends Q {
	
	/**
	 * @return Value
	 */
	J get();
	
	/**
	 * @return Type
	 */
	T getType();
	
}
