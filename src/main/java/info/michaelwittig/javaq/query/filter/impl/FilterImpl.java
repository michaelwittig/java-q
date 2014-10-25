package info.michaelwittig.javaq.query.filter.impl;

import info.michaelwittig.javaq.query.column.Column;
import info.michaelwittig.javaq.query.filter.Filter;
import info.michaelwittig.javaq.query.type.List;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.impl.TypeList;
import info.michaelwittig.javaq.query.value.Value;
import info.michaelwittig.javaq.query.value.impl.ListValue;

/**
 * Filter implementation.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public final class FilterImpl<J, T extends Type<J>> implements Filter {
	
	/** Column. */
	private final Column<T> column;
	
	/** Q. */
	private final String q;
	
	
	/**
	 * @param left Left
	 * @param comparator Comparator
	 * @param right Right
	 */
	public FilterImpl(final Column<T> left, final FilterComparator comparator, final ListValue<J, TypeList<J, Type<J>>> right) {
		this.column = left;
		this.q = this.column.getName() + comparator.toQ() + right.toQ();
	}
	
	/**
	 * @param left Left
	 * @param comparator Comparator
	 * @param right Right
	 */
	public FilterImpl(final Column<T> left, final FilterComparator comparator, final Value<J, T> right) {
		this.column = left;
		this.q = this.column.getName() + comparator.toQ() + right.toQ();
	}
	
	/**
	 * @param left Left
	 * @param comparator Comparator
	 * @param right Right
	 */
	public FilterImpl(final Column<T> left, final FilterComparator comparator, final Column<T> right) {
		this.column = left;
		this.q = this.column.getName() + comparator.toQ() + right.toQ();
	}
	
	/**
	 * @param left Left
	 * @param comparator Comparator
	 * @param right Right
	 */
	public FilterImpl(final Column<T> left, final FilterComparator comparator, final List<J, T> right) {
		this.column = left;
		this.q = this.column.getName() + comparator.toQ() + right.toQ();
	}
	
	@Override
	public String toQ() {
		return this.q;
	}
	
	@Override
	public Column<T> getColumn() {
		return this.column;
	}
	
}
