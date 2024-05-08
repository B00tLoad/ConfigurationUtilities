package space.b00tload.utils.configuration.exceptions;


import space.b00tload.utils.configuration.ConfigValues;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The <code>ConfigIncompleteException</code> is thrown when the config has missing values.
 *
 * @author Alix von Schirp
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigIncompleteException extends RuntimeException {

    /**
     * A {@code java.util.List} of {@code space.b00tload.utils.configuration.ConfigValues} for storing missing values.
     */
    private final List<ConfigValues> missingValues;

    /**
     * Constructs a new runtime exception with the specified detail message and missing values. The cause is not initialized, and may subsequently be initialized by a call to initCause.
     *
     * @param message       the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     * @param missingValues a <code>java.util.List</code> of type <code>space.b00tload.utils.configuration.ConfigValues</code> containing all missing values
     */
    public ConfigIncompleteException(String message, List<ConfigValues> missingValues) {
        super(message + System.lineSeparator() + "\t-\t" + missingValues.stream().map(Object::toString).collect(Collectors.joining(";" + System.lineSeparator() + "\t-\t")));
        this.missingValues = missingValues;
    }

    /**
     * Constructs a new runtime exception with the specified detail message, missing values and cause.
     * Note that the detail message associated with <code>cause</code> is not automatically incorporated in this runtime exception's detail message.
     *
     * @param message       the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     * @param cause         the cause (which is saved for later retrieval by the {@link #getMessage()} method).
     *                      (A <code>null</code> value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @param missingValues a <code>java.util.List</code> of type <code>space.b00tload.utils.configuration.ConfigValues</code> containing all missing values
     */
    public ConfigIncompleteException(String message, Throwable cause, List<ConfigValues> missingValues) {
        super(message + System.lineSeparator() + "\t-\t" + missingValues.stream().map(Object::toString).collect(Collectors.joining(";" + System.lineSeparator() + "\t-\t")), cause);
        this.missingValues = missingValues;
    }

    /**
     * Constructs a new runtime exception with the specified cause, the missing values and a detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for runtime exceptions that are little more than wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @param missingValues a <code>java.util.List</code> of type <code>space.b00tload.utils.configuration.ConfigValues</code> containing all missing values
     */
    public ConfigIncompleteException(Throwable cause, List<ConfigValues> missingValues) {
        super(cause);
        this.missingValues = missingValues;
    }

    /**
     * Constructs a new runtime exception with the specified detail
     * message, missing values, cause, suppression enabled or disabled, and writable
     * stack trace enabled or disabled.
     *
     * @param message the detail message.
     * @param cause the cause.  (A {@code null} value is permitted,
     * and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression whether or not suppression is enabled
     *                          or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     * @param missingValues a <code>java.util.List</code> of type <code>space.b00tload.utils.configuration.ConfigValues</code> containing all missing values
     */
    public ConfigIncompleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ConfigValues> missingValues) {
        super(message + System.lineSeparator() + "\t-\t" + missingValues.stream().map(Object::toString).collect(Collectors.joining(";" + System.lineSeparator() + "\t-\t")), cause, enableSuppression, writableStackTrace);
        this.missingValues = missingValues;
    }

    /**
     * Returns all missing values.
     * @return a {@code java.util.List} with all missing values.
     */
    public List<ConfigValues> getMissingValues() {
        return missingValues;
    }

}
