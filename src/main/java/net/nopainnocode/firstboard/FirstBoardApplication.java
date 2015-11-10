package net.nopainnocode.firstboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
public class FirstBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstBoardApplication.class, args);
    }

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }


    @Bean
    WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addWebRequestInterceptor(oeiv());
            }
        };
    }

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public WebRequestInterceptor oeiv() {
        OpenEntityManagerInViewInterceptor oeiv = new OpenEntityManagerInViewInterceptor();
        oeiv.setEntityManagerFactory(entityManagerFactory);
        return oeiv;
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
