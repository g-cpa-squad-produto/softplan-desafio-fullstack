package com.softplan.processesapi.application.controllers.privatecontrollers;

import com.softplan.processesapi.application.controllers.IRestController;
import com.softplan.processesapi.domain.user.subdomains.admin.services.ICreateUserService;
import com.softplan.processesapi.domain.user.subdomains.admin.services.IDeleteUserService;
import com.softplan.processesapi.domain.user.subdomains.admin.services.IGetUserService;
import com.softplan.processesapi.domain.user.subdomains.admin.services.IUpdateUserService;
import com.softplan.processesapi.domain.user.models.User;
import com.softplan.processesapi.infrastructure.responsestatus.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements IRestController<User> {

    private ICreateUserService createUserService;
    private IGetUserService getUserService;
    private IUpdateUserService updateUserService;
    private IDeleteUserService deleteUserService;

    public UserController(ICreateUserService createUserService, IGetUserService getUserService,
                          IUpdateUserService updateUserService, IDeleteUserService deleteUserService) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
        this.updateUserService = updateUserService;
        this.deleteUserService = deleteUserService;
    }

    @Override
    @GetMapping("/")
    public List getAll() {
        return getUserService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) throws ResourceNotFoundException {
        return getUserService.getOne(id).orElseThrow(
                () -> new ResourceNotFoundException("Doesn't exist any user with id " + id));
    }

    @Override
    @PostMapping()
    public User post(@RequestBody User user) {
        return createUserService.createUser(user);
    }

    @Override
    @PutMapping()
    public User put(@RequestBody User user) throws ResourceNotFoundException {
        User newUser = getUserService.getOne(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Doesn't exist any user with id " + user.getId()));

        newUser.setName(user.getName() != null && !user.getName().isEmpty() ? user.getName() : newUser.getName());
        newUser.setEmail(user.getEmail() != null && !user.getEmail().isEmpty() ? user.getEmail() : newUser.getEmail());
        newUser.setPassword(user.getPassword() != null && !user.getPassword().isEmpty() ? user.getPassword() :
                newUser.getPassword());
        newUser.setType(user.getType() != null ? user.getType() : newUser.getType());

        return updateUserService.put(newUser);
    }

    @Override
    @DeleteMapping()
    public void delete(@PathVariable Long id) {
        deleteUserService.delete(id);
    }
}
