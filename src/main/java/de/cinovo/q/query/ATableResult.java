// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query;

/**
 * @author thoeger
 * 
 */
public abstract class ATableResult implements TableResult {

    private final String name;

    /**
     * @param aName Table name
     */
    ATableResult(final String aName) {
        this.name = aName;
    }

	/**
	 * @param col Column name
	 * @param row Row
	 * @return Value
	 */
	abstract Object getAt(final String col, final int row);

	public String getName() {
        return this.name;
    }

	/**
	 * @return Column names
	 */
	abstract String[] getColNames();
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (final String col : this.getColNames()) {
			sb.append(String.format("%20s ", col));
		}
		sb.append(System.getProperty("line.separator"));
		for (int i = 0; i < this.getCols(); i++) {
			sb.append(String.format("%20s ", "--"));
		}
		sb.append(System.getProperty("line.separator"));
		for (int i = 0; i < this.getRows(); i++) {
			for (final String col : this.getColNames()) {
				final Object value = this.getAt(col, i);
				if (value == null) {
					sb.append(String.format("%20s ", ""));
				} else {
					sb.append(String.format("%20s ", value.toString()));
				}
			}
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
}
