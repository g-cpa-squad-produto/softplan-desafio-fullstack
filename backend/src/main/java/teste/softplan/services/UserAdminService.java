package teste.softplan.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teste.softplan.domain.UserAdmin;
import teste.softplan.mapper.UserAdminMapper;
import teste.softplan.repository.UserAdminRepo;
import teste.softplan.requests.UserAdminPostRequestBody;
import teste.softplan.requests.UserAdminPutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdminService {

    private final UserAdminRepo userAdminRepo;

    public List<UserAdmin> listAll(){
        return userAdminRepo.findAll();
    }

    public UserAdmin findById(long id){
        return userAdminRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not Found"));
    }

    public UserAdmin save(UserAdminPostRequestBody userAdminPostRequestBody) {
        return userAdminRepo.save(UserAdmin.builder().name(userAdminPostRequestBody.getName()).build());

    }

    public void delete(long id) {
        userAdminRepo.delete(findById(id));
    }

    public void replace(UserAdminPutRequestBody userAdminPutRequestBody) {
        UserAdmin savedUserAdmin = findById((userAdminPutRequestBody.getId()));
        UserAdmin userAdmin = UserAdminMapper.INSTANCE.toUser(userAdminPutRequestBody);
        userAdmin.setId(savedUserAdmin.getId());
        userAdminRepo.save(userAdmin);
    }


}
