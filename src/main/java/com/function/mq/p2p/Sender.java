package com.function.mq.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.jms.*;


/**
 * description: 消息生产者
 *
 * @author wangqiang
 */
@Component("sender")
public class Sender {
    //默认连接用户名 ActiveMQConnection.DEFAULT_USER admin
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码 ActiveMQConnection.DEFAULT_PASSWORD admin
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认连接地址 ActiveMQConnection.DEFAULT_BROKER_URL tcp://localhost:61616
    private static final String BROKEURL = "tcp://localhost:61616";
    //发送的消息数量
    private static final int SENDNUM = 10;

    public static void main(String[] args) {
        commonConnect();

    }

    @Test
    public static void JMSConnect(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
        Object bean = context.getBean("");
        //JmsTemplate jmsTemplate = (JmsTemplate)Object;
    }

    /**
     * 以普通方式连接mq
     */
    public static void commonConnect(){
        //连接工厂
        ConnectionFactory connectionFactory;
        //连接
        Connection connection = null;
        //会话 接受或者发送消息的线程
        Session session;
        //消息的目的地
        Destination destination;
        //消息生产者
        MessageProducer messageProducer;
        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(Sender.USERNAME, Sender.PASSWORD, Sender.BROKEURL);

        try {
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //创建一个名称为HelloWorld的消息队列
            destination = session.createQueue("HelloWorld");
            //创建消息生产者
            messageProducer = session.createProducer(destination);
            //发送消息
            sendMessage(session, messageProducer);

            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 发送消息
     * @param session
     * @param messageProducer  消息生产者
     * @throws Exception
     */
    public static void sendMessage(Session session,MessageProducer messageProducer) throws Exception{
        for (int i = 0; i < Sender.SENDNUM; i++) {
            //创建一条文本消息
            TextMessage message = session.createTextMessage("ActiveMQ 发送消息" +i);
            System.out.println("发送消息：Activemq 发送消息" + i);
            //通过消息生产者发出消息
            messageProducer.send(message);
        }

    }
}
