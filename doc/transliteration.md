# Transliteration

Transliterations are very powerful. ICU first used them to
transliterate from one script to another, e.g. from Latin script to
Arabic script. But it can do much more, e.g. decomposing compound
characters, removing some diacritics, and re-composing characters.


## `icu:transliterate`

```{xpath}
icu:transliterate(input as xs:string, transliterator-ID as xs:string) as xs:string
```
Transliterates the input string with a ICU transliterator.

Arguments:
- `input` is your input string to be transliterated
- `transliterator-ID` is the ID of a transliterator or a sequence of
  IDs separated with semicolon, e.g. `NFD; [:nonspacing mark:] Remove;
  NFC` for removing diacritics

See [ICU
documentation](https://unicode-org.github.io/icu/userguide/transforms/general/).

Bound to
[`Transliterator.getInstance(...)`](https://unicode-org.github.io/icu-docs/apidoc/released/icu4j/com/ibm/icu/text/Transliterator.html#getInstance-java.lang.String-).


## `icu:transliterator-from-rules`

```{xpath}
icu:transliterator-from-rules(ID as xs:string, rules as xs:string, direction as xs:string) as xs:boolean
```

Creates a new custom transliterator from the given rules and register
it under the ID for subsequent calls via
[`icu:transliterate`](#icutransliterate).

Arguments:
- `ID` is the identifier via which the transliterator can be called in
  subsequent calls.
- `rules` are the custom rules the transliterator is created from.
- `direction` is either the string 'FORWARD' or the string 'REVERSE'.

See [ICU documentation](https://unicode-org.github.io/icu/userguide/transforms/general/#rule-based-transliterators).

Bound to
[`Transliterator.createFromRules(...)`](https://unicode-org.github.io/icu-docs/apidoc/released/icu4j/com/ibm/icu/text/Transliterator.html#createFromRules-java.lang.String-java.lang.String-int-).


## `icu:transliterator-ids`

```{xpath}
icu:transliterator-ids() as xs:string*
```

Returns a sequence of IDs of the available
[transliterators](https://unicode-org.github.io/icu/userguide/transforms/general/#icu-transliterators).

Bound to
[`Transliterator.getAvailableIds()`](https://unicode-org.github.io/icu-docs/apidoc/released/icu4j/com/ibm/icu/text/Transliterator.html#getAvailableIDs--).


## Examples

### Removal of Diacritics

Here's an XSLT stylesheet that outputs plain text and removes
diacritics from all characters in the text nodes:

```{xslt}
<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:icu="https://unicode-org.github.io/icu/"
    version="2.0">

   <xsl:output method="text"/>

   <xsl:param name="transliterator" as="xs:string" select="'NFD; [:nonspacing mark:] Remove; NFC'"/>

   <xsl:template match="text()">
      <xsl:value-of select="icu:transliterate(., $transliterator)"/>
   </xsl:template>

</xsl:stylesheet>
```

Applied on the following XML source file it outputs: `Parlez vous
francais?`, francais without the cedilla:

```{xml}
<t>Parlez vous français?</t>
```

The important part is the compound transliterator `'NFD; [:nonspacing
mark:] Remove; NFC`. It consists of 3 transliterators.

The first one, `NFD` normalizes the input by decomposing compound
characters. The result is that c with the cedilla result in two
unicode characters. The bare cedilla is a non-spacing character.

The second one, `Remove` is applied only on non-spacing characters. So
the result of this step is the string with the cedilla removed.

The third one, `NFC` normalizes by (re-)composing characters and
non-spacing characters again. – We could have applied the removal only
onto a subset of non-spacing characters.

A common use case of such stripping diacritics include sorting in
lexical order or matching strings against a list of strings where
diacritics are not consistently used. In both cases stripping
diacritics improves the result.

Such a matching case in an upcycling task was the reason why this
library was first developed.

### A Custom Transliterator

You can define your own transliterator, register it under an ID, and
then use it in subsequent calls to transliterate strings. Here comes a
somewhat constructed example, maybe usable for an author that wants to
write a novel without e. He's not good as [Georges
Perec](https://en.wikipedia.org/wiki/A_Void), because it sails around
the e quite mechanically...

```{xslt}
<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:icu="https://unicode-org.github.io/icu/"
    version="2.0">

   <xsl:output method="text"/>

   <xsl:param name="transliterator" as="xs:string" select="'custom'"/>

   <xsl:param name="rules" as="xs:string" required="true"/>

   <xsl:param name="direction" as="xs:string" select="'forward'"/>

   <xsl:template match="/">
      <xsl:variable name="custom-transliterator" select="icu:transliterator-from-rules('custom', unparsed-text($rules), $direction)"/>
      <xsl:apply-templates/>
   </xsl:template>

   <xsl:template match="text()">
      <xsl:value-of select="icu:transliterate(., $transliterator)"/>
   </xsl:template>

</xsl:stylesheet>
```

He uses an external file named `void.txt` with rules for the `custom`
transliterator:

```{txt}
e > a;
```

He translates his novel


```{xml}
<t>Cette été ...</t>
```

Now, running the following command

```{shell}
./xslt.sh -config:saxon-config.xml -xsl:doc/transliterator-from-external-rules.xsl -s:doc/novel.xml rules=void.txt transliterator="NFD;custom;NFC"
```

results in

```{xml}
Catta átá ...
```

What goes on here? Compound characters are first decomposed by
`NFD`. Then e is replaced with a by the custom transliterator. Then
non-spacing accents are (re-)composed again.

Note, that when defining a custom transliterator with
`icu:transliterator-from-rules(...)`, this function has to be called
before calls to `icu:transliterate(...)` which makes use of it. Also
be aware of the lazy evaluation of XSLT: A call of the function in a
global `<xsl:variable>` won't get evaluated before the variable is
needed for the output of the transformation; the transliterator is not
stored in the variable, but as a side effect in Java transliterator
registry. If `$custom-transliterator` would have been defined as a
global variable, the call of `icu:transliterate(...)` would have been
resulted in an exception like this:

```{txt}
Error at char 19 in expression in xsl:value-of/@select on line 23 column 69 of transliterator-from-external-rules.xsl:
   illegal transliterator ID for icu:transliterate(..., NFD;custom;NFC)
```

### Listing Translators Available in ICU4J

[`transliterator-ids.html`](transliterator-ids.html) shows how
to use `icu:transliterator-ids()` to get available transliterators. There
are several hundred! The list can be build with the following command:

```{shell}
mvn package
./xslt.sh -config:saxon-config.xml -xsl:doc/transliterator-ids.html -s:pom.xml
```


### Task

Write a transliterator for Arabic script to Latin script that gets
vowels right.
