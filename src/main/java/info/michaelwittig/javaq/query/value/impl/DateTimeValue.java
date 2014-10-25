package info.michaelwittig.javaq.query.value.impl;

import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.impl.TypeDateTime;
import info.michaelwittig.javaq.query.type.impl.TypeList;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

/**
 * DateTime value.
 * 
 * @author mwittig
 * 
 */
public final class DateTimeValue extends AValue<Date, TypeDateTime> {
	
	/** Null. */
	public static final String NULL = "0Nz";
	
	
	/**
	 * @param value Value
	 * @return Time
	 */
	public static DateTimeValue from(final Date value) {
		if (value == null) {
			return new DateTimeValue(value, TypeDateTime.get());
		}
		
		// java does wired things with time zones (so we must correct this here)
		final long offset = TimeZone.getDefault().getOffset(value.getTime());
		return new DateTimeValue(new Date(value.getTime() - offset), TypeDateTime.get());
	}
	
	/**
	 * @param values Values
	 * @return List of times
	 */
	public static ListValue<Date, TypeList<Date, Type<Date>>> froms(final Date[] values) {
		return new ListValue<Date, TypeList<Date, Type<Date>>>(values, TypeList.getDateTime());
	}
	
	/**
	 * @param values Values
	 * @return List of times
	 */
	public static ListValue<Date, TypeList<Date, Type<Date>>> froms(final Collection<Date> values) {
		return new ListValue<Date, TypeList<Date, Type<Date>>>(values.toArray(new Date[values.size()]), TypeList.getDateTime());
	}
	
	/**
	 * @param value Value
	 * @param type Type
	 */
	private DateTimeValue(final Date value, final TypeDateTime type) {
		super(value, type);
	}
	
	@Override
	public String toQ() {
		if (this.get() == null) {
			return DateTimeValue.NULL;
		}
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd'T'HH:mm:ss.SSS");
		return sdf.format(this.get());
	}
}
