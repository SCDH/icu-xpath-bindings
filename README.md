# ICU XPath Bindings

This project provides XPath bindings of the
[ICU](https://unicode-org.github.io/icu/) library for processing
common Unicode tasks. It's based on the ICU library for Java (ICU4J)
and can be used in the [Saxon XSLT/XQuery](https://www.saxonica.com)
processor.

The bindings only use a small set of the ICU library. Other parts may
be added in future, if they are needed. XPath functions for the
following tasks are provided:

- normalization
- transliteration

## Functions

The namespace name of the XPath extension functions is
`https://unicode-org.github.io/icu/`. In this documentation, we are
using the prefix `icu` bound to this namespace:
`xmlns:icu="https://unicode-org.github.io/icu/"`.

- normalization
  - [`icu:normalize(input as xs:string, normalizer as xs:string, mode as xs:string) as xs:string`](doc/normalization.md#icunormalize)
- transliteration
  - [`icu:transliterate(input as xs:string, transliterator-ID as
    xs:string) as xs:string`](doc/transliteration.md#icutransliterate)
  - [`icu:transliterator-from-rules(ID as xs:string, rules as xs:string, direction as xs:string) as xs:boolean`](doc/transliteration.md#icutransliterator-from-rules)
  - [`icu:transliterator-ids() as xs:string*`](doc/transliteration.md#icutransliterator-ids)


## Usage

Two things are necessary:

1. Tell Saxon that there are XPath functions. This can be done via a
   [Saxon configuration
   file](https://www.saxonica.com/html/documentation11/configuration/configuration-file/). Such
   a configuration is in [`saxon-config.xml`](saxon-config.xml). You
   can use it from the Saxon command line interface via the argument
   `-config:saxon-config.xml`. When using Java, you should also have a
   look at the
   [`IcuXPathFunctionRegistry.register(Processor)`](bindings/src/main/java/de/wwu/scdh/xpath/icu/IcuXPathFunctionRegistry.java).

2. Provide the jar file to the classpath, so that the java classes
   that define the functions are available to Saxon. Dependency
   packages like ICU4J also have to be included into the classpath.

Note, that after you have run `mvn package` the jar file is present in
`target` and the dependency packages are present in
`target/lib/`. After running this command, there will also be a shell
script `xslt.sh` which is a shell wrapper around Saxon that sets the
class path correctly. You can take it as an example for setting the
classpath. Here is a list of the dependency packages definitively
required:

- icu4j
- icu4j-charset
- icu4j-localespi
- slf4j-api



## Further Reading

- [extension functions](https://www.saxonica.com/html/documentation11/extensibility/extension-functions-J/ext-full-J.html)

- [strip accents with ICU
  transliterator](https://stackoverflow.com/questions/2992066/code-to-strip-diacritical-marks-using-icu)
