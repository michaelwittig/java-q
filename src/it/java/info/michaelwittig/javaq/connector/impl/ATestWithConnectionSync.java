package info.michaelwittig.javaq.connector.impl;

import info.michaelwittig.javaq.connector.KXConnectorSync;
import info.michaelwittig.javaq.query.ListResult;
import info.michaelwittig.javaq.query.PrimitiveResult;
import info.michaelwittig.javaq.query.TableResult;

import java.sql.Timestamp;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import kx.c.Minute;
import kx.c.Second;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author mwittig
 * 
 * @param <T> KXConnectorSync
 * 
 */
@SuppressWarnings("javadoc")
public abstract class ATestWithConnectionSync<T extends KXConnectorSync> extends ATestWithConnection<T> {
	
	// Dict
	
	@Test
	@Ignore
	// not yet supported
	public void testReadSimpleDict() throws Exception {
		this.c().execute("(`a`b`c)!(1 2 3)");
	}
	
	// Table
	
	@Test
	public void testReadSimpleTable() throws Exception {
		final TableResult res = (TableResult) this.c().execute("([] a:(1 2 3 4 5 6); b:(111000b); c:(`a`b`c`d`e`f))");
		Assert.assertEquals(3, res.getCols());
		Assert.assertEquals(6, res.getRows());
		Assert.assertArrayEquals(new String[] {"a", "b", "c"}, res.getColNames());
		Assert.assertEquals(1L, res.getAt(0, 0));
		Assert.assertEquals(1L, res.getAt("a", 0));
		Assert.assertEquals(true, res.getAt(1, 0));
		Assert.assertEquals(true, res.getAt("b", 0));
	}
	
	@Test
	public void testReadKeyedTable() throws Exception {
		final TableResult res = (TableResult) this.c().execute("select by a from ([] a:(1 2 3 4 5 6); b:(111000b); c:(`a`b`c`d`e`f))");
		Assert.assertEquals(3, res.getCols());
		Assert.assertEquals(6, res.getRows());
		Assert.assertArrayEquals(new String[] {"a", "b", "c"}, res.getColNames());
		Assert.assertEquals(1L, res.getAt(0, 0));
		Assert.assertEquals(1L, res.getAt("a", 0));
		Assert.assertEquals(true, res.getAt(1, 0));
		Assert.assertEquals(true, res.getAt("b", 0));
	}
	
	// Primitive
	
	@Test
	public void testReadPrimitiveBoolean() throws Exception {
		final PrimitiveResult<Boolean> res = (PrimitiveResult<Boolean>) this.c().execute("1b");
		Assert.assertEquals(true, res.getPrimitive());
	}
	
	@Test
	public void testReadPrimitiveByte() throws Exception {
		final PrimitiveResult<Byte> res = (PrimitiveResult<Byte>) this.c().execute("0x00");
		Assert.assertEquals(Byte.valueOf((byte) 0), res.getPrimitive());
	}
	
	@Test
	public void testReadPrimitiveShort() throws Exception {
		final PrimitiveResult<Short> res = (PrimitiveResult<Short>) this.c().execute("1h");
		Assert.assertEquals(Short.valueOf((short) 1), res.getPrimitive());
	}
	
	@Test
	public void testReadPrimitiveInteger() throws Exception {
		try {
			final PrimitiveResult<Integer> res = (PrimitiveResult<Integer>) this.c().execute("1i");
			Assert.assertEquals(Integer.valueOf(1), res.getPrimitive());
		} finally {
			this.terminateQProcess();
		}
	}
	
	@Test
	public void testReadPrimitiveLong() throws Exception {
		final PrimitiveResult<Integer> res = (PrimitiveResult<Integer>) this.c().execute("1j");
		Assert.assertEquals(Long.valueOf(1), res.getPrimitive());
	}
	
	@Test
	public void testReadPrimitiveReal() throws Exception {
		final PrimitiveResult<Float> res = (PrimitiveResult<Float>) this.c().execute("1e");
		Assert.assertEquals(Float.valueOf(1.0f), res.getPrimitive());
	}
	
	@Test
	public void testReadPrimitiveFloat() throws Exception {
		final PrimitiveResult<Double> res = (PrimitiveResult<Double>) this.c().execute("1f");
		Assert.assertEquals(Double.valueOf(1.0), res.getPrimitive());
	}
	
