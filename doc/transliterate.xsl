<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:icu="https://unicode-org.github.io/icu/"
    version="2.0">

   <xsl:output method="text"/>

   <xsl:param name="transliterator" as="xs:string" select="'Any-NFD; [:nonspacing mark:] Any-Remove; Any-NFC'"/>

   <xsl:template match="text()">
      <xsl:value-of select="icu:transliterate(., $transliterator)"/>
   </xsl:template>

</xsl:stylesheet>
