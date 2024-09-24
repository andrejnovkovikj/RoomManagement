package mk.ukim.finki.akreditacii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class RoomManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomManagementApplication.class, args);
    }

}
