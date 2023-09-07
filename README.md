# wikipedia4j


[![build](https://github.com/llmjava/wikipedia4j/actions/workflows/main.yml/badge.svg)](https://github.com/llmjava/wikipedia4j/actions/workflows/main.yml) [![Jitpack](https://jitpack.io/v/llmjava/wikipedia4j.svg)](https://jitpack.io/#llmjava/wikipedia4j) [![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://llmjava.github.io/wikipedia4j/javadoc/)  [![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

</b>

This is a Java client library that you can use to search and retrieve documents from **Wikipedia** . It can be used in Android or any Java and Kotlin Project.


## Add Dependency

### Gradle

To use library in your gradle project follow the steps below:

1. Add this in your root `build.gradle` at the end of repositories:
    ```groovy
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    ```
2. Add the dependency
   ```groovy
   dependencies {
       def WIKIPEDIA4J_VERSION = "..."
       implementation "com.github.llmjava:wikipedia4j:$WIKIPEDIA4J_VERSION"
   }
   ```

### Maven

To use the library in your Maven project, follow the steps below:

1. Add the JitPack repository to your build file:
    ```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    ```
2. Add the dependency
    ```xml
    <dependency>
        <groupId>com.github.llmjava</groupId>
        <artifactId>wikipedia4j</artifactId>
        <version>${WIKIPEDIA4J_VERSION}</version>
    </dependency>
    ```

## Usage

Example code to use the **Wikipedia API**:

```java
class App {

   public static void main(String[] args) {
      
   }
}
```

## Build Project

Clone the repository and import as Maven project in IntelliJ IDEA or Eclipse

Before building the project, make sure you have the following things installed.

- Maven
- Java 8

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To build the library using Gradle, execute the following command

```shell
./gradlew build
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.