	@Test
	public void testReadPrimitiveChar() throws Exception {
		final PrimitiveResult<Character> res = (PrimitiveResult<Character>) this.c().execute("\"a\"");
		Assert.assertEquals(Character.valueOf('a'), res.getPrimitive());
	}
	
	@Test
	public void testReadPrimitiveSymbol() throws Exception {
		final PrimitiveResult<String> res = (PrimitiveResult<String>) this.c().execute("`abc");
		Assert.assertEquals("abc", res.getPrimitive());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testReadPrimitiveTimestamp() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final PrimitiveResult<Timestamp> res = (PrimitiveResult<Timestamp>) this.c().execute("2013.01.01D10:00:00.000000000");
		Assert.assertEquals(new Timestamp(2013 - 1900, 0, 1, 10, 0, 0, 0), res.getPrimitive());
		TimeZone.setDefault(tz);
	}
	
	@Test
	public void testReadPrimitiveMonth() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final PrimitiveResult<kx.c.Month> res = (PrimitiveResult<kx.c.Month>) this.c().execute("2000.01m");
		Assert.assertEquals("2000-01", res.getPrimitive().toString());
		TimeZone.setDefault(tz);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testReadPrimitiveDate() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final PrimitiveResult<java.sql.Date> res = (PrimitiveResult<java.sql.Date>) this.c().execute("2000.01.01");
		Assert.assertEquals(new java.sql.Date(2000 - 1900, 0, 1), res.getPrimitive());
		TimeZone.setDefault(tz);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testReadPrimitiveDatetime() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final PrimitiveResult<java.util.Date> res = (PrimitiveResult<java.util.Date>) this.c().execute("2000.01.01T10:00:00.000");
		Assert.assertEquals(new Date(2000 - 1900, 0, 1, 10, 0, 0), res.getPrimitive());
		TimeZone.setDefault(tz);
	}
	
	@Test
	public void testReadPrimitiveTimespan() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final PrimitiveResult<kx.c.Timespan> res = (PrimitiveResult<kx.c.Timespan>) this.c().execute("01:00:00.000000000");
		Assert.assertEquals("01:00:00.000000000", res.getPrimitive().toString());
		TimeZone.setDefault(tz);
	}
	
	@Test
	public void testReadPrimitiveMinute() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final PrimitiveResult<Minute> res = (PrimitiveResult<Minute>) this.c().execute("01:00");
		Assert.assertEquals("01:00", res.getPrimitive().toString());
		TimeZone.setDefault(tz);
	}
	
	@Test
	public void testReadPrimitiveSeconde() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final PrimitiveResult<Second> res = (PrimitiveResult<Second>) this.c().execute("01:00:00");
		Assert.assertEquals("01:00:00", res.getPrimitive().toString());
		TimeZone.setDefault(tz);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testReadPrimitiveTime() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final PrimitiveResult<java.sql.Time> res = (PrimitiveResult<java.sql.Time>) this.c().execute("01:00:00.000");
		Assert.assertEquals(new java.sql.Time(1, 0, 0), res.getPrimitive());
		TimeZone.setDefault(tz);
	}
	
	@Test
	public void testReadPrimitiveGuid() throws Exception {
		final PrimitiveResult<UUID> res = (PrimitiveResult<UUID>) this.c().execute("first -2?0Ng");
		Assert.assertNotNull(res.getPrimitive());
	}
	
	// List
	
	@Test
	public void testReadListBoolean() throws Exception {
		final ListResult<Boolean> res = (ListResult<Boolean>) this.c().execute("enlist 1b");
		Assert.assertArrayEquals(new Boolean[] {true}, res.getList());
	}
	
	@Test
	public void testReadListByte() throws Exception {
		final ListResult<Byte> res = (ListResult<Byte>) this.c().execute("enlist 0x00");
		Assert.assertArrayEquals(new Byte[] {(byte) 0}, res.getList());
	}
	
	@Test
	public void testReadListShort() throws Exception {
		final ListResult<Short> res = (ListResult<Short>) this.c().execute("enlist 1h");
		Assert.assertArrayEquals(new Short[] {(short) 1}, res.getList());
	}
	
	@Test
	public void testReadListInteger() throws Exception {
		try {
			final ListResult<Integer> res = (ListResult<Integer>) this.c().execute("enlist 1i");
			Assert.assertArrayEquals(new Integer[] {1}, res.getList());
		} finally {
			this.terminateQProcess();
		}
	}
	
	@Test
	public void testReadListLong() throws Exception {
		final ListResult<Integer> res = (ListResult<Integer>) this.c().execute("enlist 1j");
		Assert.assertArrayEquals(new Long[] {1L}, res.getList());
	}
	
	@Test
	public void testReadListReal() throws Exception {
		final ListResult<Float> res = (ListResult<Float>) this.c().execute("enlist 1e");
		Assert.assertArrayEquals(new Float[] {1.0f}, res.getList());
	}
	
	@Test
	public void testReadListFloat() throws Exception {
		final ListResult<Double> res = (ListResult<Double>) this.c().execute("enlist 1f");
		Assert.assertArrayEquals(new Double[] {1.0}, res.getList());
	}
	
	@Test
	public void testReadListChar() throws Exception {
		final ListResult<Character> res = (ListResult<Character>) this.c().execute("enlist \"a\"");
		Assert.assertArrayEquals(new Character[] {'a'}, res.getList());
	}
	
	@Test
	public void testReadListSymbol() throws Exception {
		final ListResult<String> res = (ListResult<String>) this.c().execute("enlist `abc");
		Assert.assertArrayEquals(new String[] {"abc"}, res.getList());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testReadListTimestamp() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final ListResult<Timestamp> res = (ListResult<Timestamp>) this.c().execute("enlist 2013.01.01D10:00:00.000000000");
		Assert.assertArrayEquals(new Timestamp[] {new Timestamp(2013 - 1900, 0, 1, 10, 0, 0, 0)}, res.getList());
		TimeZone.setDefault(tz);
	}
	
	@Test
	public void testReadListMonth() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final ListResult<kx.c.Month> res = (ListResult<kx.c.Month>) this.c().execute("enlist 2000.01m");
		Assert.assertEquals("2000-01", res.getList()[0].toString());
		TimeZone.setDefault(tz);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testReadListDate() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final ListResult<java.sql.Date> res = (ListResult<java.sql.Date>) this.c().execute("enlist 2000.01.01");
		Assert.assertArrayEquals(new java.sql.Date[] {new java.sql.Date(2000 - 1900, 0, 1)}, res.getList());
		TimeZone.setDefault(tz);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testReadListDatetime() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final ListResult<java.util.Date> res = (ListResult<java.util.Date>) this.c().execute("enlist 2000.01.01T10:00:00.000");
		Assert.assertArrayEquals(new Date[] {new Date(2000 - 1900, 0, 1, 10, 0, 0)}, res.getList());
		TimeZone.setDefault(tz);
	}
	
	@Test
	public void testReadListTimespan() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final ListResult<kx.c.Timespan> res = (ListResult<kx.c.Timespan>) this.c().execute("enlist 01:00:00.000000000");
		Assert.assertEquals("01:00:00.000000000", res.getList()[0].toString());
		TimeZone.setDefault(tz);
	}
	
	@Test
	public void testReadListMinute() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final ListResult<Minute> res = (ListResult<Minute>) this.c().execute("enlist 01:00");
		Assert.assertEquals("01:00", res.getList()[0].toString());
		TimeZone.setDefault(tz);
	}
	
	@Test
	public void testReadListSeconde() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final ListResult<Second> res = (ListResult<Second>) this.c().execute("enlist 01:00:00");
		Assert.assertEquals("01:00:00", res.getList()[0].toString());
		TimeZone.setDefault(tz);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testReadListTime() throws Exception {
		final TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final ListResult<java.sql.Time> res = (ListResult<java.sql.Time>) this.c().execute("enlist 01:00:00.000");
		Assert.assertArrayEquals(new java.sql.Time[] {new java.sql.Time(1, 0, 0)}, res.getList());
		TimeZone.setDefault(tz);
	}
	
	@Test
	public void testReadListGuid() throws Exception {
		final ListResult<UUID> res = (ListResult<UUID>) this.c().execute("-2?0Ng");
		Assert.assertEquals(2, res.getList().length);
	}
	
}
