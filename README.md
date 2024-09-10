# java-check-class-version
A command line tool to check the [major version](https://docs.oracle.com/javase/specs/jvms/se12/html/jvms-4.html) of classes contained in a jar.

Please note that it does not check that the classes are compatible with the java api of a specific version.

## Requirements
It requires java 8+ JRE.

## Download
The artifact is published in ![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.fathzer/java-check-class-version).

## Usage
'java -jar java-check-class-version-0.0.1.jar file version [options]'

**file** is the name of the jar file to check.
**version** is the maximum major class version allowed.

Available options:
- **-i** : *module-info.class* and *package-info.class* are ignored.
- **-j** : The version parameter is not a major class version, but a java version.

The **exit code** of the command is 0 all classes successfully pass the test.

The following command will check compatibility with java 8:  
'java -jar java-check-class-version-0.0.1.jar myjar.jar 8 -i -j'

The following command will check compatibility with java 11:  
'java -jar java-check-class-version-0.0.1.jar myjar.jar 55'

## Limitations
[Multi release jar](https://openjdk.org/jeps/238) are not supported.
