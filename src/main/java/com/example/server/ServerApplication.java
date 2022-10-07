package com.example.server;

import com.example.server.enumeration.Status;
import com.example.server.models.Server;
import com.example.server.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}



@Bean
CommandLineRunner run(ServerRepo serverRepo){
		return  args -> {
		serverRepo.save(new Server(null,"192.168.1.168","Ubuntu Linux","32GB","Personal PC",
				"http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.1.58","Fedora Linux","16GB","Dell tower",
					"http://localhost:8080/server/image/server2.png", Status.SERVER_DOWN));
			serverRepo.save(new Server(null,"192.168.1.21","Ms 2008","64GB","Web server",
					"http://localhost:8080/server/image/server3.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.1.14","Red hat enterprise linux","128GB","Mail server",
					"http://localhost:8080/server/image/server4.png", Status.SERVER_DOWN));
		};
}




}
