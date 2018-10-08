package br.com.softplan.process.response;

import br.com.softplan.process.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessResponse {

    private Long id;
    private String name;
    private List<UserResponse> usersResponse;
}
