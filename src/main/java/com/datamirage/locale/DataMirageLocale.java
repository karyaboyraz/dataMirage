package com.datamirage.locale;

/**
 * An enum representing supported locales for fake data generation.
 * Each locale corresponds to a specific language and country combination,
 * and is used to load locale-specific data for generating fake information.
 */
public enum DataMirageLocale {
    /** Turkish (Turkey) locale */
    TR_TR("tr_TR"),
    /** English (United States) locale */
    EN_US("en_US"),
    /** German (Germany) locale */
    DE_DE("de_DE"),
    /** French (France) locale */
    FR_FR("fr_FR"),
    /** Spanish (Spain) locale */
    ES_ES("es_ES"),
    /** Italian (Italy) locale */
    IT_IT("it_IT"),
    /** Russian (Russia) locale */
    RU_RU("ru_RU");

    private final String code;

    /**
     * Constructs a new DataMirageLocale with the specified locale code.
     *
     * @param code The locale code in the format "language_COUNTRY"
     */
    DataMirageLocale(String code) {
        this.code = code;
    }

    /**
     * Returns the locale code for this DataMirageLocale.
     *
     * @return The locale code in the format "language_COUNTRY"
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the locale code as a string representation.
     *
     * @return The locale code in the format "language_COUNTRY"
     */
    @Override
    public String toString() {
        return code;
    }

    /**
     * Returns the DataMirageLocale enum constant corresponding to the specified locale code.
     *
     * @param code The locale code to look up
     * @return The corresponding DataMirageLocale enum constant
     * @throws IllegalArgumentException if the specified locale code is not supported
     */
    public static DataMirageLocale fromCode(String code) {
        for (DataMirageLocale locale : values()) {
            if (locale.getCode().equalsIgnoreCase(code)) {
                return locale;
            }
        }
        throw new IllegalArgumentException("Unsupported locale: " + code);
    }
} 