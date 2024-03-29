package com.bipincodes.testing;

import com.bipincodes.testing.template.Template;
import com.bipincodes.testing.template.TemplateEngine;

public class Messenger {
    private MailServer mailServer;
    private TemplateEngine templateEngine;
    public Messenger(MailServer mailServer,
                     TemplateEngine templateEngine) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
    }
    public void sendMessage(Client client, Template template) {
        String messageContent =
                templateEngine.generateMessage(template, client);
        System.out.println("Below is the generated message that will be sent...");
        System.out.println(messageContent);
        mailServer.send(client.getAddresses(), messageContent);

    }

}
