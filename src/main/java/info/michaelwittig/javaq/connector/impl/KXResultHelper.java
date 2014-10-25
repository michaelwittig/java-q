package info.michaelwittig.javaq.connector.impl;

import info.michaelwittig.javaq.connector.KXException;
import info.michaelwittig.javaq.query.EmptyResult;
import info.michaelwittig.javaq.query.FlipFlipResult;
import info.michaelwittig.javaq.query.FlipResult;
import info.michaelwittig.javaq.query.ListResult;
import info.michaelwittig.javaq.query.PrimitiveResult;
import info.michaelwittig.javaq.query.Result;

import java.util.UUID;

import kx.c;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author mwittig
 * 
 */
public final class KXResultHelper {
	
	/**
	 * Converts an result from the kx library to a QConnector Result.
	 * 
	 * @param res Result from q
	 * @return Result
	 * @throws KXException If the result type is not supported
	 */
	public static Result convert(final Object res) throws KXException {
		if (res == null) {
			return new EmptyResult();
		}
		// table
		if (res instanceof c.Flip) {
			return new FlipResult("", (c.Flip) res);
		}
		// dict
		if (res instanceof c.Dict) {
			final c.Dict dict = (c.Dict) res;
			if ((dict.x instanceof c.Flip) && (dict.y instanceof c.Flip)) {
				final c.Flip key = (c.Flip) dict.x;
				final c.Flip data = (c.Flip) dict.y;
				return new FlipFlipResult("", key, data);
			}
		}
		if (res instanceof Object[]) {
			final Object[] oa = (Object[]) res;
			if ((oa.length == 2) && (oa[0] instanceof String) && (oa[1] instanceof c.Flip)) {
				final String table = (String) oa[0];
				final c.Flip flip = (c.Flip) oa[1];
				return new FlipResult(table, flip);
			} else if ((oa.length == 3) && (oa[1] instanceof String) && (oa[2] instanceof c.Flip)) {
				final String table = (String) oa[1];
				final c.Flip flip = (c.Flip) oa[2];
				return new FlipResult(table, flip);
			} else {
				return new ListResult<Object>(oa);
			}
		}
		// list
		if (res instanceof byte[]) {
			return new ListResult<Byte>(ArrayUtils.toObject((byte[]) res));
		}
		if (res instanceof boolean[]) {
			return new ListResult<Boolean>(ArrayUtils.toObject((boolean[]) res));
		}
		if (res instanceof short[]) {
			return new ListResult<Short>(ArrayUtils.toObject((short[]) res));
		}
		if (res instanceof int[]) {
			return new ListResult<Integer>(ArrayUtils.toObject((int[]) res));
		}
		if (res instanceof long[]) {
			return new ListResult<Long>(ArrayUtils.toObject((long[]) res));
		}
		if (res instanceof float[]) {
			return new ListResult<Float>(ArrayUtils.toObject((float[]) res)); // q real
		}
		if (res instanceof double[]) {
			return new ListResult<Double>(ArrayUtils.toObject((double[]) res)); // q float
		}
		if (res instanceof char[]) {
			return new ListResult<Character>(ArrayUtils.toObject((char[]) res));
		}
		if (res.getClass().isArray()) {
			if (res.getClass().getComponentType() == String.class) { // q symbol
				return new ListResult<String>((String[]) res);
			}
			if (res.getClass().getComponentType() == java.sql.Timestamp.class) { // q timestamp
				return new ListResult<java.sql.Timestamp>((java.sql.Timestamp[]) res);
			}
			if (res.getClass().getComponentType() == kx.c.Minute.class) { // q minute
				return new ListResult<kx.c.Minute>((kx.c.Minute[]) res);
			}
			if (res.getClass().getComponentType() == kx.c.Second.class) { // q second
				return new ListResult<kx.c.Second>((kx.c.Second[]) res);
			}
			if (res.getClass().getComponentType() == kx.c.Month.class) { // q month
				return new ListResult<kx.c.Month>((kx.c.Month[]) res);
			}
			if (res.getClass().getComponentType() == java.sql.Time.class) { // q time
				return new ListResult<java.sql.Time>((java.sql.Time[]) res);
			}
			if (res.getClass().getComponentType() == java.sql.Date.class) { // q date
				return new ListResult<java.sql.Date>((java.sql.Date[]) res);
			}
			if (res.getClass().getComponentType() == java.util.Date.class) { // q datetime
				return new ListResult<java.util.Date>((java.util.Date[]) res);
			}
			if (res.getClass().getComponentType() == kx.c.Timespan.class) { // q timespan
				return new ListResult<kx.c.Timespan>((kx.c.Timespan[]) res);
			}
			if (res.getClass().getComponentType() == UUID.class) { // q guid
				return new ListResult<UUID>((UUID[]) res);
			}
		}
		// primitive
		if (res instanceof Boolean) {
			return new PrimitiveResult<Boolean>((Boolean) res);
		}
		if (res instanceof Byte) {
			return new PrimitiveResult<Byte>((Byte) res);
		}
		if (res instanceof Short) {
			return new PrimitiveResult<Short>((Short) res);
		}
		if (res instanceof Long) {
			return new PrimitiveResult<Long>((Long) res);
		}
		if (res instanceof Integer) {
			return new PrimitiveResult<Integer>((Integer) res);
		}
		if (res instanceof Float) { // q real
			return new PrimitiveResult<Float>((Float) res);
		}
		if (res instanceof Double) { // q float
			return new PrimitiveResult<Double>((Double) res);
		}
		if (res instanceof Character) { // q char
			return new PrimitiveResult<Character>((Character) res);
		}
		if (res instanceof String) { // q symbol
			return new PrimitiveResult<String>((String) res);
		}
		if (res instanceof java.sql.Timestamp) { // q timestamp
			return new PrimitiveResult<java.sql.Timestamp>((java.sql.Timestamp) res);
		}
		if (res instanceof kx.c.Minute) { // q minute
			return new PrimitiveResult<kx.c.Minute>((kx.c.Minute) res);
		}
		if (res instanceof kx.c.Second) { // q second
			return new PrimitiveResult<kx.c.Second>((kx.c.Second) res);
		}
		if (res instanceof kx.c.Month) { // q month
			return new PrimitiveResult<kx.c.Month>((kx.c.Month) res);
		}
		if (res instanceof java.sql.Time) { // q time
			return new PrimitiveResult<java.sql.Time>((java.sql.Time) res);
		}
		if (res instanceof java.sql.Date) { // q date
			return new PrimitiveResult<java.sql.Date>((java.sql.Date) res);
		}
		if (res instanceof java.util.Date) { // q datetime
			return new PrimitiveResult<java.util.Date>((java.util.Date) res);
		}
		if (res instanceof kx.c.Timespan) { // q timespan
			return new PrimitiveResult<kx.c.Timespan>((kx.c.Timespan) res);
		}
		if (res instanceof UUID) { // q guid
			return new PrimitiveResult<UUID>((UUID) res);
		}
		throw new KXException("Unsupported sync result type: " + res.getClass());
	}
}
