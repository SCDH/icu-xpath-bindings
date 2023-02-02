package de.wwu.scdh.xpath.icu.text;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;

import net.sf.saxon.lib.ExtensionFunctionDefinition;
import net.sf.saxon.lib.ExtensionFunctionCall;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.value.StringValue;
import net.sf.saxon.trans.XPathException;

public class NormalizeTest {

    protected ExtensionFunctionCall normalizer;

    @BeforeEach
    public void setup() {
	ExtensionFunctionDefinition normalizeDefinition = new Normalize();
	normalizer = normalizeDefinition.makeCallExpression();
    }

    @Test
    // corner case: empty input string
    public void testNormalizeDecomposeEmpty() throws XPathException {
	String input = "";
	Sequence[] arguments = new Sequence[] {
		new StringValue(input),
		new StringValue("nfc"),
		new StringValue("decompose")
	    };
	StringValue output = (StringValue) normalizer.call(null, arguments);
	// the decomposed string is as long as the input string
	assertEquals(input.length(), output.getContent().length());
    }

    @Test
    public void testBadNormalizer() throws XPathException {
	String input = "Do you speak English?";
	Sequence[] arguments = new Sequence[] {
		new StringValue(input),
		new StringValue("fails!!"),
		new StringValue("decompose")
	    };
	assertThrows(XPathException.class, () -> normalizer.call(null, arguments));
    }

    @Test
    public void testBadMode() throws XPathException {
	String input = "Do you speak English?";
	Sequence[] arguments = new Sequence[] {
		new StringValue(input),
		new StringValue("nfc"),
		new StringValue("fails!!")
	    };
	assertThrows(XPathException.class, () -> normalizer.call(null, arguments));
    }

    @Test
    // corner case: nothing to decompose
    public void testNormalizeDecomposeEnglish() throws XPathException {
	String input = "Do you speak English?";
	Sequence[] arguments = new Sequence[] {
		new StringValue(input),
		new StringValue("nfc"),
		new StringValue("decompose")
	    };
	StringValue output = (StringValue) normalizer.call(null, arguments);
	// the decomposed string is as long as the input string
	assertEquals(input.length(), output.getContent().length());
    }

    @Test
    // corner case: nothing to compose
    public void testNormalizeComposeEnglish() throws XPathException {
	String input = "Do you speak English?";
	Sequence[] arguments = new Sequence[] {
		new StringValue(input),
		new StringValue("nfc"),
		new StringValue("compose")
	    };
	StringValue output = (StringValue) normalizer.call(null, arguments);
	// the decomposed string is as long as the input string
	assertEquals(input.length(), output.getContent().length());
    }


    @Test
    public void testNormalizeDecomposeFrench() throws XPathException {
	String input = "Parlez vous français?";
	Sequence[] arguments = new Sequence[] {
		new StringValue(input),
		new StringValue("nfc"),
		new StringValue("decompose")
	    };
	StringValue output = (StringValue) normalizer.call(null, arguments);
	// the decomposed string is 1 character longer than the input string
	assertEquals(input.length() + 1, output.getContent().length());
    }

    @Test
    public void testNormalizeComposeFrench() throws XPathException {
	String input = "Parlez vous français?";
	Sequence[] arguments = new Sequence[] {
		new StringValue(input),
		new StringValue("nfc"),
		new StringValue("compose")
	    };
	StringValue output = (StringValue) normalizer.call(null, arguments);
	// the composed string is as long as the input string which has a composed character
	assertEquals(input.length(), output.getContent().length());
    }

}
