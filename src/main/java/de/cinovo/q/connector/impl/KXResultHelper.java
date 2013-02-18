// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

import kx.c;
import de.cinovo.q.query.FlipFlipResult;
import de.cinovo.q.query.FlipResult;
import de.cinovo.q.query.ListResult;
import de.cinovo.q.query.PrimitiveResult;
import de.cinovo.q.query.Result;

/**
 * 
 * Copyright 2012 Cinovo AG
 * 
 * @author mwittig
 * 
 */
public final class KXResultHelper {
	
	/**
	 * Converts an result from the kx library to a QConnector Result.
	 * 
	 * @param res Result from q
	 * @return Result
	 */
	public static Result convert(final Object res) {
		if (res instanceof c.Flip) {
			return new FlipResult("", (c.Flip) res);
		} else if (res instanceof c.Dict) {
			final c.Dict dict = (c.Dict) res;
			if ((dict.x instanceof c.Flip) && (dict.y instanceof c.Flip)) {
				final c.Flip key = (c.Flip) dict.x;
				final c.Flip data = (c.Flip) dict.y;
				return new FlipFlipResult(key, data);
			}
		} else {
            if (res instanceof Object[]) {
                final Object[] oa = (Object[]) res;
                if (oa.length == 2 && oa[0] instanceof String && oa[1] instanceof c.Flip) {
                    final String table =  (String) oa[0];
                    final c.Flip flip = (c.Flip) oa[1];
                    return new FlipResult(table, flip);
                } else if (oa.length == 3 && oa[1] instanceof String && oa[2] instanceof c.Flip) {
                    final String table =  (String) oa[1];
                    final c.Flip flip = (c.Flip) oa[2];
                    return new FlipResult(table, flip);
                } else {
                    return new ListResult<Object>(oa);
                }
            } else if (res.getClass().isArray()) {
                if (res.getClass().getComponentType() == String.class) {
					return new ListResult<String>((String[]) res);
				}
			} else {
				if (res instanceof String) {
					return new PrimitiveResult<String>((String) res);
				}
			}
		}
		return null; // can not be casted to a Q result
	}
}
