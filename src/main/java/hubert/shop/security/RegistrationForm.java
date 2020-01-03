package hubert.shop.security;

import hubert.shop.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;
import java.util.ArrayList;

@Data
public class RegistrationForm {
    @NotBlank
    @Email
    private String username;
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 chars")
    private String password;
    private String fullname;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password), new ArrayList<>());
    }
}
