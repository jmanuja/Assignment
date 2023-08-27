package com.alephlabs.assignment.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Generator Util
 */
public class GeneratorUtil {
    static final int ALPHANUMARIC_CODE_LENGTH= 7;

    /**
     * Generate Alpha numeric key with provided length
     * @return Unique Alpha numeric key
     */
    public static String generateAlphaNumericString() {
        return RandomStringUtils.randomAlphanumeric(ALPHANUMARIC_CODE_LENGTH);
    }
}
