package com.bipincodes.testing.template;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.IllegalFormatException;

import static org.junit.jupiter.api.Assertions.*;

class TemplateEngineTest {
    TemplateEngine engine = new TemplateEngine();
    @Test
    void testProcessTemplateShouldReturnResult() {

        assertEquals("Hi bipin, this is regarding greetings", engine.processTemplate(
                "Hi #{name}, this is regarding #{subject}", new HashMap<>(){
                {
                    put("name", "bipin");
                    put("subject", "greetings");
                }})
        );

        assertEquals("Hi bipin, this is regarding greetings", engine.processTemplate(
                "Hi #{name}, this is regarding #{subject}", new HashMap<>(){
                    {
                        put("name", "bipin");
                        put("subject", "greetings");
                        put("notRequired", "lorem ipsum");
                    }})
        );
    }

    @Test
    void testProcessTemplateShouldThrowException(){
        IllegalArgumentException illegalFormatException = assertThrows(IllegalArgumentException.class, ()-> {
                    engine.processTemplate(
                            "Hi #{name}, this is regarding #{subject}", new HashMap<>() {
                                {
                                    put("name", "bipin");
                                }});
                }
        );
    }
}