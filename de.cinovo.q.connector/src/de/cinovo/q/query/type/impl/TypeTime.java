// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import de.cinovo.q.query.type.OrdinalType;


/**
 * Time.
 *
 * @author mwittig
 *
 */
public final class TypeTime implements OrdinalType<Time> {

	/** Null. */
	public static final String NULL = "0Nt";

	/** Seconds to millis factor. */
	public static final long SECONDS_TO_MILLIS = 1000L;

	/** Minutes to millis factor. */
	public static final long MINUTES_TO_MILLIS = 60L * SECONDS_TO_MILLIS;

	/** Hours to millis factor. */
	public static final long HOURS_TO_MILLIS = 60L * MINUTES_TO_MILLIS;

	/**
	 * @param value Value
	 * @return Time
	 */
	public static TypeTime from(final Time value) {
		if (value == null) {
			return new TypeTime(value);
		} else {
			// java makes wired things with timezones (so we must correct this here)
			final long offset = TimeZone.getDefault().getOffset(value.getTime());
			return new TypeTime(new Time(value.getTime() - offset));
		}
	}

	/**
	 * @param hours [0-23]
	 * @param minutes [0-59]
	 * @param seconds [0-59]
	 * @param millis [0-99]
	 * @return Time
	 */
	public static TypeTime from(final int hours, final int minutes, final int seconds, final int millis) {
		return from(new Time(hours * HOURS_TO_MILLIS + minutes * MINUTES_TO_MILLIS + seconds * SECONDS_TO_MILLIS + millis));
	}

	/** Value. */
	private final Time value;

	@Override
	public String toQ() {
		if (this.value == null) {
			return NULL;
		}
		final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		return sdf.format(this.value);
	}

	/**
	 * @param aValue Value
	 */
	private TypeTime(final Time aValue) {
		this.value = aValue;
	}

}
