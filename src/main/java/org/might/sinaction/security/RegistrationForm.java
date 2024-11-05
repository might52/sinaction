package org.might.sinaction.security;

import lombok.Data;
import org.might.sinaction.db.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phoneNumber);
    }

}
