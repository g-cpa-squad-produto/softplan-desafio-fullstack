package br.com.softplan.process.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    private Long id;

    @NotNull(message = "Nome é obrigatório")
    private String name;

    @NotNull(message = "E-mail é obrigatório")
    private String email;

    @NotNull(message = "Perfil é obrigatório")
    private List<Long> profiles;
}
