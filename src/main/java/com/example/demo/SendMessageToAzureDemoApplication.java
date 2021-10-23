package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

@SpringBootApplication
@RestController
public class SendMessageToAzureDemoApplication {

	 public static void main(String[] args) {
		SpringApplication.run(SendMessageToAzureDemoApplication.class, args);
	}
	
	@PostMapping("/sendmessagetoazure")
	public void sendMessage(@RequestBody AzureMessage azuremessage)
	{
		
		ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
	            .connectionString(azuremessage.getConnectionString())
	            .sender()
	            .queueName(azuremessage.getQueueName())
	            .buildClient();

	    senderClient.sendMessage(new ServiceBusMessage(azuremessage.getMessage()));
	    System.out.println("Sent a message to the queue: " + azuremessage.getMessage());        
	}


}
