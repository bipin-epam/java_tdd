package test;

import com.bipincodes.testing.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @ParameterizedTest()
    @MethodSource("rawTagInput")
    @DisplayName("canParseSingleTagNameAndItsValue")
    void testParseTags_whenProvidedSingleTagAndValue_returnsMap(String rawTags){

        Map<String, String> tags = Main.parseTags(rawTags);
        assertNotNull(tags);
        assertEquals(tags.size(), 1);
    }

    @ParameterizedTest()
    @MethodSource("rawTagsInput")
    @DisplayName("canParseMultipleTagNameAndItsValue")
    void testParseTags_whenProvidedMultipleTagAndValue_returnsMap(String rawTags){

        Map<String, String> tags = Main.parseTags(rawTags);
        assertNotNull(tags);
        assertEquals(tags.size(), 2);
    }
    @ParameterizedTest
    @MethodSource("invalidRawTagInput")
    @DisplayName("throwsFormatExceptionIfInvalidFormatTagsAreParsed")
    void testParseTags_whenProvidedInputIsInvalid_throwsInvalidFormatException(String rawTags){
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
                Arguments.of("A : V A : B")
        );
    }
    static Stream<Arguments> rawTagInput(){
        return Stream.of(
                Arguments.of("subject:Great beginnings"),
                Arguments.of("greeting:Hi there"));
    }

    static Stream<Arguments> rawTagsInput(){
        return Stream.of(
                Arguments.of("subject:Great beginnings,greeting:GoodMorning"),
                Arguments.of("greeting:Hi there,subject:Winter has come"));
    }
}
