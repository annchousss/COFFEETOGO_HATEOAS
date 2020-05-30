package ru.itis.coffeeservice.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

public class Client {

    private Channel channel;
    private String replyQueueName;

    private final static String REQUEST_QUEUE_NAME = "calls";

    public Client() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            replyQueueName = channel.queueDeclare("", false, true, false, null).getQueue();
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException();
        }
    }

    public void downloadPicture(String fileUrl) {
        String correlationId = UUID.randomUUID().toString();
        try {

            BasicProperties properties = new BasicProperties.Builder()
                    .correlationId(correlationId)
                    .replyTo(replyQueueName)
                    .build();

            channel.basicPublish("", REQUEST_QUEUE_NAME, properties, fileUrl.getBytes());

            CompletableFuture<Long> result = new CompletableFuture<>();

            String consumerTag = channel.basicConsume(replyQueueName, true, (tag, message) -> {
                if (message.getProperties().getCorrelationId().equals(correlationId)) {
                    result.complete(Long.parseLong(new String(message.getBody())));
                }
            }, tag -> {});
            channel.basicCancel(consumerTag);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
