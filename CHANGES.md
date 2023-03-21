# Changes

## 0.3.0

- support multiple oxygen versions
  - build with version-specific Saxon and SDK releases

## 0.2.6

- fix issue #1

## 0.2.5

- fix link to release
- no changes to codebase

## 0.2.4

- setup up github actions for testing and releasing
- no changes since last release


## 0.2.3

- issue #1
  - added resources to analyse it
  - tried to set the class loader to fix it, but is not fixed yet

## 0.2.2

- a fix in the class loading in the oxygen plugin makes the extension
  functions available by installing the plugin

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
