#!/bin/sh

java -Ddebug="true" -Dorg.slf4j.simpleLogger.defaultLogLevel=debug -cp bindings/target/lib/Saxon-HE-${saxon.version}.jar:bindings/target/lib/xmlresolver-${xmlresolver.version}.jar:bindings/target/lib/slf4j-api-${slf4j.version}.jar:bindings/target/lib/slf4j-simple-${slf4j.version}.jar:bindings/target/lib/icu4j-${icu4j.version}.jar:bindings/target/lib/icu4j-charset-${icu4j.version}.jar:bindings/target/lib/icu4j-localespi-${icu4j.version}.jar:bindings/target/${project.artifactId}-${project.version}.jar net.sf.saxon.Transform $@
