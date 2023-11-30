package com.bipincodes.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    @DisplayName("canParseSingleTagNameAndItsValue")
    void testParseTags_whenProvidedSingleTagAndValue_returnsMap(){
        assertEquals(
                Map.of("subject", "Great beginnings"),
                Main.parseTags("subject:Great beginnings"));
    }

    @Test
    @DisplayName("canParseMultipleTagNameAndItsValue")
    void testParseTagsShouldReturnMapOfMultipleObjects(){

        assertEquals(
                Map.of("subject", "Great beginnings", "greeting", "GoodMorning"),
                Main.parseTags("subject:Great beginnings,greeting:GoodMorning"));
    }
    @ParameterizedTest
    @MethodSource("invalidRawTagInput")
    @DisplayName("throwsFormatExceptionIfInvalidFormatTagsAreParsed")
    void testParseTagsShouldThrowException(String rawTags){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->{
            Main.parseTags(rawTags);
        });
        assertEquals(exception.getMessage(), "Tag format must be in format : 'name:value,name:value");
    }

    static Stream<Arguments> invalidRawTagInput(){
        return Stream.of(
                Arguments.of("subject"),
                Arguments.of(""),
                Arguments.of(","),
                Arguments.of(",,,"),
                Arguments.of(":"),
                Arguments.of("::::"),
                Arguments.of("A : V A : B"));
    }

}