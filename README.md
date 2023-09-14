# Things I use
1. Java 17
2. maven
3. Apache poi 5.2.3
4. Jxls 2.13

# This is the library configuration
```xml
<!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
<dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi</artifactId>
  <version>5.2.3</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
<dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi-ooxml</artifactId>
  <version>5.2.3</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.jxls/jxls -->
<dependency>
  <groupId>org.jxls</groupId>
  <artifactId>jxls</artifactId>
  <version>2.13.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.jxls/jxls-poi -->
<dependency>
  <groupId>org.jxls</groupId>
  <artifactId>jxls-poi</artifactId>
  <version>2.13.0</version>
</dependency>
```
# Functions
1. Download template Excel( file in resource)
2. Export data from object to excel template by Jxls
-> Doc: https://jxls.sourceforge.net/reference/if_command.html
3. Read excel data by apache poi
