package de.wwu.scdh.xpath.icu;

import net.sf.saxon.s9api.Processor;

/**
 * A class that provides static fields used all over the library and
 * static methods for registrating the XPath functions.
 *
 * Note, that the registration can also be done via the Saxon
 * configuration file.
 *
 * @see https://www.saxonica.com/html/documentation11/extensibility/extension-functions-J/ext-full-J.html
 */
public class IcuXPathFunctionRegistry {

    /**
     * The default prefix of the XPath function library is <code>icu</code>.
     */
    public static final String PREFIX = "icu";

    /**
     * The default namespace of the XPath function library is
     * <code>https://unicode-org.github.io/icu/</code>.
     *
     */
    public static final String NAMESPACE = "https://unicode-org.github.io/icu/";

    /**
     * This static method registers all extension functions for a
     * given s9api {@link Processor}.
     *
     * @param processor the {@link Processor} instance
     *
     */
    static public void register(Processor processor) {
	processor.registerExtensionFunction(new de.wwu.scdh.xpath.icu.text.Normalize());
	processor.registerExtensionFunction(new de.wwu.scdh.xpath.icu.text.Transliterate());
	processor.registerExtensionFunction(new de.wwu.scdh.xpath.icu.text.TransliteratorIds());
	processor.registerExtensionFunction(new de.wwu.scdh.xpath.icu.text.TransliteratorFromRules());
    }

}
