package space.b00tload.utils.configuration;

import space.b00tload.utils.configuration.exceptions.ConfigException;
import space.b00tload.utils.configuration.exceptions.ConfigIncompleteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Loads the configuration from all supported sources (cli-args, .env, toml-file, default).
 *
 * @author Alix von Schirp
 * @version 1.0.0
 * @since 1.0.0
 */
public class Configuration {

    /**
     * The singleton instance
     */
    private static Configuration INSTANCE;
    /**
     * Holds all configured values
     */
    private final HashMap<ConfigValues, String> values = new HashMap<>();

    /**
     * Loads all values from all config sources. Errors out if values are missing.
     *
     * @param args                   The cli-args provided at software launch
     * @param currentSoftwareVersion The current version number of the software using this util
     * @param applicationBaseDir     The directory in which the config folder and file are to be created
     * @param configValues           The enum.values() array of the ConfigValues enum.
     * @throws ConfigIncompleteException if not all values without a default value were set using other configuration methods.
     */
    private Configuration(String[] args, String currentSoftwareVersion, String applicationBaseDir, ConfigValues[] configValues) throws ConfigIncompleteException {
        //load values set in cli-args
        for (int i = 0; i < args.length; i++) {
            for (ConfigValues v : configValues) {
                if (v.getFlag().equalsIgnoreCase(args[i]) || v.getFlagAlias().equals(args[i])) {
                    if (args[i + 1].startsWith("-")) continue;
                    values.put(v, args[i + 1]);
                    i++;
                }
            }
        }

        //load yet unset values from .env
        for (ConfigValues v : configValues) {
            if (values.containsKey(v)) continue;
            if (System.getenv().containsKey(v.getEnvironmentVariable()))
                values.put(v, System.getenv(v.getEnvironmentVariable()));
        }

        //load yet unset values from toml config file
        try (TomlConfiguration tomlConfig = new TomlConfiguration(applicationBaseDir, currentSoftwareVersion)) {
            for (ConfigValues v : configValues) {
                if (values.containsKey(v)) continue;
                if (tomlConfig.contains(v.getTomlPath())) values.put(v, tomlConfig.getString(v.getTomlPath()));
            }
        }

        //load default values for as of yet unset methods
        for (ConfigValues v : configValues) {
            if (values.containsKey(v)) continue;
            values.put(v, v.getDefaultValue());
        }

        //find all unset config values
        List<ConfigValues> missingValues = new ArrayList<>();
        for (ConfigValues v : values.keySet()) {
            if (values.get(v) == null) missingValues.add(v);
        }

        //Error out on incomplete config
        if (!missingValues.isEmpty()) throw new ConfigIncompleteException("Missing values: ", missingValues);
    }

    /**
     * Used to get the singleton instance
     *
     * @return the singleton instance.
     */
    public static Configuration getInstance() {
        if (INSTANCE == null) throw new ConfigException("Configuration not initialized");
        return INSTANCE;
    }

    /**
     * Initializes the singelton instance.
     *
     * @param args                   The cli-args provided at software launch
     * @param currentSoftwareVersion The current version number of the software using this util
     * @param applicationBaseDir     The directory in which the config folder and file are to be created
     * @param configValues           The enum.values() array of the ConfigValues enum.
     * @throws ConfigIncompleteException if not all values without a default value were set using other configuration methods.
     */
    public static void init(String[] args, String currentSoftwareVersion, String applicationBaseDir, ConfigValues[] configValues) throws ConfigIncompleteException {
        INSTANCE = new Configuration(args, currentSoftwareVersion, applicationBaseDir, configValues);
    }

    /**
     * Gets a value from the config
     *
     * @param v the key of the value to be returned
     * @return the requested value
     */
    public String get(ConfigValues v) {
        return values.get(v);
    }
}
