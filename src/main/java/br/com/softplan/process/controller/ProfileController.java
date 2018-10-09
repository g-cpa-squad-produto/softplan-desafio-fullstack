package br.com.softplan.process.controller;

import br.com.softplan.process.model.Profile;
import br.com.softplan.process.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("Test")
@RequestMapping(path = "/profile")
public class ProfileController {

    private ProfileService service;

    @Autowired
    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Profile> list() {
        return this.service.findAll();
    }
}
