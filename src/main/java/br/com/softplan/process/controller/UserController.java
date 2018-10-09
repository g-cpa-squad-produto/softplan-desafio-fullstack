package br.com.softplan.process.controller;

import br.com.softplan.process.converter.UserRequestConverter;
import br.com.softplan.process.converter.UserResponseConverter;
import br.com.softplan.process.request.UserRequest;
import br.com.softplan.process.response.UserResponse;
import br.com.softplan.process.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private UserService service;
    private UserResponseConverter responseConverter;
    private UserRequestConverter requestConverter;

    @Autowired
    public UserController(UserService service, UserResponseConverter responseConverter,
                          UserRequestConverter requestConverter) {
        this.service = service;
        this.responseConverter = responseConverter;
        this.requestConverter = requestConverter;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid UserRequest user) {
        this.service.save(requestConverter.encode(user));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id,
                       @RequestBody @Valid UserRequest user) {
        user.setId(id);
        this.service.save(requestConverter.encode(user));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> list() {
        return this.responseConverter.decode(this.service.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse find(@PathVariable Long id) throws Exception {
        return this.responseConverter.decode(this.service.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }
}
