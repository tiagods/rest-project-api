package com.tiagods.restprojectapi.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.tiagods.restprojectapi.service.ClientesService;

@Component 
@PropertySource("classpath:jms.properties")
public class JmsMessageListener{
	@Autowired 
	private JmsTemplate jmsTemplate;
    
    @Autowired
    private ClientesService clientes;
    
    @Value("${spring.activemq.queue}")
    private String queue;
    
    @Value("${spring.activemq.topic}")
    private String topic;

    @JmsListener(destination = "${spring.activemq.queue}")
    public void onReceiverQueue(String str) {
    	clientes.salvar(str);
    }

    public void putQueue(String value) throws JmsException{
    	jmsTemplate.convertAndSend(queue, value);
    }
}
