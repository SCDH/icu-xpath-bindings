#!/bin/sh

SCRIPT=$(realpath "$0")
SCRIPTPATH=$(dirname "$SCRIPT")

JARS=$SCRIPTPATH/bindings/target/lib/Saxon-HE-${saxon.version}.jar
JARS=$JARS:$SCRIPTPATH/bindings/target/lib/xmlresolver-${xmlresolver.version}.jar
JARS=$JARS:$SCRIPTPATH/bindings/target/lib/slf4j-api-${slf4j.version}.jar
JARS=$JARS:$SCRIPTPATH/bindings/target/lib/slf4j-simple-${slf4j.version}.jar
JARS=$JARS:$SCRIPTPATH/bindings/target/lib/icu4j-${icu4j.version}.jar
JARS=$JARS:$SCRIPTPATH/bindings/target/lib/icu4j-charset-${icu4j.version}.jar
JARS=$JARS:$SCRIPTPATH/bindings/target/lib/icu4j-localespi-${icu4j.version}.jar
JARS=$JARS:$SCRIPTPATH/bindings/target/${project.artifactId}-${project.version}.jar

java -Ddebug="true" -Dorg.slf4j.simpleLogger.defaultLogLevel=debug -cp $JARS $@
