package teste.softplan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teste.softplan.domain.UserAdmin;
import teste.softplan.repository.UserAdminRepo;
import teste.softplan.requests.UserAdminPostRequestBody;
import teste.softplan.requests.UserAdminPutRequestBody;
import teste.softplan.services.UserAdminService;
import teste.softplan.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("users")
@Log4j2
@RequiredArgsConstructor
public class UserAdminController {
    private final DateUtil dateUtil;
    private final UserAdminService userAdminService;


    @GetMapping
    public ResponseEntity<List<UserAdmin>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(userAdminService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAdmin> findById(@PathVariable long id){
        return ResponseEntity.ok(userAdminService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserAdmin> save(@RequestBody UserAdminPostRequestBody userAdminPostRequestBody) {
        return new ResponseEntity<>(userAdminService.save(userAdminPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userAdminService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody UserAdminPutRequestBody userAdminPutRequestBody) {
        userAdminService.replace(userAdminPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
