package de.wwu.scdh.xpath.icu.text;

import java.util.MissingResourceException;

import net.sf.saxon.lib.ExtensionFunctionDefinition;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.value.SequenceType;
import net.sf.saxon.lib.ExtensionFunctionCall;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.value.StringValue;
import net.sf.saxon.value.AtomicValue;

import com.ibm.icu.text.Normalizer2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.wwu.scdh.xpath.icu.XPathUtils;
import de.wwu.scdh.xpath.icu.IcuXPathFunctionRegistry;


/**
 * <code>icu:normalize(input as xs:string, normalizer as xs:string, mode as xs:string) as xs:string</code>
 *
 */
public class Normalize extends ExtensionFunctionDefinition {

    private static final Logger LOG = LoggerFactory.getLogger(Normalize.class);

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
	    SequenceType.SINGLE_STRING,
	    SequenceType.SINGLE_STRING,
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

		try {
		    // cast arguments
		    String input = XPathUtils.getStringArgument(arguments[0]);
		    String normalizerName = XPathUtils.getStringArgument(arguments[1]);
		    String modeString = XPathUtils.getStringArgument(arguments[2]);

		    LOG.debug("evaluating xsl:normalize({}, {}, {})",
			      input, normalizerName, modeString);

		    // get normalizer mode from argument
		    Normalizer2.Mode mode = null;
		    if (modeString.equals("compose")) {
			mode = Normalizer2.Mode.COMPOSE;
		    } else if (modeString.equals("compose_contiguous")) {
			mode = Normalizer2.Mode.COMPOSE_CONTIGUOUS;
		    } else if (modeString.equals("decompose")) {
			mode = Normalizer2.Mode.DECOMPOSE;
		    } else if (modeString.equals("fcd")) {
			mode = Normalizer2.Mode.FCD;
		    } else {
			throw new XPathException("illegal mode for icu:normalize() function: " + modeString);
		    }

		    // create normalizer instance
		    Normalizer2 normalizer;
		    normalizer = Normalizer2.getInstance(null, normalizerName, mode);

		    // normalize the input string
		    String normalized = normalizer.normalize(input);

		    return new StringValue(normalized);
		} catch (IndexOutOfBoundsException e) {
		    throw new XPathException("wrong number of arguments for icu:normalize(), requires "
					     + this.getDefinition().getArgumentTypes().length,
					     e);
		} catch (MissingResourceException e) {
		    throw new XPathException("illegal normalizer for icu:normalize() function: "
					     + ((StringValue) arguments[1].materialize()).toString(),
					     e);
		}
	    }
	};
    }
}
