package de.wwu.scdh.xpath.icu.text;

import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;

import net.sf.saxon.lib.ExtensionFunctionDefinition;
import net.sf.saxon.lib.ExtensionFunctionCall;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.value.StringValue;
import net.sf.saxon.value.BooleanValue;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.ma.arrays.SimpleArrayItem;
import net.sf.saxon.s9api.*;

import de.wwu.scdh.xpath.icu.IcuXPathFunctionRegistry;

public class TransliteratorFromRulesTest {

    private static final String XSLT = Paths.get("src", "test", "resources", "xsl", "transliterator-from-rules.xsl").toFile().toString();

    protected ExtensionFunctionCall function, other;

    @BeforeEach
    public void setup() {
	ExtensionFunctionDefinition functionDefinition = new TransliteratorFromRules();
	function = functionDefinition.makeCallExpression();

    	ExtensionFunctionDefinition otherDefinition = new Transliterate();
	other = otherDefinition.makeCallExpression();
    }

    @Test
    public void testReplaceXwithY() throws XPathException {
	Sequence[] arguments = new Sequence[] {
		new StringValue("test"),
		new StringValue("x > y; x{a} > b; "),
		new StringValue("forward")
	    };
	BooleanValue success = (BooleanValue) function.call(null, arguments);
	assertTrue(success.getBooleanValue());

	// now test if we can use it to transliterate
	String input = "xa";
	Sequence[] otherArguments = new Sequence[] {
		new StringValue(input),
		new StringValue("test")
	    };
	StringValue output = (StringValue) other.call(null, otherArguments);
	assertEquals("ya", output.getContent().toString());
    }

    @Test
    public void testInXslt() throws SaxonApiException {
	// set up processor and register extension functions
	Processor processor = new Processor(false);
	IcuXPathFunctionRegistry.register(processor);

	Source stylesheet = new StreamSource(XSLT);
	XsltCompiler compiler = processor.newXsltCompiler();
	XsltExecutable executable = compiler.compile(stylesheet);
	Xslt30Transformer transformer = executable.load30();

	String sourceFile = Paths.get("src", "test", "resources", "samples", "xa.xml").toFile().toString();
	Source source = new StreamSource(sourceFile);

	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	transformer.transform(source, processor.newSerializer(outputStream));

	assertEquals("ya", outputStream.toString());
    }

}
