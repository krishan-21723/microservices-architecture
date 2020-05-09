package com.xyz.notificationservice.kafka.consumer;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.springframework.beans.factory.annotation.Value;

import com.xyz.notificationservice.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseConsumer {

    @Value("${bootstrap.servers}")
    private String bootstrapServers;

    @Value("${zookeeper.servers}")
    private String zookeeperServers;
    
	public abstract void process(String topic, byte[] record) throws Exception;

	public void consume(final List<String> topics, String groupId) {
		KStreamBuilder kStreamBuilder = new KStreamBuilder();
        KStream<String, byte[]> eventStream = kStreamBuilder.stream(topics.toArray(new String[] {}));
        KafkaStreams kafkaStreams = null;
        try {
        	Function<KStream<String, byte[]>, Void> function = getKStreamFunction(topics, this);
        	function.apply(eventStream);
            kafkaStreams = setupKafkaStreams(kStreamBuilder, kafkaStreams, groupId);
            log.info("Starting consumer stream for consuming topics : {}", topics);
            kafkaStreams.start();
        } catch (Throwable e) {
            log.error("Error while consuming stream for event : {}, error : {}", topics, e.getMessage(), e);
            kafkaStreams.close();
        } finally {
            log.info("Started consuming stream for topic: {}", topics);
        }
	}
	
	private Function<KStream<String, byte[]>, Void> getKStreamFunction(final List<String> topics, BaseConsumer baseConsumer) {
		return (eventStream) -> {
			eventStream.foreach((key, objectJson) -> {
				try {
					baseConsumer.process(key, objectJson);
				} catch (Exception e) {
					log.error("Exception in processing key {}, objectJson {}", CommonUtils.toJson(key), CommonUtils.toJson(objectJson), e);
				}
			});
			return null;
		};
	}
	
	public KafkaStreams setupKafkaStreams(KStreamBuilder kStreamBuilder, KafkaStreams kafkaStreams, String groupId) {
		kafkaStreams = initializeKafkaStream(kStreamBuilder, kafkaStreams, groupId);
		addShutDownHook(kafkaStreams);
		return kafkaStreams;
	}
	 
	public KafkaStreams initializeKafkaStream(KStreamBuilder kStreamBuilder, KafkaStreams kafkaStreams, String groupId) {
		Properties properties = createConsumerProperties(groupId);
		kafkaStreams = new KafkaStreams(kStreamBuilder, properties);
		kafkaStreams.setUncaughtExceptionHandler((thread, throwable) -> {
			try {
				// this handler will be called whenever stream thread terminates via some exception
				log.info("Stream thread terminated unexpectedly, thread name : {}, message : {}", thread.getName(), throwable.getMessage(), throwable);
			} catch (Throwable e) {
				log.error("Stream thread terminated unexpectedly, thread name : {}, message : {}", thread.getName(), throwable.getMessage(), throwable);
			}
		});
		return kafkaStreams;
	}
	 
	 private Properties createConsumerProperties(String groupId) {
	        Properties props = new Properties();
	        props.put(StreamsConfig.APPLICATION_ID_CONFIG, groupId);
	        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	        props.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG, zookeeperServers);
	        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
	        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass());
	        return props;
	    }
	 
	 public void addShutDownHook(KafkaStreams kafkaStreams) {
		// To allow your application to gracefully shutdown kafka stream thread in response to SIGTERM
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				kafkaStreams.close();
			} catch (Exception e) {

			}
		}));
	}

	public static String getMessageInBuilder(byte[] body) {
		return new StringBuilder(new String(body, StandardCharsets.UTF_8)).toString();
	}
}
