package br.com.softplan.processmanagement.services;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.Opinion;
import br.com.softplan.processmanagement.domain.UserType;
import br.com.softplan.processmanagement.repositories.ProcessesRepository;
import br.com.softplan.processmanagement.repositories.OpinionRepository;
import br.com.softplan.processmanagement.repositories.UsersSystemRepository;
import br.com.softplan.processmanagement.security.SignUpRequest;
import br.com.softplan.processmanagement.services.exceptions.EmailAlreadyUsedException;
import br.com.softplan.processmanagement.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersSystemRepository usersSystemRepository;

    @Autowired
    private OpinionRepository userSystemProcessRepository;

    @Autowired
    private ProcessesService processesService;

    @Autowired
    private ProcessesRepository processesRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Transactional
    public UserSystem save(UserSystem userSystem) {
        userSystem.setId(null);
        Optional<UserSystem> userByEmail = usersSystemRepository.findByEmail(userSystem.getEmail());
        if(userByEmail.isPresent()){
            throw new EmailAlreadyUsedException("Email already used.");
        }

        String password = userSystem.getPassword();
        if(password != null && StringUtils.hasText(password)){
            userSystem.setPassword(bcryptEncoder.encode(password));
        }

        return usersSystemRepository.save(userSystem);
    }

    @Transactional
    public UserSystem saveUserFromRequest(SignUpRequest signUpRequest) {
        UserSystem newUser = new UserSystem();
        newUser.setName(signUpRequest.getName());
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setType(signUpRequest.getUserType());
        newUser.setPassword(bcryptEncoder.encode(signUpRequest.getPassword()));
        return save(newUser);
    }

    public List<UserSystem> list() {
        return usersSystemRepository.findAll();
    }

    public List<UserSystem> listFinalizadores() {
        return usersSystemRepository.findAllByType(UserType.FINALIZADOR);
    }

    public UserSystem searchById(Long id) {
        Optional<UserSystem> user = usersSystemRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        return user.get();
    }

    @Transactional
    public UserSystem update(UserSystem userSystem) {
        checkExistence(userSystem);

        String password = userSystem.getPassword();
        if(password != null && StringUtils.hasText(password)){
            userSystem.setPassword(bcryptEncoder.encode(password));
        }

        return usersSystemRepository.save(userSystem);
    }

    @Transactional
    public void delete(Long id) {
        try {
            usersSystemRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("Distributor not found");
        }
    }

    public void checkExistence(UserSystem userSystem) {
        searchById(userSystem.getId());
    }

    public Boolean verifyExistence(String email) {
        return usersSystemRepository.existsByEmail(email);
    }

    public List<Opinion> getOpinionsByUser(Long idUser) {
        Optional<UserSystem> user = Optional.of(this.searchById(idUser));

        if (user.isPresent()) {
            List<Opinion> opinions = userSystemProcessRepository.findAllByUserSystemAndTextNotNull(user.get());
            return opinions;
        }
        return new ArrayList<Opinion>();
    }

    public List<UserSystem> listByProcess(Long idProcess) {
        Process process = processesService.searchById(idProcess);
        return processesRepository.findUsersByProcess(process);
    }
}
