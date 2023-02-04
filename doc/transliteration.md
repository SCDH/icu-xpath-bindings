## Transliteration

Transliterations are very powerful. ICU first used them to
transliterate from one script to another, e.g. from Latin script to
Arabic script. But it can do much more, e.g. decomposing compound
characters, removing some diacritics, and re-composing characters.


### `icu:transliterate`

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
[`Transliterator.getInstance()`](https://unicode-org.github.io/icu-docs/apidoc/released/icu4j/com/ibm/icu/text/Transliterator.html#getInstance-java.lang.String-).


### `icu:transliterator-ids`

```{xpath}
icu:transliterator-ids() as xs:string*
```

Returns a sequence of IDs of the available
[transliterators](https://unicode-org.github.io/icu/userguide/transforms/general/#icu-transliterators).

Bound to
[`Transliterator.getAvailableIds()`](https://unicode-org.github.io/icu-docs/apidoc/released/icu4j/com/ibm/icu/text/Transliterator.html#getAvailableIDs--).
