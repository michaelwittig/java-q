package info.michaelwittig.javaq.query.value.impl;

import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.impl.TypeList;
import info.michaelwittig.javaq.query.type.impl.TypeLong;

import java.util.Collection;

/**
 * Long value.
 * 
 * @author mwittig
 * 
 */
public final class LongValue extends AValue<Long, TypeLong> {
	
	/** Null. */
	public static final String NULL = "0Nj";
	
	
	/**
	 * @param value Value
	 * @return Long value
	 */
	public static LongValue from(final Long value) {
		return new LongValue(value, TypeLong.get());
	}
	
	/**
	 * @param values Values
	 * @return List of longs
	 */
	public static ListValue<Long, TypeList<Long, Type<Long>>> froms(final Long[] values) {
		return new ListValue<Long, TypeList<Long, Type<Long>>>(values, TypeList.getLong());
	}
	
	/**
	 * @param values Values
	 * @return List of longs
	 */
	public static ListValue<Long, TypeList<Long, Type<Long>>> froms(final Collection<Long> values) {
		return new ListValue<Long, TypeList<Long, Type<Long>>>(new Long[values.size()], TypeList.getLong());
	}
	
	/**
	 * @param value Value
	 * @param type Type
	 */
	private LongValue(final Long value, final TypeLong type) {
		super(value, type);
	}
	
	@Override
	public String toQ() {
		if (this.get() == null) {
			return LongValue.NULL;
		}
		return String.valueOf(this.get()) + "j";
	}
	
}
