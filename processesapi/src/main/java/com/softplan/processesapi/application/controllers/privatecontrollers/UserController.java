package com.softplan.processesapi.application.controllers.privatecontrollers;

import com.softplan.processesapi.application.controllers.IRestController;
import com.softplan.processesapi.domain.user.admin.models.Admin;
import com.softplan.processesapi.domain.user.admin.services.ICreateUserService;
import com.softplan.processesapi.domain.user.admin.services.IDeleteUserService;
import com.softplan.processesapi.domain.user.admin.services.IGetUserService;
import com.softplan.processesapi.domain.user.admin.services.IUpdateUserService;
import com.softplan.processesapi.domain.user.enums.UserType;
import com.softplan.processesapi.domain.user.finisher.models.Finisher;
import com.softplan.processesapi.domain.user.models.User;
import com.softplan.processesapi.domain.user.triator.models.Triator;
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
        if (user.getType() == UserType.ADMIN) {
            Admin newUser = new Admin(user);
            return createUserService.createAdmin(newUser);
        } else if (user.getType() == UserType.TRIATOR) {
            Triator newUser = new Triator(user);
            return createUserService.createTriator(newUser);
        } else {
            Finisher newUser = new Finisher(user);
            return createUserService.createFinisher(newUser);
        }
    }

    @Override
    @PutMapping()
    public User put(@RequestBody User user) {
        return updateUserService.put(user);
    }

    @Override
    @DeleteMapping()
    public void delete(@PathVariable Long id) {
        deleteUserService.delete(id);
    }
}
