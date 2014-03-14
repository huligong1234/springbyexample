package org.jeedevframework.web.jms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

public class SenderApp{
    public static void main( String[] args ) throws IOException {
        MessageSender sender = getMessageSender();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();

        while (!StringUtils.isEmpty(text)) {
            System.out.println(String.format("send message: %s", text));
            sender.send(text);
            text = br.readLine();
        }
    }

    public static MessageSender getMessageSender() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-jms.xml");
       return (MessageSender) context.getBean("messageSender");
    }
}
