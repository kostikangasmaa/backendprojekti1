package fi.backendkurssi.fishapp;

import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.backendkurssi.fishapp.domain.Fish;
import fi.backendkurssi.fishapp.domain.FishRepository;
import fi.backendkurssi.fishapp.domain.User;
import fi.backendkurssi.fishapp.domain.UserRepository;

@SpringBootApplication
public class FishappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishappApplication.class, args);
	}

	@Bean
	public CommandLineRunner fishdemo(UserRepository uRepository, FishRepository fishRepository) {
		return (args) -> {


			//User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			//User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			//uRepository.save(user1);
			//uRepository.save(user2);

			// Fish fish1 = new Fish();
            //fish1.setSpecies("Salmon");
            //fish1.setLength(70.5);
            //fish1.setWeight(3.2);
            //fish1.setDate(new Date());
            //fish1.setLocation("River A");
            //fish1.setUser(user1);

            //Fish fish2 = new Fish();
            //fish2.setSpecies("Trout");
            //fish2.setLength(50.0);
            //fish2.setWeight(2.1);
            //fish2.setDate(new Date());
            //fish2.setLocation("Lake B");
            //fish2.setUser(user1);

			//fishRepository.saveAll(Arrays.asList(fish1, fish2));
		};
	}

}
