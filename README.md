
# ConfigurationUtilities

A java library to load config from cli-args, .env, toml-config and default values.

![GitHub License](https://img.shields.io/github/license/B00tLoad/ConfigurationUtilities)
![GitHub Issues or Pull Requests](https://img.shields.io/github/issues/B00tLoad/ConfigurationUtilities)
![GitHub repo size](https://img.shields.io/github/repo-size/B00tLoad/ConfigurationUtilities)
![Maven Central Version](https://img.shields.io/maven-central/v/space.b00tload.utils/ConfigurationUtilities)




## Acknowledgements
This project depends on the following libraries:

 - org.slf4j:slf4f-api
 - com.electronwill.night-config:toml


## Installation

Install ConfigurationUtilities with Maven

```xml
    <dependency>
      <groupId>space.b00tload.utils</groupId>
      <artifactId>ConfigurationUtilities</artifactId>
      <version>1.0.0</version>
    </dependency>
```
    
## Usage/Examples
Create an enum implementing the interface `space.b00tload.utils.configuration.ConfigValues` (e.g. as shown below).
```java
public enum ConfigurationValues implements ConfigValues {

    DISCORD_TOKEN("discord-token", "db", "DISCORD_BOT_TOKEN", "discord.token.bot", null),
    DISCORD_APP_ID("discord-app-id", "da", "DISCORD_APPLICATION_ID", "discord.token.applicationid", null),
    DISCORD_PUB_KEY("discord-public-key", "dp", "DISCORD_PUBLIC_KEY", "discord.token.publickey", null),
    W2G_TOKEN("w2g-token", "w", "W2G_API_TOKEN", "w2g.token", null),
    ENDPOINT_PORT("webserver-port", "p", "WEBSERVER_PORT", "webserver.port", "86542"),
    ;

    private final String flag, flagAlias, env, toml, defaultValue;

    ConfigValues(String flag, String flagAlias, String env, String toml, String defaultValue) {
        this.flag = flag;
        this.flagAlias = flagAlias;
        this.env = env;
        this.toml = toml;
        this.defaultValue = defaultValue;
    }

    [... implement override methods]

    @Override
    public String toString() {
        return "ConfigValues{" +
                ", defaultValue='" + defaultValue + '\'' +
                "tomlPath='" + toml + '\'' +
                ", environmentVarName='" + env + '\'' +
                ", flagAlias='" + flagAlias + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
```

Afterwards initialize the Configuration using `Configuration.init(args, SOFTWARE_VERSION, APPLICATION_BASE, ConfigurationValues.values());`.
- args = args passed to main-methods
- SOFTWARE_VERSION = the version of your software
- APPLICATION_BASE = the base directory for app data (config.toml will be saved in APPLICATION_BASE/config/)
- ConfigurationValues.values() = values() method of the enum

## License

[GNU GPL v3](https://github.com/B00tLoad/ConfigurationUtilities/blob/master/LICENSE)


## Maintainers

- [@B00tLoad](https://www.github.com/B00tLoad)


## Support

For support, open an issue or contact me at alix (at) ja-lol-ey (dot) de.

