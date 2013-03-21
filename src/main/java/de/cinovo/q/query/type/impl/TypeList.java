// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.ValueFactory;
import de.cinovo.q.query.value.Value;

/**
 * List.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public class TypeList<J, T extends Type<J>> implements Type<J[]> {
	
	/** Instances. */
	private static final TypeList<Boolean, Type<Boolean>> BOOLEAN_INSTANCE = new TypeList<Boolean, Type<Boolean>>(TypeBoolean.get());
	private static final TypeList<Date, Type<Date>> DATE_INSTANCE = new TypeList<Date, Type<Date>>(TypeDate.get());
	private static final TypeList<java.util.Date, Type<java.util.Date>> DATETIME_INSTANCE = new TypeList<java.util.Date, Type<java.util.Date>>(TypeDateTime.get());
	private static final TypeList<BigDecimal, Type<BigDecimal>> FLOAT_INSTANCE = new TypeList<BigDecimal, Type<BigDecimal>>(TypeFloat.get());
	private static final TypeList<Integer, Type<Integer>> INTEGER_INSTANCE = new TypeList<Integer, Type<Integer>>(TypeInteger.get());
	private static final TypeList<Long, Type<Long>> LONG_INSTANCE = new TypeList<Long, Type<Long>>(TypeLong.get());
	private static final TypeList<BigDecimal, Type<BigDecimal>> REAL_INSTANCE = new TypeList<BigDecimal, Type<BigDecimal>>(TypeReal.get());
	private static final TypeList<String, Type<String>> SYMBOL_INSTANCE = new TypeList<String, Type<String>>(TypeSymbol.get());
	private static final TypeList<Time, Type<Time>> TIME_INSTANCE = new TypeList<Time, Type<Time>>(TypeTime.get());
	private static final TypeList<Timestamp, Type<Timestamp>> TIMESTAMP_INSTANCE = new TypeList<Timestamp, Type<Timestamp>>(TypeTimestamp.get());
	
	
	/**
	 * @return Instance
	 */
	public static TypeList<Boolean, Type<Boolean>> getBoolean() {
		return TypeList.BOOLEAN_INSTANCE;
	}
	
	/**
	 * @return Instance
	 */
	public static TypeList<Date, Type<Date>> getDate() {
		return TypeList.DATE_INSTANCE;
	}
	
	/**
	 * @return Instance
	 */
	public static TypeList<java.util.Date, Type<java.util.Date>> getDateTime() {
		return TypeList.DATETIME_INSTANCE;
	}
	
	/**
	 * @return Instance
	 */
	public static TypeList<BigDecimal, Type<BigDecimal>> getFloat() {
		return TypeList.FLOAT_INSTANCE;
	}
	
	/**
	 * @return Instance
	 */
	public static TypeList<Integer, Type<Integer>> getInteger() {
		return TypeList.INTEGER_INSTANCE;
	}
	
	/**
	 * @return Instance
	 */
	public static TypeList<Long, Type<Long>> getLong() {
		return TypeList.LONG_INSTANCE;
	}
	
	/**
	 * @return Instance
	 */
	public static TypeList<BigDecimal, Type<BigDecimal>> getReal() {
		return TypeList.REAL_INSTANCE;
	}
	
	/**
	 * @return Instance
	 */
	public static TypeList<String, Type<String>> getSymbol() {
		return TypeList.SYMBOL_INSTANCE;
	}
	
	/**
	 * @return Instance
	 */
	public static TypeList<Time, Type<Time>> getTime() {
		return TypeList.TIME_INSTANCE;
	}
	
	/**
	 * @return Instance
	 */
	public static TypeList<Timestamp, Type<Timestamp>> getTimestamp() {
		return TypeList.TIMESTAMP_INSTANCE;
	}
	
	
	/** Item type. */
	private final Type<J> itemType;
	
	
	/**
	 * @param anItemType Item type
	 */
	private TypeList(final Type<J> anItemType) {
		super();
		this.itemType = anItemType;
	}
	
	@Override
	public ValueFactory<J[], Type<J[]>> geValueFactory() {
		return new ValueFactory<J[], Type<J[]>>() {
			
			@Override
			public Value<J[], ? extends Type<J[]>> create(final J[] value) {
				throw new UnsupportedOperationException(); // TODO implement
			}
			
			@Override
			public Value<J[], ? extends Type<J[]>> fromQ(final Object value) {
				throw new UnsupportedOperationException(); // TODO implement
			}
		};
	}
	
	/**
	 * @return Item type
	 */
	public Type<J> getItemType() {
		return this.itemType;
	}
	
}
