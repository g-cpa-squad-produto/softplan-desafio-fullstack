package unpg.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unpg.Model.User;
import unpg.Repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody User request) {
        User user = new User(request.getUserId(), request.getName(), request.getEmail(), request.getPassword(),
                request.getRoles());
        return userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> get() {
        return userRepository.findAll();
    }

    @RequestMapping("/{id}")
    public User getById(@PathVariable("id") int idUser) {
        return userRepository.findByUserId(idUser);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable("id") int idUser) {
        userRepository.deleteByUserId(idUser);
        return "Usuário removido com sucesso " + idUser;
    }

    @RequestMapping("login")
    public User login(@RequestBody User request) throws Exception {
        User user = userRepository.findByEmail(request.getEmail());

        System.out.println(user);

        if (user == null)
            throw new Exception("Email não encontrado: " + request.getEmail());

        if (user.getPassword().equals(request.getPassword())) {
            return user;
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User edit(@PathVariable("id") int idUser, @RequestBody User request) throws Exception {
        User userEdited = userRepository.findByUserId(idUser);
        if (userEdited == null) {
            throw new Exception("Usuário não encontrado");
        }
        userEdited.setName(request.getName());
        userEdited.setEmail(request.getEmail());
        userEdited.setPassword(request.getPassword());
        userEdited.setRoles(request.getRoles());

        userRepository.save(userEdited);

        return userEdited;
    }
}
