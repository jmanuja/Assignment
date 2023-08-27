package com.alephlabs.assignment.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GeneratorUtilTest {

    @Test
    public void testGenerateAlphaNumericStringSize(){
        String random=GeneratorUtil.generateAlphaNumericString();
        assertThat(random.length())
                .isEqualTo(7);
    }

}
