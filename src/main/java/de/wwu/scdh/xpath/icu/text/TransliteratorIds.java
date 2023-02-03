package de.wwu.scdh.xpath.icu.text;

import java.util.Collections;
import java.util.List;

import net.sf.saxon.lib.ExtensionFunctionDefinition;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.value.SequenceType;
import net.sf.saxon.lib.ExtensionFunctionCall;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.value.StringValue;
import net.sf.saxon.om.GroundedValue;
import net.sf.saxon.om.EmptyAtomicSequence;

import com.ibm.icu.text.Transliterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.wwu.scdh.xpath.icu.IcuXPathFunctionRegistry;


/**
 * <code>icu:transliterator-ids() as xs:string*</code>
 *
 * This adds a function for getting a sequence of transliterator IDs.
 * It is based on {@link Transliterator#getAvailableIDs()}.
 */
public class TransliteratorIds extends ExtensionFunctionDefinition {

    private static final Logger LOG = LoggerFactory.getLogger(TransliteratorIds.class);

    public static final String FUNCTION_NAME = "transliterator-ids";

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
	return new SequenceType[] {};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SequenceType getResultType(SequenceType[] suppliedArgumentTypes) {
	return SequenceType.STRING_SEQUENCE;
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
		LOG.debug("evaluating xsl:transliterator-ids()");

		List<String> ids = Collections.list(Transliterator.getAvailableIDs());
		int size = ids.size();
		LOG.debug("This version of ICU provides {} transliterators", size);

		// make a sequence of xs:string values
		GroundedValue output = EmptyAtomicSequence.getInstance();
		for (int i = 0; i < size; i++) {
		    output = output.concatenate(new StringValue(ids.get(i)));
		}
		return output;
	    }
	};
    }
}
