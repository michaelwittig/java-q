// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.TimeZone;

import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.impl.TypeDate;
import de.cinovo.q.query.type.impl.TypeList;

/**
 * Time value.
 * 
 * @author mwittig
 * 
 */
public final class DateValue extends AValue<Date, TypeDate> {
	
	/** Null. */
	public static final String NULL = "0Nd";
	
	
	/**
	 * @param value Value
	 * @return Time
	 */
	public static DateValue from(final Date value) {
		if (value == null) {
			return new DateValue(value, TypeDate.get());
		}
		
		// java does wired things with time zones (so we must correct this here)
		final long offset = TimeZone.getDefault().getOffset(value.getTime());
		return new DateValue(new Date(value.getTime() - offset), TypeDate.get());
	}
	
	/**
	 * @param values Values
	 * @return List of times
	 */
	public static ListValue<Date, TypeList<Date, Type<Date>>> froms(final Date[] values) {
		return new ListValue<Date, TypeList<Date, Type<Date>>>(values, TypeList.getDate());
	}
	
	/**
	 * @param values Values
	 * @return List of times
	 */
	public static ListValue<Date, TypeList<Date, Type<Date>>> froms(final Collection<Date> values) {
		return new ListValue<Date, TypeList<Date, Type<Date>>>(values.toArray(new Date[values.size()]), TypeList.getDate());
	}
	
	/**
	 * @param value Value
	 * @param type Type
	 */
	public DateValue(final Date value, final TypeDate type) {
		super(value, type);
	}
	
	@Override
	public String toQ() {
		if (this.get() == null) {
			return DateValue.NULL;
		}
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		return sdf.format(this.get());
	}
}
