package space.b00tload.utils.configuration;

import com.electronwill.nightconfig.core.file.FileConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.b00tload.utils.configuration.exceptions.ConfigException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Interfaces with the toml config file.
 */
public class TomlConfiguration implements AutoCloseable {

    private static FileConfig config;
    private static Logger LOGGER;

    /**
     * Loads the config file.
     *
     * @param APPLICATION_BASE       The directory in which the config folder and file are to be created
     * @param currentSoftwareVersion The current version number of the software using this util
     */
    TomlConfiguration(String APPLICATION_BASE, String currentSoftwareVersion) {
        LOGGER = LoggerFactory.getLogger(TomlConfiguration.class);
        Path configBase = Paths.get(APPLICATION_BASE, "config");
        if (!configBase.toFile().exists()) //noinspection ResultOfMethodCallIgnored
            configBase.toFile().mkdirs();
        config = FileConfig.builder(Paths.get(String.valueOf(configBase), "config.toml")).defaultResource("/empty.toml").autosave().autoreload().build();
        config.load();
        checkVersion(currentSoftwareVersion);
    }

    /**
     * Saves and closes the config file.
     */
    public void close() {
        config.save();
        config.close();
    }

    /**
     * Checks whether the config version is the same as software version. Sends a warning to stderr if version was updated.
     * @param currentVersion The current software version.
     */
    public void checkVersion(String currentVersion) {
        String configVersion = getString("file.version");
        if (currentVersion == null) {
            LOGGER.error("Error: Failed to retrieve current version. Assuming 0.0.1");
            currentVersion = "0.0.1";
        }
        if (configVersion == null) {
            throw new ConfigException("Invalid config. \"file.version\" is not set.");
        } else if (configVersion.equals("0.0.0")) {
            LOGGER.info("New installation detected. Creating config file.");
            setString("file.version", currentVersion);
        } else if (currentVersion.compareTo(configVersion) < 0) {
            LOGGER.warn("Software updated. Please check the wiki if and how to migrate.");
            setString("file.version", currentVersion);
        }
    }

    /**
     * Checks if the config contains a value at some path.
     * @param key the path to check, each part separated by a dot. Example "a. b. c"
     * @return <code>true</code> if the path is associated with a value, <code>false</code> if it's not.
     */
    public boolean contains(String key) {
        return config.contains(key);
    }

    /**
     * Gets a value from the config.
     * @param key the value's path, each part separated by a dot. Example "a. b. c"
     * @return the value at the given path, or <code>null</code> if there is no such value.
     */
    public String getString(String key) {
        return config.get(key);
    }

    /**
     * Sets a value to the config.
     * @param key the value's path, each part separated by a dot. Example "a. b. c"
     * @param value the value to set
     */
    public void setString(String key, String value) {
        config.set(key, value);
    }


}
