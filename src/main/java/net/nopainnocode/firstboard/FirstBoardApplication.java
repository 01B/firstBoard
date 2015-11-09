package net.nopainnocode.firstboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FirstBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstBoardApplication.class, args);
    }

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }


    // todo : to check the validation add validation annotation to domain classes

    // todo : exception control

    // todo : reflection to copy a class object

    // todo : security

    // todo : authority

    // todo : study

    // what is ResponseEntity, ReponseEntity.ok, ReponseEntity.status(HttpStatus).Header or Body
    // ListResponse

}
