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
      <xsl:variable name="transliterator" select="icu:transliterator-from-rules('custom', unparsed-text($rules), $direction)"/>
      <xsl:apply-templates/>
   </xsl:template>

   <xsl:template match="text()">
      <xsl:value-of select="icu:transliterate(., $transliterator)"/>
   </xsl:template>

</xsl:stylesheet>
