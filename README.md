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

### Normalize

```{xpath}
icu:normalize(input as xs:string, normalizer as xs:string, mode as xs:string) as xs:string
```
Returns the `input` string in an equivalent, but normalized form.

Arguments:
- `input` is your input string to be normalized
- `normalizer` is the abbreviated name one of the normalizers
  described in the [ICU normalize
  docs](https://unicode-org.github.io/icu/userguide/transforms/normalization/),
  e.g. `nfc`
- `mode` is one of `decompose`, `compose`, `compose_contiguous` or
  `fcd`, as described in the referenced docs.

See [ICU
documentation](](https://unicode-org.github.io/icu/userguide/transforms/normalization/). Bound
to
[`Normalizer2.getInstance()`](https://unicode-org.github.io/icu-docs/apidoc/released/icu4j/com/ibm/icu/text/Normalizer2.html#getInstance-java.io.InputStream-java.lang.String-com.ibm.icu.text.Normalizer2.Mode-).

### Transliterate

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
documentation](](https://unicode-org.github.io/icu/userguide/transforms/general/). Bound
to
[`Transliterator.getInstance()`](https://unicode-org.github.io/icu-docs/apidoc/released/icu4j/com/ibm/icu/text/Transliterator.html#getInstance-java.lang.String-).


```{xpath}
icu:transliterator-ids() as xs:string*
```

Returns a sequence of IDs of the available
[transliterators](https://unicode-org.github.io/icu/userguide/transforms/general/#icu-transliterators).

Bound to
[`Transliterator.getAvailableIds()`](https://unicode-org.github.io/icu-docs/apidoc/released/icu4j/com/ibm/icu/text/Transliterator.html#getAvailableIDs--).


## Further Reading

- [extension functions](https://www.saxonica.com/html/documentation11/extensibility/extension-functions-J/ext-full-J.html)

- [strip accents with ICU
  transliterator](https://stackoverflow.com/questions/2992066/code-to-strip-diacritical-marks-using-icu)
