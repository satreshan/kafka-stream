package kafkastremdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;
//import kafkastremdemo.CountryMessage;

public class ProduceData {
	
	public Producer<String, String> myKafkaProducer() {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		Producer<String, String> producer = null;
		try (InputStream properties = Resources.getResource("producer.props")
				.openStream()) {
			props.load(properties);
			//Producer<String, String> producer = new KafkaProducer<String, String>(props);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		producer = new KafkaProducer<String, String>(props);
		return producer;
	}
	

	public void produce(){
		ObjectMapper mapper = new ObjectMapper();
		InputStream message;
		try {
			message = Resources.getResource("message.json").openStream();
			String messageStr = CharStreams.toString(new InputStreamReader(message));
			//CountryMessage msg = mapper.readValue(message, CountryMessage.class);
			produceMessage("countries", messageStr);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public boolean produceMessage(String topicName,String message) {
		Producer<String, String> producer = null;
		try {
			
			// Check arguments null value
			if (topicName == null) {
				System.out
						.println("Please specify the topic name to produce the message.");
				return false;
			}
			producer = myKafkaProducer();

			producer.send(new ProducerRecord<String, String>(topicName, message));
			System.out.println("Message sent successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (producer != null)
				producer.close();
		}
		
		return true;
	}
	
	public static void main(String args[]) {
		ProduceData o = new ProduceData();
		o.produce();
	}
}
