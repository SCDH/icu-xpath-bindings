# ICU XPath Bindings

This project provides XPath bindings of the
[ICU](https://unicode-org.github.io/icu/) Java library for processing
Unicode. It can be used in the [Saxon
XSLT/XQuery](https://www.saxonica.com) processor.

## Functions

The namespace name of the XPath extension functions is
`https://unicode-org.github.io/icu/`. We are using the prefix `icu`
bound to this namespace:

```{xpath}
icu:normalize(input as xs:string, normalizer as xs:string, mode as xs:string) as xs:string
```
where
- `input` is your input string to be normalized
- `normalizer` is the abbreviated name one of the normalizers
  described in the [ICU normalize
  docs](https://unicode-org.github.io/icu/userguide/transforms/normalization/),
  e.g. `nfc`
- `mode` is one of `decompose`, `compose`, `compose_contiguous` or
  `fcd`, as described in the referenced docs.


## Further Reading

- [extension functions](https://www.saxonica.com/html/documentation11/extensibility/extension-functions-J/ext-full-J.html)

- [strip accents with ICU
  transliterator](https://stackoverflow.com/questions/2992066/code-to-strip-diacritical-marks-using-icu)
