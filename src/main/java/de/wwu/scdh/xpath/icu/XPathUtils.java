package de.wwu.scdh.xpath.icu;

import net.sf.saxon.om.Sequence;
import net.sf.saxon.value.StringValue;
import net.sf.saxon.value.AtomicValue;
import net.sf.saxon.trans.XPathException;

import com.ibm.icu.text.Transliterator;

/**
 * Utilities used throughout the binding library.
 */
public class XPathUtils {

    /**
     * Cast a argument of type <code>xs:string</code> to a {@link String}.
     *
     * @param arg  the argument as {@link Sequence}
     * @return a {@link String}
     */
    public static String getStringArgument(Sequence arg) {
	StringValue inputValue = (StringValue) arg.materialize();
	return ((AtomicValue) inputValue).toString();
    }

    public static int getDirectionArgument(Sequence arg) throws XPathException {
	StringValue inputValue = (StringValue) arg.materialize();
	String directionString = ((AtomicValue) inputValue).toString();
	if (directionString.toLowerCase().equals("forward")) {
	    return Transliterator.FORWARD;
	} else if (directionString.toLowerCase().equals("reverse")) {
	    return Transliterator.REVERSE;
	} else {
	    throw new XPathException("illegal direction: " + directionString
				     + " Allowed values are FORWARD and REVERSE");
	}
    }
}
