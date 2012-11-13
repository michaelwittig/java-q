package de.cinovo.q.connector.impl;

import kx.c;
import de.cinovo.q.query.FlipFlipResult;
import de.cinovo.q.query.FlipResult;
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
			return new FlipResult((c.Flip) res);
		} else if (res instanceof c.Dict) {
			final c.Dict dict = (c.Dict) res;
			if ((dict.x instanceof c.Flip) && (dict.y instanceof c.Flip)) {
				final c.Flip key = (c.Flip) dict.x;
				final c.Flip data = (c.Flip) dict.y;
				return new FlipFlipResult(key, data);
			}
		}
		return null; // can not be casted to a Q result
	}
	
}
