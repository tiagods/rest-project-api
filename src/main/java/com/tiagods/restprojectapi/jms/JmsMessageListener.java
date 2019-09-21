package com.tiagods.restprojectapi.jms;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.tiagods.restprojectapi.repository.Clientes;
import com.tiagods.restprojectapi.service.ClientesService;

@Component 
@PropertySource("classpath:jms.properties")
public class JmsMessageListener{
	@Autowired 
	private JmsTemplate jmsTemplate;
    @Autowired 
    private JmsTemplate jmsTemplateTopic;
    
    @Autowired
    private ClientesService clientes;
    
    @Value("${spring.activemq.queue}")
    private String queue;
    
    @Value("${spring.activemq.topic}")
    private String topic;

    @JmsListener(destination = "${spring.activemq.queue}")
    public void onReceiverQueue(String str) {
    	System.out.println("Recebido msg: "+str);
    	clientes.setMsgQueue(str);
    }

    @JmsListener(destination = "topic.sample", containerFactory = "jmsFactoryTopic")
    public void onReceiverTopic(String str) {
    	System.out.println("Recebido msg topic: "+str	 );
    }
    public void putQueue(String value) {
    	 jmsTemplate.convertAndSend(queue, value);
    }
    public void putTopic(String mensagem){
        jmsTemplateTopic.convertAndSend(topic, mensagem);
    }
    
    /*
    @Override
    public void run(ApplicationArguments args) throws Exception {
        jmsTemplate.convertAndSend("queue.sample", "{user: 'wolmir', usando: 'fila'}");
        jmsTemplateTopic.convertAndSend("topic.sample", "{user: 'wolmir', usando: 'tópico'}");
    }
    */
}
