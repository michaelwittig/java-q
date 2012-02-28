package de.cinovo.q.query;


/**
 * Table result wrapper.
 *
 * @author mwittig
 *
 * @param <T> Table
 *
 */
public interface TableResultWrapper<T extends Table> extends ResultWrapper, Iterable<TableRow<T>> {

	@Override
	TableResultWrapper<T> fromQ(Object res);

}
