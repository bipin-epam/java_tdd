package com.bipincodes.testing.template;

import com.bipincodes.testing.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateEngine {
    public String generateMessage(Template template, Client client) {
        Map<String, String> tags = client.getTags();
        String userTemplate = template.getTemplate();

        return processTemplate(userTemplate, tags);
    }

    public String processTemplate(String template, Map<String, String> tags) {
        for (String placeholder : extractPlaceholders(template)) {
            System.out.println("Extracted : " + placeholder);
            if (!tags.containsKey(placeholder)) {
                throw new IllegalArgumentException("Missing variable: " + placeholder);
            }
        }

        String result = template;
        for (Map.Entry<String, String> entry : tags.entrySet()) {
            result = result.replace("#{" + entry.getKey() + "}", entry.getValue());
        }

        return result;
    }

    private static Iterable<String> extractPlaceholders(String template) {

        List<String> templateWords = new ArrayList<>();
        Pattern pattern = Pattern.compile("#\\{([^}]+)\\}");
        Matcher matcher = pattern.matcher(template);

        while (matcher.find()) {
            templateWords.add(matcher.group(1));
        }
        return templateWords;
    }
}
