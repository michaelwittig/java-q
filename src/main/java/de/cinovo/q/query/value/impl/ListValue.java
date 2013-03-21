// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import java.util.ArrayList;

import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.impl.TypeList;
import de.cinovo.q.query.value.Value;

public class ListValue<J, T extends TypeList<J, Type<J>>> extends AValue<J[], T> {

	/** Values. */
	private final ArrayList<Value<J, ? extends Type<J>>> values;
	
	public ListValue(final J[] aValue, final T aType) {
		super(aValue, aType);
		if (aValue != null) {
			this.values = new ArrayList<Value<J, ? extends Type<J>>>();
			for (final J value : aValue) {
				this.values.add(aType.getItemType().geValueFactory().create(value));
			}
		} else {
			this.values = null;
		}
		
	}

	@Override
	public final String toQ() {
		final StringBuilder sb = new StringBuilder();
		if (this.values != null) {
			sb.append("(");
			if (this.values.size() > 0) {
				for (final Value<J, ? extends Type<J>> value : this.values) {
					sb.append(value.toQ());
					sb.append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append(")");
		} else {
			sb.append("()"); // TODO is this correct?
		}
		return sb.toString();
	}

}
