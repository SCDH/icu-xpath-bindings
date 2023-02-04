## Normalization

Normalization is used to convert text to a unique, equivalent form,
e.g. composed characters to precomposed characters. Normalization
allows for easier sorting and searching of text. (Cf. [ICU
documentation](https://unicode-org.github.io/icu/userguide/transforms/normalization/))


### icu:normalize

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
documentation](https://unicode-org.github.io/icu/userguide/transforms/normalization/). 

Bound to
[`Normalizer2.getInstance()`](https://unicode-org.github.io/icu-docs/apidoc/released/icu4j/com/ibm/icu/text/Normalizer2.html#getInstance-java.io.InputStream-java.lang.String-com.ibm.icu.text.Normalizer2.Mode-).
