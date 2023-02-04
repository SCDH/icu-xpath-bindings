# ICU XPath Bindings

This project provides XPath bindings of the
[ICU](https://unicode-org.github.io/icu/) Java library for processing
Unicode. It can be used in the [Saxon
XSLT/XQuery](https://www.saxonica.com) processor.

## Namespace

The namespace name of the XPath extension functions is
`https://unicode-org.github.io/icu/`. We are using the prefix `icu`
bound to this namespace in the following section:
`xmlns:icu="https://unicode-org.github.io/icu/"`

## Functions

- [`icu:normalize(input as xs:string, normalizer as xs:string, mode as xs:string) as xs:string`](doc/normalization.md)
- [`icu:transliterate(input as xs:string, transliterator-ID as xs:string) as xs:string`](doc/transliteration.md)
- [`icu:transliterator-ids() as xs:string*`](doc/transliteration.md)

## Usage

Two things are necessary:

1. Tell Saxon that there are XPath functions. This can be done via a
   [Saxon configuration file](). Such a configuration is in
   [`saxon-config.xml`](saxon-config.xml). You can use it from the
   Saxon command line interface via the argument `-config
   saxon-config.xml`. When using Java, you should also have a look at
   the
   [`IcuXPathFunctionRegistry.register(Processor)`](src/main/java/de/wwu/scdh/xpath/icu/IcuXPathFunctionRegistry.java).

2. Provide a jar file to the classpath, so that the java classes that
   define the functions are available to Saxon. Dependency packages
   like ICU4J also have to be included into the classpath.

Note, that the dependency packages are present in `target/lib/` after
you have run `mvn package`. After running this command, there will
also be a shell script `xslt.sh` which is a shell wrapper around Saxon
that sets the class path correctly. You can take it as an example for
setting the classpath.


## Further Reading

- [extension functions](https://www.saxonica.com/html/documentation11/extensibility/extension-functions-J/ext-full-J.html)

- [strip accents with ICU
  transliterator](https://stackoverflow.com/questions/2992066/code-to-strip-diacritical-marks-using-icu)
