package com.datamirage;

import com.datamirage.locale.DataMirageLocale;

/**
 * A builder class for creating DataMirage instances with fluent API.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * DataMirage dataMirage = DataMirage.builder()
 *     .locale(DataMirageLocale.EN_US)
 *     .seed(12345L)
 *     .build();
 * }
 * </pre>
 * </p>
 */
public class DataMirageBuilder {
    private DataMirageLocale locale = DataMirageLocale.TR_TR;
    private Long seed;

    /**
     * Sets the locale for the DataMirage instance.
     *
     * @param locale The locale to use for generating localized fake data
     * @return This builder instance for method chaining
     */
    public DataMirageBuilder locale(DataMirageLocale locale) {
        this.locale = locale;
        return this;
    }

    /**
     * Sets the seed for the random number generator.
     * When a seed is set, the DataMirage instance will produce reproducible results.
     *
     * @param seed The seed value for the random number generator
     * @return This builder instance for method chaining
     */
    public DataMirageBuilder seed(long seed) {
        this.seed = seed;
        return this;
    }

    /**
     * Builds and returns a new DataMirage instance with the configured settings.
     *
     * @return A new DataMirage instance
     */
    public DataMirage build() {
        return new DataMirage(locale, seed);
    }
}
