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
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.ma.arrays.SimpleArrayItem;
import net.sf.saxon.s9api.*;

import de.wwu.scdh.xpath.icu.IcuXPathFunctionRegistry;

public class TransliterateTest {

    private static final String XSLT = Paths.get("src", "test", "resources", "xsl", "transliterate.xsl").toFile().toString();

    protected ExtensionFunctionCall function;

    @BeforeEach
    public void setup() {
	ExtensionFunctionDefinition functionDefinition = new Transliterate();
	function = functionDefinition.makeCallExpression();
    }

    @Test
    public void testRemoveAccentsFrench() throws XPathException {
	String input = "Parlez vous français?";
	Sequence[] arguments = new Sequence[] {
		new StringValue(input),
		new StringValue("NFD; [:nonspacing mark:] Remove; NFC")
	    };
	StringValue output = (StringValue) function.call(null, arguments);
	assertEquals("Parlez vous francais?", output.getStringValue());
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

	String sourceFile = Paths.get("src", "test", "resources", "samples", "french.xml").toFile().toString();
	Source source = new StreamSource(sourceFile);

	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	transformer.transform(source, processor.newSerializer(outputStream));

	assertEquals("Parlez vous francais?", outputStream.toString());
    }

}
