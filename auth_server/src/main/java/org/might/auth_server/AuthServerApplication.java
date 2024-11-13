package org.might.auth_server;

import org.might.auth_server.users.User;
import org.might.auth_server.users.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            repo.save(new User("123", encoder.encode("123"), "ROLE_ADMIN"));
            repo.save(new User("321", encoder.encode("321"), "ROLE_ADMIN"));
        };
    }
}
