// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import de.cinovo.q.query.type.OrdinalType;


/**
 * Timestamp.
 *
 * @author mwittig
 *
 */
public final class TypeTimestamp implements OrdinalType<Timestamp> {

	/** Null. */
	public static final String NULL = "0Np";

	/** Micros to nanos. */
	public static final int MICROS_TO_NANOS = 1000;

	/** Millis to nanos. */
	public static final int MILLIS_TO_NANOS = 1000 * MICROS_TO_NANOS;

	/**
	 * @param value Value
	 * @return Timestamp
	 */
	public static TypeTimestamp from(final Timestamp value) {
		if (value == null) {
			return new TypeTimestamp(value);
		} else {
			// java makes wired things with timezones (so we must correct this here)
			final long offset = TimeZone.getDefault().getOffset(value.getTime());
			final Timestamp timestamp = new Timestamp(value.getTime() - offset);
			timestamp.setNanos(value.getNanos());
			return new TypeTimestamp(timestamp);
		}
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
	public static TypeTimestamp from(final int years, final int months, final int days,
			final int hours, final int minutes, final int seconds, final int millis, final int micros, final int nanos) {
		final GregorianCalendar cal = new GregorianCalendar(years, months - 1, days, hours, minutes, seconds);
		final Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
		timestamp.setNanos(millis * MILLIS_TO_NANOS + micros * MICROS_TO_NANOS + nanos);
		return new TypeTimestamp(timestamp);
	}

	/** Value. */
	private final Timestamp value;

	@Override
	public String toQ() {
		if (this.value == null) {
			return NULL;
		}
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		final SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		final DecimalFormat df = new DecimalFormat("000000000");
		return sdf.format(this.value) + "D" + sdf2.format(this.value) + "." + df.format(this.value.getNanos());
	}

	/**
	 * @param aValue Value
	 */
	private TypeTimestamp(final Timestamp aValue) {
		this.value = aValue;
	}

}
