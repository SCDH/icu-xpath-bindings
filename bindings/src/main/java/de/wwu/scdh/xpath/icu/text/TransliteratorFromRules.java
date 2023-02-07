package de.wwu.scdh.xpath.icu.text;

import net.sf.saxon.lib.ExtensionFunctionDefinition;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.value.SequenceType;
import net.sf.saxon.lib.ExtensionFunctionCall;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.value.BooleanValue;

import com.ibm.icu.text.Transliterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.wwu.scdh.xpath.icu.XPathUtils;
import de.wwu.scdh.xpath.icu.IcuXPathFunctionRegistry;


/**
 * <code>icu:transliterator-from-rules(ID as xs:string, rules as xs:string, direction as xs:string) as xs:boolean</code>
 *
 * This function has a side effect: The newly generated transliterator
 * is registered as an object and can be used in subsequent calls to
 * the transliterate function.
 */
public class TransliteratorFromRules extends ExtensionFunctionDefinition {

    private static final Logger LOG = LoggerFactory.getLogger(TransliteratorFromRules.class);

    public static final String FUNCTION_NAME = "transliterator-from-rules";

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
	return SequenceType.SINGLE_BOOLEAN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasSideEffects() {
	return true;
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
		    String id = XPathUtils.getStringArgument(arguments[0]);
		    String rules = XPathUtils.getStringArgument(arguments[1]);
		    int direction = XPathUtils.getDirectionArgument(arguments[2]);

		    LOG.debug("evaluating xsl:transliterator-from-rules({}, {}, {})", id, rules, direction);

		    // create transliterator instance
		    Transliterator transliterator = Transliterator.createFromRules(id, rules, direction);

		    // register it
		    Transliterator.registerInstance(transliterator);

		    // return true on success
		    return (Sequence) BooleanValue.TRUE;
		} catch (IndexOutOfBoundsException e) {
		    throw new XPathException("wrong number of arguments for " + FUNCTION_NAME + "(...), requires "
					     + this.getDefinition().getArgumentTypes().length,
					     e);
		} catch (IllegalArgumentException e) {
		    throw new XPathException("illegal ID or invalid rules for " + FUNCTION_NAME + "("
					     + XPathUtils.getStringArgument(arguments[0]) + ", ...)",
					     e);
		}
	    }
	};
    }
}
