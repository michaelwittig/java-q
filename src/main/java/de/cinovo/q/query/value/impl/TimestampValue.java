// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.impl.TypeList;
import de.cinovo.q.query.type.impl.TypeTimestamp;

/**
 * Timestamp value.
 * 
 * @author mwittig
 * 
 */
public final class TimestampValue extends AValue<Timestamp, TypeTimestamp> {
	
	/** Null. */
	public static final String NULL = "0Np";
	
	/** Micros to nanos. */
	public static final int MICROS_TO_NANOS = 1000;
	
	/** Millis to nanos. */
	public static final int MILLIS_TO_NANOS = 1000 * TimestampValue.MICROS_TO_NANOS;
	
	
	/**
	 * @param value Value
	 * @return Timestamp
	 */
	public static TimestampValue from(final Date value) {
		return TimestampValue.from(new Timestamp(value.getTime()));
	}
	
	/**
	 * @param value Value
	 * @return Timestamp
	 */
	public static TimestampValue from(final Timestamp value) {
		if (value == null) {
			return new TimestampValue(value, TypeTimestamp.get());
		}
		
		// java does wired things with time zones (so we must correct this here)
		final long offset = TimeZone.getDefault().getOffset(value.getTime());
		final Timestamp timestamp = new Timestamp(value.getTime() - offset);
		timestamp.setNanos(value.getNanos());
		return new TimestampValue(timestamp, TypeTimestamp.get());
	}
	
	/**
	 * @param years [1960-20xx]
	 * @param months [1-12]
	 * @param days [1-31]
	 * @param hours [0-23]
	 * @param minutes [0-59]
	 * @param seconds [0-59]
	 * @param millis [0-99]
	 * @param micros [0-99]
	 * @param nanos [0-99]
	 * @return Timestamp
	 */
	public static TimestampValue from(final int years, final int months, final int days, final int hours, final int minutes, final int seconds, final int millis, final int micros, final int nanos) {
		final GregorianCalendar cal = new GregorianCalendar(years, months - 1, days, hours, minutes, seconds);
		final long offset = cal.getTimeZone().getOffset(cal.getTimeInMillis());
		final Timestamp timestamp = new Timestamp(cal.getTimeInMillis() + offset);
		timestamp.setNanos((millis * TimestampValue.MILLIS_TO_NANOS) + (micros * TimestampValue.MICROS_TO_NANOS) + nanos);
		return TimestampValue.from(timestamp);
	}
	
	/**
	 * @param values Values
	 * @return List of timestamps
	 */
	public static ListValue<Timestamp, TypeList<Timestamp, Type<Timestamp>>> froms(final Timestamp[] values) {
		return new ListValue<Timestamp, TypeList<Timestamp, Type<Timestamp>>>(values, TypeList.getTimestamp());
	}
	
	/**
	 * @param value Value
	 * @param type Type
	 */
	private TimestampValue(final Timestamp value, final TypeTimestamp type) {
		super(value, type);
	}
	
	@Override
	public String toQ() {
		if (this.get() == null) {
			return TimestampValue.NULL;
		}
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		final SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		final DecimalFormat df = new DecimalFormat("000000000");
		return sdf.format(this.get()) + "D" + sdf2.format(this.get()) + "." + df.format(this.get().getNanos());
	}
	
}
