/*
 * @(#)NoSuchMethodException.java	1.14 05/11/17
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.lang;

/**
 * Thrown when a particular method cannot be found.
 *
 * @author     unascribed
 * @version    1.14, 11/17/05
 * @since      JDK1.0
 */
public
class NoSuchMethodException extends Exception {
    /**
     * Constructs a <code>NoSuchMethodException</code> without a detail message.
     */
    public NoSuchMethodException() {
	super();
    }

    /**
     * Constructs a <code>NoSuchMethodException</code> with a detail message. 
     *
     * @param      s   the detail message.
     */
    public NoSuchMethodException(String s) {
	super(s);
    }
}
