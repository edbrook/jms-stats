package uk.co.dekoorb.jmsstats;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.command.CommandTypes;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class MessageConsumer {

    private final StatsStore statsStore;

    MessageConsumer(StatsStore statsStore) {
        this.statsStore = statsStore;
    }

    @JmsListener(destination = "${jms.endpoint}")
    public void receiveMessage(ActiveMQMessage message) throws JMSException {
        String body;
        if (message.getDataStructureType() == CommandTypes.ACTIVEMQ_TEXT_MESSAGE) {
            body = ((ActiveMQTextMessage)message).getText();
            long bytes = body.length();
            long totBytes = statsStore.incBytes(bytes);
            long msg = statsStore.incCount();
        } else {
            System.err.printf("unhandled message type: %s%n", message.getJMSType());
        }
    }
}