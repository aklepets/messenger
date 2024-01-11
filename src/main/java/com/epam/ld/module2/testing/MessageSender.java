package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

public class MessageSender {
    public static void main(String[] args) throws IllegalArgumentException {
        MailServer mailServer = new MailServer();
        TemplateEngine templateEngine = new TemplateEngine();
        Messenger messenger = new Messenger(mailServer, templateEngine);
        Template template = new Template();
        Client client;
        if (args.length!=0) {
            client = new ClientFile(args[0]);
            messenger.sendMessage(client, template, args[1]);
        } else {
            client = new Client();
            messenger.sendMessage(client, template);
        }
    }
}
