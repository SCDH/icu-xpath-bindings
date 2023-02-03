#!/bin/sh

java -Ddebug="true" -cp target/lib/Saxon-HE-${saxon.version}.jar:target/lib/xmlresolver-${xmlresolver.version}.jar:target/lib/slf4j-api-${slf4j.version}.jar:target/lib/slf4j-simple-${slf4j.version}.jar:target/lib/icu4j-${icu4j.version}.jar:target/lib/icu4j-charset-${icu4j.version}.jar:target/lib/icu4j-localespi-${icu4j.version}.jar:target/${project.artifactId}-${project.version}.jar net.sf.saxon.Transform $@
