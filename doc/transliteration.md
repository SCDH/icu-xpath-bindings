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

```{xsl}
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
