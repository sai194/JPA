package com.sye.microservices;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sye.microservices.domain.Gender;
import com.sye.microservices.domain.User;
import com.sye.microservices.domain.UserProfile;
import com.sye.microservices.repository.UserProfileRepository;
import com.sye.microservices.repository.UserRepository;

@SpringBootApplication
public class JpaOneToOneDemoApplication implements CommandLineRunner{
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaOneToOneDemoApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
        // Clean up database tables
        userProfileRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        //=========================================

        // Create a User instance
        User user = new User("User", "System", "abc@xyz.com",
                "abc123def");

        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1986, 6, 20);

        // Create a UserProfile instance
        UserProfile userProfile = new UserProfile("+91-1234567890", Gender.MALE, dateOfBirth.getTime(),
                "", " ", "", "Hyderabad",
                "Telanagana", "India", "1234567");


        // Set child reference(userProfile) in parent entity(user)
        user.setUserProfile(userProfile);

        // Set parent reference(user) in child entity(userProfile)
        userProfile.setUser(user);

        // Save Parent Reference (which will save the child as well)
        userRepository.save(user);

        //=========================================
    }
	
	
}
