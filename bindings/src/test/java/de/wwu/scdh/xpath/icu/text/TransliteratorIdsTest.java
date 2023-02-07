package de.wwu.scdh.xpath.icu.text;

import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;

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
import net.sf.saxon.s9api.*;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import de.wwu.scdh.xpath.icu.IcuXPathFunctionRegistry;

public class TransliteratorIdsTest {

    private static final String XSLT = Paths.get("src", "test", "resources",  "xsl", "transliterator-ids.html").toFile().toString();

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

    @Test
    public void testInXslt() throws SaxonApiException {
	// set up processor and register extension functions
	Processor processor = new Processor(false);
	IcuXPathFunctionRegistry.register(processor);

	Source stylesheet = new StreamSource(XSLT);
	XsltCompiler compiler = processor.newXsltCompiler();
	XsltExecutable executable = compiler.compile(stylesheet);
	Xslt30Transformer transformer = executable.load30();

	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	// we are using the stylesheet as xml source, too
	transformer.transform(stylesheet, processor.newSerializer(outputStream));

	// we just assert that the output is not empty
	assertFalse(outputStream.toString().isEmpty());
	//assertEquals("", outputStream.toString());
    }

}
