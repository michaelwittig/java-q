package info.michaelwittig.javaq.query.value.impl;

import java.sql.Time;

import org.junit.Assert;
import org.junit.Test;

/**
 * TimeValue test.
 * 
 * @author mwittig
 * 
 */
public class TimeValueTest {
	
	/** */
	@Test
	public final void testNullQ() {
		Assert.assertEquals("0Nt", TimeValue.from(null).toQ());
	}
	
	/**
	 * @return Timestamp
	 */
	private Time create() {
		final Time time = new Time(1000 * 60 * 60 * 12);
		return time;
	}
	
	/**
	 * @return Timestamp
	 */
	private Time create2() {
		final Time time = new Time(1000 * 60 * 60 * 13);
		return time;
	}
	
	/** */
	@Test
	public final void testQ() {
		Assert.assertEquals("12:00:00.000", TimeValue.from(this.create()).toQ());
	}
	
	/** */
	@Test
	public final void testQ2() {
		Assert.assertEquals("12:30:00.500", TimeValue.from(12, 30, 0, 500).toQ());
	}
	
	/** */
	@Test
	public final void testListEmpty() {
		Assert.assertEquals("()", TimeValue.froms(new Time[] {}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithOneItem() {
		Assert.assertEquals("(12:00:00.000)", TimeValue.froms(new Time[] {this.create()}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithTwoItems() {
		Assert.assertEquals("(12:00:00.000,13:00:00.000)", TimeValue.froms(new Time[] {this.create(), this.create2()}).toQ());
	}
	
}
