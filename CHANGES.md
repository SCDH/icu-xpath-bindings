# Changes

## 0.2.1

- assemble dependencies into a single jar file for the oxygen plugin

## 0.2.0

- provide XPath extension functions through the SPI
- delivery as oXygen plugin
- made this a multi-module maven project

## 0.1.0

- implementation of
  - [`icu:normalize(input as xs:string, normalizer as xs:string, mode as xs:string) as xs:string`](doc/normalization.md#icunormalize)
  - [`icu:transliterate(input as xs:string, transliterator-ID as
    xs:string) as xs:string`](doc/transliteration.md#icutransliterate)
  - [`icu:transliterator-from-rules(ID as xs:string, rules as xs:string, direction as xs:string) as xs:boolean`](doc/transliteration.md#icutransliterator-from-rules)
  - [`icu:transliterator-ids() as xs:string*`](doc/transliteration.md#icutransliterator-ids)