package de.wwu.scdh.xpath.icu.text;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;

import net.sf.saxon.lib.ExtensionFunctionDefinition;
import net.sf.saxon.lib.ExtensionFunctionCall;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.om.SequenceIterator;

public class TransliteratorIdsTest {

    protected ExtensionFunctionCall function;

    @BeforeEach
    public void setup() {
	ExtensionFunctionDefinition functionDefinition = new TransliteratorIds();
	function = functionDefinition.makeCallExpression();
    }

    @Test
    public void testTranslitertorIds() throws XPathException {
	Sequence[] arguments = new Sequence[] {};
	Sequence output = function.call(null, arguments);
	SequenceIterator iter = output.iterate();
	// assert that we have at least one ID
	assertNotNull(iter.next());;
    }

}
