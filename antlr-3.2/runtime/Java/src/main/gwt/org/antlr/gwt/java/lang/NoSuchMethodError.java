/*
 * @(#)NoSuchMethodError.java	1.22 05/11/17
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.lang;

/**
 * Thrown if an application tries to call a specified method of a 
 * class (either static or instance), and that class no longer has a 
 * definition of that method. 
 * <p>
 * Normally, this error is caught by the compiler; this error can 
 * only occur at run time if the definition of a class has 
 * incompatibly changed. 
 *
 * @author  unascribed
 * @version 1.22, 11/17/05
 * @since   JDK1.0
 */
public
class NoSuchMethodError extends Error {
    /**
     * Constructs a <code>NoSuchMethodError</code> with no detail message.
     */
    public NoSuchMethodError() {
	super();
    }

    /**
     * Constructs a <code>NoSuchMethodError</code> with the 
     * specified detail message. 
     *
     * @param   s   the detail message.
     */
    public NoSuchMethodError(String s) {
	super(s);
    }
}
