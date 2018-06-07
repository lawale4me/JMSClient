/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MB;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author Ahmed.Oladele
 */
@Named(value = "messageManagedBean")
@SessionScoped
public class MessageManagedBean implements Serializable{

    @Resource(mappedName = "jms/MyQueue")
    private Queue myQueue;
    
    private String name;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

       
    public MessageManagedBean() {
    }

    private void sendJMSMessageToMyQueue(String messageData) {
        context.createProducer().send(myQueue, messageData);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }            
    
    public void send(){
       sendJMSMessageToMyQueue(name);
    }
}
