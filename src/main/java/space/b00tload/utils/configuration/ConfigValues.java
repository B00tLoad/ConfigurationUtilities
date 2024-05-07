package space.b00tload.utils.configuration;


/**
 * An interface to implement in enums containing all configurable values.
 *
 * @author Alix von Schirp
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ConfigValues {

    /**
     * The command line flag for this config value.
     *
     * @return the command line flag
     * @example --discord-token
     */
    String getFlag();

    /**
     * The command line alias for this config value.
     *
     * @return the command line alias
     * @example -d
     */
    String getFlagAlias();

    /**
     * The environment variable name for this config value.
     *
     * @return the environment variable name
     * @example DISCORD_APP_TOKEN
     */
    String getEnvironmentVariable();

    /**
     * The path to the config value in a toml file.
     *
     * @return the toml path.
     * @example discord.token
     */
    String getTomlPath();

    /**
     * The default value used if not set via other configuration means.
     *
     * @return the default value.
     */
    String getDefaultValue();

}
