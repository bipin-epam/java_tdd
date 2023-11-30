package com.bipincodes.testing;

import com.bipincodes.testing.template.Template;
import com.bipincodes.testing.template.TemplateEngine;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        init();
    }

    static String readInputWithMsg(String msg){
        System.out.println(msg + ",or type 'no' to exit...");
        String value = sc.nextLine();

        if(value.equalsIgnoreCase("no"))
            return null;
        return value;
    }

    static void init(){

        String template = "";
        String rawTags = "";
        String receiverAddress = "";

        Map<String, String> tags = null;
        Messenger messenger = new Messenger(new MailServer(), new TemplateEngine());

        while(true){
            template = readInputWithMsg("Enter the template String, or type 'no' to exit...");
            if(template == null) break;

            rawTags = readInputWithMsg("Enter the value for tags in format : 'name:value,name:value,name:value'");
            if(rawTags == null) break;
            tags = parseTags(rawTags);

            receiverAddress = readInputWithMsg("Enter the value for address in format: example@mail.com;example@mail.com;example@mail.com");
            if(receiverAddress == null) break;

            messenger.sendMessage(new Client(receiverAddress , tags), new Template(template));
        }

        System.out.println("Program completed, press 'enter' to exit");
        sc.nextLine();
    }


    public static Map<String, String> parseTags(String rawTags){

        String EXCEPTION_MSG = "Tag format must be in format : 'name:value,name:value";

        if(rawTags.isEmpty() || rawTags.isBlank())
            throw new IllegalArgumentException(EXCEPTION_MSG);

        String TAG_SEPARATOR = ",";
        String TAG_VALUE_SEPARATOR = ":";
        Map<String, String> tags = new HashMap<>();

        List<String> separatedTagsValuePair = Arrays.stream(rawTags.split(TAG_SEPARATOR)).toList();
        if(separatedTagsValuePair.isEmpty())
            throw new IllegalArgumentException(EXCEPTION_MSG);

        for(String tagValue : separatedTagsValuePair){
            List<String> splitData = Arrays.stream(tagValue.trim().split(TAG_VALUE_SEPARATOR)).toList();

            //Each split value should have two part (tag name , tag value)
            if(splitData.size() <= 1 || splitData.size() % 2 != 0) {
                throw new IllegalArgumentException(EXCEPTION_MSG);
            }
            tags.put(splitData.get(0).trim(), splitData.get(1).trim());
        }
        return tags;
    }
}
