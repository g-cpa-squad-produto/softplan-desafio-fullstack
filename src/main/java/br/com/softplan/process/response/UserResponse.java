package br.com.softplan.process.response;

import br.com.softplan.process.model.Profile;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    @JsonInclude
    private Long id;

    @JsonInclude
    private String name;

    @JsonInclude
    private String email;

    @JsonInclude
    private List<Profile> profiles;

    @JsonInclude
    private List<String> roles;

    @JsonInclude
    private String token;
}
