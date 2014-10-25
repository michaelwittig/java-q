package info.michaelwittig.javaq.query.value.impl;

import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.impl.TypeBoolean;
import info.michaelwittig.javaq.query.type.impl.TypeList;

import java.util.Collection;

/**
 * Boolean value.
 * 
 * @author mwittig
 * 
 */
public final class BooleanValue extends AValue<Boolean, TypeBoolean> {
	
	/** Null. */
	public static final String NULL = "";
	
	
	/**
	 * @param value Value
	 * @return Real
	 */
	public static BooleanValue from(final Boolean value) {
		return new BooleanValue(value, TypeBoolean.get());
	}
	
	/**
	 * @param values Values
	 * @return List of reals
	 */
	public static ListValue<Boolean, TypeList<Boolean, Type<Boolean>>> froms(final Boolean[] values) {
		return new ListValue<Boolean, TypeList<Boolean, Type<Boolean>>>(values, TypeList.getBoolean());
	}
	
	/**
	 * @param values Values
	 * @return List of reals
	 */
	public static ListValue<Boolean, TypeList<Boolean, Type<Boolean>>> froms(final Collection<Boolean> values) {
		return new ListValue<Boolean, TypeList<Boolean, Type<Boolean>>>(values.toArray(new Boolean[values.size()]), TypeList.getBoolean());
	}
	
	/**
	 * @param value Value
	 * @param type Type
	 */
	private BooleanValue(final Boolean value, final TypeBoolean type) {
		super(value, type);
	}
	
	@Override
	public String toQ() {
		if (this.get() == null) {
			return BooleanValue.NULL;
		}
		if (this.get() == true) {
			return "1b";
		}
		return "0b";
	}
	
}
