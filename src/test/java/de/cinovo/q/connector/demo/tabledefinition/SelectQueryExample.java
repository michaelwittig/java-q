// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.demo.tabledefinition;

import de.cinovo.q.connector.KXConnectorSync;
import de.cinovo.q.connector.impl.KXConnectorFactory;
import de.cinovo.q.query.Result;
import de.cinovo.q.query.Select;
import de.cinovo.q.query.Select.Sort.Direction;
import de.cinovo.q.query.TableRow;
import de.cinovo.q.query.value.impl.SymbolValue;

/**
 * Select query example with a static table definition.
 * 
 * @author mwittig
 * 
 */
public final class SelectQueryExample {
	
	/**
	 * @param args Arguments
	 * @throws Exception Of something went wrong
	 */
	public static void main(final String[] args) throws Exception {
		// static table definition
		final MyTable table = MyTable.get();
		
		// create select
		final Select select = table.select().column(table.size().sum()).column(table.price().avg()).group(table.sym().group()).filter(table.sym().filterIn(SymbolValue.froms(new String[] {"AAA", "BBB"}))).order(Direction.descending, table.time()).build();
		System.out.println("Q: " + select.toQ());
		
		// connect to kdb+
		final KXConnectorSync kx = KXConnectorFactory.create("localhost", 5011, true, false);
		kx.connect();
		
		// execute select and print the result
		final Result result = kx.select(select);
		for (final TableRow row : table.read(result)) {
			System.out.println(row.get(table.time()));
			System.out.println(row.get(table.sym()));
			System.out.println(row.get(table.price()));
			System.out.println(row.get(table.size()));
		}
		
		// close connection
		kx.disconnect();
	}
	
	/** */
	private SelectQueryExample() {
		super();
	}
	
}
