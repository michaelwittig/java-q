// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type;

import de.cinovo.q.query.value.Value;

/**
 * Type factory.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public interface ValueFactory<J, T extends Type<J>> {
	
	/**
	 * @param value Value
	 * @return Value
	 */
	Value<J, ? extends T> create(J value);
	
	/**
	 * @param value Value
	 * @return Value
	 */
	Value<J, ? extends T> fromQ(Object value);
	
}
