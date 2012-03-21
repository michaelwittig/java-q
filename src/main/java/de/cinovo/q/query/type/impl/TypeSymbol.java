// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import de.cinovo.q.query.type.NominalType;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.ValueFactory;
import de.cinovo.q.query.value.Value;
import de.cinovo.q.query.value.impl.SymbolValue;


/**
 * Symbol.
 *
 * @author mwittig
 *
 */
public final class TypeSymbol implements NominalType<String> {

	/** Instance. */
	private static final TypeSymbol INSTANCE = new TypeSymbol();

	/**
	 * @return Instance
	 */
	public static TypeSymbol get() {
		return INSTANCE;
	}

	@Override
	public ValueFactory<String, Type<String>> geValueFactory() {
		return new ValueFactory<String, Type<String>>() {

			@Override
			public Value<String, ? extends Type<String>> create(final String value) {
				return SymbolValue.from(value);
			}

			@Override
			public Value<String, ? extends Type<String>> fromQ(final Object value) {
				if (value instanceof String) {
					return this.create((String) value);
				} // TODO what to do woth Lists String[] ???
				throw new IllegalArgumentException("Type is " + value.getClass().getSimpleName());
			}
		};
	}

	/**  */
	private TypeSymbol() {
		super();
	}

}
