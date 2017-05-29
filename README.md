# Figaro Maven Plugin

Figaro is a utility which can be used to update environment specific application configurations at build-time.

How to use:
  * First, replace your environment specific configurations with a token in the form of `@@[<token-id>]` (example: **@@[db.user]**)
  * Next, configure their respective replacement values as follows:

Note: **token-ids** can have either a single replacement value or multiple environment-specific replacement values

Create a configuration file named `figaro.json` in your classpath. Here's a sample configuration file.
```javascript
{
  "files": [
   {
      "path": "dummy_app/web_component/conf/context.xml",
      "configs": {
        "xfilepath": "myfolder/otherfolder/file.ext"
      }
    },
    {
      "path": "dummy_app/scheduler/db.properties",
      "configs": {
        "db.user": {
          "dev": "devuser",
          "prod": "produser"
        }
    }
  ]
}
```

How to enable Figaro in your application:
  * Create build profiles for each of your environments in your Maven `pom.xml` file
  * Add the plugin to each of them passing the relevant environment name

When building your project, if you do not specify the required profile (ex: `mvn -Pprod install`) the default profile (**dev** in the following example) will be executed.

```xml
<profiles>
   <profile>
      <id>dev</id>
      <activation>
         <activeByDefault>true</activeByDefault>
      </activation>
      <build>
         <plugins>
            <plugin>
               <groupId>com.virtusa.gto</groupId>
               <artifactId>figaro-maven-plugin</artifactId>
               <version>1.0-SNAPSHOT</version>
               <executions>
                  <execution>
                     <phase>compile</phase>
                     <configuration>
                        <env>dev</env>
                     </configuration>
                     <goals>
                        <goal>run</goal>
                     </goals>
                  </execution>
               </executions>
            </plugin>
         </plugins>
      </build>
   </profile>
   <profile>
      <id>prod</id>
      <build>
         <plugins>
            <plugin>
               <groupId>com.virtusa.gto</groupId>
               <artifactId>figaro-maven-plugin</artifactId>
               <version>1.0-SNAPSHOT</version>
               <executions>
                  <execution>
                     <phase>compile</phase>
                     <configuration>
                        <env>prod</env>
                     </configuration>
                     <goals>
                        <goal>run</goal>
                     </goals>
                  </execution>
               </executions>
            </plugin>
         </plugins>
      </build>
   </profile>
</profiles>
```