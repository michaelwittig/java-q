// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.column;

import de.cinovo.q.query.type.Type;

/**
 * Aggregating.
 * 
 * @author mwittig
 * 
 * @param <T> Type
 */
public interface AggregatingOrdinal<T extends Type<?>> extends AggregatingNominal<T> {
	
	/**
	 * @return Minimum
	 */
	AggregateColumn<T> min();
	
	/**
	 * @return maximum
	 */
	AggregateColumn<T> max();
	
	/**
	 * @return Average
	 */
	AggregateColumn<T> avg();
	
	/**
	 * @return Sum
	 */
	AggregateColumn<T> sum();
	
}
