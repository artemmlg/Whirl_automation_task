package api.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Generated;

@Data
@Generated("com.robohorse.robopojogenerator")
public class RegistrationRequestModel {
    public RegistrationRequestModel() {
    }

    public RegistrationRequestModel(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;
}