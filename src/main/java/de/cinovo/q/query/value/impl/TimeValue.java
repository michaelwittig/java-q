// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.TimeZone;

import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.impl.TypeList;
import de.cinovo.q.query.type.impl.TypeTime;

/**
 * Time value.
 * 
 * @author mwittig
 * 
 */
public final class TimeValue extends AValue<Time, TypeTime> {
	
	/** Null. */
	public static final String NULL = "0Nt";
	
	/** Seconds to millis factor. */
	public static final long SECONDS_TO_MILLIS = 1000L;
	
	/** Minutes to millis factor. */
	public static final long MINUTES_TO_MILLIS = 60L * TimeValue.SECONDS_TO_MILLIS;
	
	/** Hours to millis factor. */
	public static final long HOURS_TO_MILLIS = 60L * TimeValue.MINUTES_TO_MILLIS;
	
	
	/**
	 * @param value Value
	 * @return Time
	 */
	public static TimeValue from(final Time value) {
		if (value == null) {
			return new TimeValue(value, TypeTime.get());
		}
		
		// java does wired things with time zones (so we must correct this here)
		final long offset = TimeZone.getDefault().getOffset(value.getTime());
		return new TimeValue(new Time(value.getTime() - offset), TypeTime.get());
	}
	
	/**
	 * @param hours [0-23]
	 * @param minutes [0-59]
	 * @param seconds [0-59]
	 * @param millis [0-99]
	 * @return Time
	 */
	public static TimeValue from(final int hours, final int minutes, final int seconds, final int millis) {
		return TimeValue.from(new Time((hours * TimeValue.HOURS_TO_MILLIS) + (minutes * TimeValue.MINUTES_TO_MILLIS) + (seconds * TimeValue.SECONDS_TO_MILLIS) + millis));
	}
	
	/**
	 * @param values Values
	 * @return List of times
	 */
	public static ListValue<Time, TypeList<Time, Type<Time>>> froms(final Time[] values) {
		return new ListValue<Time, TypeList<Time, Type<Time>>>(values, TypeList.getTime());
	}
	
	/**
	 * @param values Values
	 * @return List of times
	 */
	public static ListValue<Time, TypeList<Time, Type<Time>>> froms(final Collection<Time> values) {
		return new ListValue<Time, TypeList<Time, Type<Time>>>(values.toArray(new Time[values.size()]), TypeList.getTime());
	}
	
	/**
	 * @param value Value
	 * @param type Type
	 */
	private TimeValue(final Time value, final TypeTime type) {
		super(value, type);
	}
	
	@Override
	public String toQ() {
		if (this.get() == null) {
			return TimeValue.NULL;
		}
		final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		return sdf.format(this.get());
	}
}
