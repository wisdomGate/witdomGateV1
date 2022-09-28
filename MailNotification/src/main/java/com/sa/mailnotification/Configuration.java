package com.sa.mailnotification;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

//@org.springframework.context.annotation.Configuration
public class Configuration {
    private static final String Defualt_url="tcp://localhost:8181";
    private static final String Message_Queue="safinal";
    @Bean
    public ConnectionFactory connectionFactory() throws JMSException {
        ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(Defualt_url);
        return connectionFactory;
    }
    @Bean
    public JmsTemplate template() throws JMSException {
        JmsTemplate template=new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(Message_Queue);
        return template;
    }
    @Bean
    public MessageConverter converter(){
        return new SimpleMessageConverter();
    }
}
