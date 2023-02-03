package de.wwu.scdh.xpath.icu;

import net.sf.saxon.om.Sequence;
import net.sf.saxon.value.StringValue;
import net.sf.saxon.value.AtomicValue;

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
}
