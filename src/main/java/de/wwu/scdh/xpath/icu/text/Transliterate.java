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

import com.ibm.icu.text.Transliterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.wwu.scdh.xpath.icu.XPathUtils;
import de.wwu.scdh.xpath.icu.IcuXPathFunctionRegistry;


/**
 * <code>icu:transliterate(input as xs:string, transliterator as xs:string) as xs:string</code>
 *
 */
public class Transliterate extends ExtensionFunctionDefinition {

    private static final Logger LOG = LoggerFactory.getLogger(Transliterate.class);

    public static final String FUNCTION_NAME = "transliterate";

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
		    String transliterator = XPathUtils.getStringArgument(arguments[1]);

		    LOG.debug("evaluating xsl:transliterate('{}', '{}')", input, transliterator);

		    // create transliterator instance
		    Transliterator icuTransliterator = Transliterator.getInstance(transliterator);

		    // do the job
		    return new StringValue(icuTransliterator.transliterate(input));
		} catch (IndexOutOfBoundsException e) {
		    throw new XPathException("wrong number of arguments for icu:transliterate(), requires "
					     + this.getDefinition().getArgumentTypes().length,
					     e);
		} catch (IllegalArgumentException e) {
		    throw new XPathException("illegal transliterator ID for icu:transliterate(..., "
					     + ((StringValue) arguments[1]).materialize().toString() + ")",
					     e);
		} catch (MissingResourceException e) {
		    throw new XPathException("illegal transliterator ID for icu:transliterate() function: "
					     + ((StringValue) arguments[1]).materialize().toString(),
					     e);
		}
	    }
	};
    }
}
