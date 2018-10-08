package br.com.softplan.process.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;

    @NotNull(message = "Campo obrigatório")
    private String name;

    @NotNull(message = "Campo obrigatório")
    private String username;

    @NotNull(message = "Campo obrigatório")
    private String email;

    @NotNull(message = "Campo obrigatório")
    private String password;
}
