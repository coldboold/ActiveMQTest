import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender {
	public static final int SEND_NUM = 5;
	public static final String BROKER_URL = "failover:(tcp://192.168.86.80:61616,tcp://192.168.86.81:61616,tcp://192.168.86.82:61616)";
	public static final String DESTINATION = "tc.mq.queue.student";

	public static void sendMessage(Session session, MessageProducer producer) throws JMSException {
		for (int i = 0; i < SEND_NUM; i++) {
			// String message = "发送消息" + (i + 1) + "条";
			// TextMessage text = session.createTextMessage(message);
			Student student = new Student("冷血", 22 + i, "1234567890", "北京市西城区");
			ObjectMessage message = session.createObjectMessage(student);
			producer.send(message);
		}
	}

	public static void run() throws JMSException {
		Connection connection = null;
		Session session = null;
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(DESTINATION);
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			sendMessage(session, producer);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		MessageSender.run();
	}

}
