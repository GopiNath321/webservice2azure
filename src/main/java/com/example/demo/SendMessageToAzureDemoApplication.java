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
		String connectionString = "Endpoint=sb://apicentrics.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=f0j9monM6TRW+QUS+709zVqWcjAB8zaFpbAR/Tk2uJc=";
		String queueName = "gopi-queue";
		
		ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
	            .connectionString(connectionString)
	            .sender()
	            .queueName(queueName)
	            .buildClient();

	    senderClient.sendMessage(new ServiceBusMessage(azuremessage.getData()));
	    System.out.println("Sent a message to the queue: " + azuremessage.getData());        
	}


}
