package de.wwu.scdh.xpath.icu.text;

import net.sf.saxon.lib.ExtensionFunctionDefinition;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.value.SequenceType;
import net.sf.saxon.lib.ExtensionFunctionCall;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.value.StringValue;

import de.wwu.scdh.xpath.icu.IcuXPathFunctionRegistry;


/**
 * <code>icu:normalize(xs:string) as xs:string</code>
 *
 */
public class Normalize extends ExtensionFunctionDefinition {

    public static final String FUNCTION_NAME = "normalize";

    /**
     * {@inheritDoc}
     */
    @Override
    public StructuredQName getFunctionQName() {
	return new StructuredQName(IcuXPathFunctionRegistry.PREFIX,
				   IcuXPathFunctionRegistry.NAMESPACE,
				   FUNCTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SequenceType[] getArgumentTypes() {
	return new SequenceType[] {
	    SequenceType.SINGLE_STRING
	};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SequenceType getResultType(SequenceType[] suppliedArgumentTypes) {
	return SequenceType.SINGLE_STRING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExtensionFunctionCall makeCallExpression() {
	return new ExtensionFunctionCall() {
	    @Override
	    public Sequence call(XPathContext context, Sequence[] arguments)
		throws XPathException {
		StringValue inputString = (StringValue) arguments[0];

		// TODO: do something

		return inputString;
	    }
	};
    }
}
