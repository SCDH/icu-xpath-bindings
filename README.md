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

## XPath Functions

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

## Getting started

For getting started have a look at the example sections in the
[transliteration](doc/transliteration.md) and
[normalization](doc/normalization.md) documentation.


## Installation

### oXygen XML Editor

Installation for the oXygen XML editor is very simple. You only have
to provide the following URL to the installation dialog from **Help**
-> **Install new add-ons...**:

```
https://scdh.github.io/icu-xpath-bindings/descriptor.xml
```

Note: As we don't have a key for signing the extension, we will have
to proceed anyway at some stage of the installation process.

After the installation, you can use the new XPath function everywhere
in oXygen. You don't need to clone this repo.

### Usage with Saxon's command line interface

Two things are necessary:

1. Tell Saxon that there are XPath functions. This can be done via a
   [Saxon configuration
   file](https://www.saxonica.com/html/documentation11/configuration/configuration-file/). Such
   a configuration is in [`saxon-config.xml`](saxon-config.xml). You
   can use it from the Saxon command line interface via the argument
   `-config:saxon-config.xml`.

2. Provide the [jar file]() to the classpath, so that the Java classes
   that define the functions are available to Saxon. Dependency
   packages like ICU4J also have to be included into the classpath.

- icu4j
- icu4j-charset
- icu4j-localespi
- slf4j-api

You can get the dependency jar files manually through [Maven
Central](https://mvnrepository.com/repos/central) or you can clone
this git repository and run the [Maven](https://maven.apache.org/)
build process, which downloads and builds everything for you
automatically:

```{shell}
mvn package
```

After you have run `mvn package` all the required jar files are
present within the project:

- `bindings/target/icu-xpath-bindings-VERSION.jar`
- `bindings/target/lib/icu4j-VERSION.jar`
- `bindings/target/lib/icu4j-charset-VERSION.jar`
- `bindings/target/lib/icu4j-localespi-VERSION.jar`
- `bindings/target/lib/slf4j-api-VERSION.jar`

For convenience, after running `mvn package` there will also be the
shell script `xslt.sh` in the repo's root folder. It's a shell wrapper
around Saxon that sets the classpath correctly.


### Java

When using Java, you should also have a look at the
[`IcuXPathFunctionRegistry.register(Processor)`](bindings/src/main/java/de/wwu/scdh/xpath/icu/IcuXPathFunctionRegistry.java). Moreover,
the classes with the function definition are registered for loading
through the SPI.



## Further Reading

- [extension functions](https://www.saxonica.com/html/documentation11/extensibility/extension-functions-J/ext-full-J.html)

- [strip accents with ICU
  transliterator](https://stackoverflow.com/questions/2992066/code-to-strip-diacritical-marks-using-icu)
