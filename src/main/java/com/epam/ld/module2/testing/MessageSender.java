package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

public class MessageSender {
    public static void main(String[] args) throws IllegalArgumentException {
        MailServer mailServer = new MailServer();
        TemplateEngine templateEngine = new TemplateEngine();
        Messenger messenger = new Messenger(mailServer, templateEngine);
        Client client = new Client();
        Template template = new Template();
        messenger.sendMessage(client, template);
    }
}
