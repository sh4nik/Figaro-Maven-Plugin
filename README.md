# Figaro Maven Plugin

Figaro is a utility which can be used to update environment specific application configurations at build-time.

Any line in your project files which starts with the expected token format (`@@<token-id>`) will be replaced with a predefined text. These **token-ids** and their respective values can be configured as follows:

*Note: **token-ids** can have either a single replacement value or multiple environment-specific replacement values*

Create a configuration file named `figaro.json` in your classpath. Here's a sample configuration file.
```javascript
{
  "files": [
   {
      "path": "dummy_app/web_component/conf/context.xml",
      "configs": {
        "xfilepath": "<entry path=\"myfolder/otherfolder/file.ext\">"
      }
    },
    {
      "path": "dummy_app/scheduler/db.properties",
      "configs": {
        "db.host": {
          "dev": "db.host=devsvr",
          "qa": "db.host=qasvr",
          "prod": "db.host=prodsvr"
        }
    }
  ]
}
```

To enable Figaro in your application, add the maven plugin to your `pom.xml` file as follows:
```xml
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
```