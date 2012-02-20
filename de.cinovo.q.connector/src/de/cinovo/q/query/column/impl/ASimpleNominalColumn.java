package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.filter.EqualityFiltering;
import de.cinovo.q.query.filter.Filter;
import de.cinovo.q.query.filter.impl.FilterComparator;
import de.cinovo.q.query.filter.impl.FilterImpl;
import de.cinovo.q.query.group.Group;
import de.cinovo.q.query.group.Grouping;
import de.cinovo.q.query.type.NominalType;

/**
 * Abstract simple nominal column.
 *
 * @author mwittig
 *
 * @param <T> Type
 */
public abstract class ASimpleNominalColumn<T extends NominalType<?>> implements Column<T>, EqualityFiltering<T>, Grouping {

	/** Name. */
	private final String name;

	/** Type. */
	private final Class<T> type;

	/**
	 * @param aName Name
	 * @param aType Type
	 */
	protected ASimpleNominalColumn(final String aName, final Class<T> aType) {
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
	public final Class<T> getType() {
		return this.type;
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
	public final Filter filterEqualTo(final T value) {
		return this.createFilter(FilterComparator.equal, value);
	}

	@Override
	public final Filter filterNotEqualTo(final T value) {
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

	/**
	 * @param comarator Comparator
	 * @param value Value
	 * @return Filter
	 */
	protected final Filter createFilter(final FilterComparator comarator, final T value) {
		return new FilterImpl<T>(this, comarator, value);
	}

	/**
	 * @param comarator Comparator
	 * @param column Column
	 * @return Filter
	 */
	protected final Filter createFilter(final FilterComparator comarator, final Column<T> column) {
		return new FilterImpl<T>(this, comarator, column);
	}

}
