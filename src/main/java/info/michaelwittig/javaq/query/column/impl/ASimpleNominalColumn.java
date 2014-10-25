package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.column.AggregateColumn;
import info.michaelwittig.javaq.query.column.AggregatingNominal;
import info.michaelwittig.javaq.query.column.Aggregation;
import info.michaelwittig.javaq.query.column.Column;
import info.michaelwittig.javaq.query.column.VirtualColumn;
import info.michaelwittig.javaq.query.column.Virtualling;
import info.michaelwittig.javaq.query.filter.EqualityFiltering;
import info.michaelwittig.javaq.query.filter.Filter;
import info.michaelwittig.javaq.query.filter.impl.FilterComparator;
import info.michaelwittig.javaq.query.filter.impl.FilterImpl;
import info.michaelwittig.javaq.query.group.Group;
import info.michaelwittig.javaq.query.group.Grouping;
import info.michaelwittig.javaq.query.type.List;
import info.michaelwittig.javaq.query.type.NominalType;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.impl.TypeList;
import info.michaelwittig.javaq.query.value.Value;
import info.michaelwittig.javaq.query.value.impl.ListValue;

/**
 * Abstract simple nominal column.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public abstract class ASimpleNominalColumn<J, T extends NominalType<J>> implements Column<T>, EqualityFiltering<J, T>, Virtualling<T>, AggregatingNominal<T>, Grouping {
	
	/** Name. */
	private final String name;
	
	/** Type. */
	private final T type;
	
	
	/**
	 * @param aName Name
	 * @param aType Type
	 */
	protected ASimpleNominalColumn(final String aName, final T aType) {
		super();
		this.name = aName;
		this.type = aType;
	}
	
	@Override
	public final String toQ() {
		return this.name;
	}
	
	@Override
	public final String getName() {
		return this.name;
	}
	
	@Override
	public final T getType() {
		return this.type;
	}
	
	@Override
	public final VirtualColumn<T> virtual(final String virtual) {
		return new VirtualColumnImpl<T>(virtual, this);
	}
	
	@Override
	public final AggregateColumn<T> first() {
		return this.createAggregation(Aggregation.first);
	}
	
	@Override
	public final AggregateColumn<T> last() {
		return this.createAggregation(Aggregation.last);
	}
	
	@Override
	public final AggregateColumn<T> count() {
		return this.createAggregation(Aggregation.count);
	}
	
	/**
	 * @param aggregation Aggregation
	 * @return Filter
	 */
	protected final AggregateColumn<T> createAggregation(final Aggregation aggregation) {
		return new AggregateColumnImpl<T>(aggregation, this);
	}
	
	@Override
	public final Group group() {
		return new Group() {
			
			@Override
			public String toQ() {
				return ASimpleNominalColumn.this.toQ();
			}
			
			@Override
			public Column<T> getColumn() {
				return ASimpleNominalColumn.this;
			}
		};
	}
	
	@Override
	public final Filter filterEqualTo(final Value<J, T> value) {
		return this.createFilter(FilterComparator.equal, value);
	}
	
	@Override
	public final Filter filterNotEqualTo(final Value<J, T> value) {
		return this.createFilter(FilterComparator.notEqua, value);
	}
	
	@Override
	public final Filter filterEqualTo(final Column<T> column) {
		return this.createFilter(FilterComparator.equal, column);
	}
	
	@Override
	public final Filter filterNotEqualTo(final Column<T> column) {
		return this.createFilter(FilterComparator.notEqua, column);
	}
	
	@Override
	public final Filter filterIn(final ListValue<J, TypeList<J, Type<J>>> list) {
		return this.createFilter(FilterComparator.in, list);
	}
	
	/**
	 * @param comarator Comparator
	 * @param value Value
	 * @return Filter
	 */
	protected final Filter createFilter(final FilterComparator comarator, final ListValue<J, TypeList<J, Type<J>>> value) {
		return new FilterImpl<J, T>(this, comarator, value);
	}
	
	/**
	 * @param comarator Comparator
	 * @param value Value
	 * @return Filter
	 */
	protected final Filter createFilter(final FilterComparator comarator, final Value<J, T> value) {
		return new FilterImpl<J, T>(this, comarator, value);
	}
	
	/**
	 * @param comarator Comparator
	 * @param column Column
	 * @return Filter
	 */
	protected final Filter createFilter(final FilterComparator comarator, final Column<T> column) {
		return new FilterImpl<J, T>(this, comarator, column);
	}
	
	/**
	 * @param comarator Comparator
	 * @param list List
	 * @return Filter
	 */
	protected final Filter createFilter(final FilterComparator comarator, final List<J, T> list) {
		return new FilterImpl<J, T>(this, comarator, list);
	}
	
}
