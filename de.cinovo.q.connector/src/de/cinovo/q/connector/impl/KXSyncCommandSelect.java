package de.cinovo.q.connector.impl;

import java.io.IOException;

import kx.c;
import kx.c.KException;
import de.cinovo.q.connector.KXException;
import de.cinovo.q.connector.KXTable;
import de.cinovo.q.query.Select;

/**
 * KX string command with result.
 *
 * @author mwittig
 *
 */
public final class KXSyncCommandSelect implements KXSyncCommand {

	/** Select. */
	private final Select select;

	/**
	 * @param aSelect Select
	 */
	public KXSyncCommandSelect(final Select aSelect) {
		super();
		this.select = aSelect;
	}

	public Select getSelect() {
		return select;
	}

	@Override
	public KXTable execute(final c c) throws KXException, KException, IOException {
		final Object res = c.k(this.select.toQ());
		if (res instanceof c.Flip) {
			return new KXTableImpl((c.Flip) res);
		}
		throw new KXException("Result is not a table");
	}

}
