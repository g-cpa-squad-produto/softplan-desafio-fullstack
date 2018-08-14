package br.com.fwom.api.controllers;

import br.com.fwom.api.exceptions.ResourceNotFoundException;
import br.com.fwom.api.models.Lawsuit;
import br.com.fwom.api.models.LawsuitUser;
import br.com.fwom.api.models.User;
import br.com.fwom.api.services.LawsuitService;
import br.com.fwom.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LawsuitsController {

    @Autowired
    private UserService userService;

    @Autowired
    private LawsuitService lawsuitService;

    private Authentication getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/trial/lawsuits/{lawsuitId}")
    public Optional<Lawsuit> getLawsuit(@PathVariable Long lawsuitId) {
        return lawsuitService.findById(lawsuitId);
    }

    @GetMapping("/trial/lawsuits")
    public List<Lawsuit> listLawsuits() {
        return lawsuitService.findAll();
    }

    @GetMapping("/editor/lawsuits/opened")
    public Set<Lawsuit> getOpenedLawsuits() {
        return lawsuitService.findAllOpened(getCurrentUser().getPrincipal().toString());
    }

    @PutMapping("/editor/lawsuits/opened/{lawsuitId}")
    public Lawsuit updateOpenedLawsuit(@PathVariable Long lawsuitId,
                                       @Valid @RequestBody Lawsuit lawsuit) {
        Optional<Lawsuit> rawLawsuit = lawsuitService.findById(lawsuitId);
        if (!rawLawsuit.isPresent()) {
            throw new ResourceNotFoundException("Lawsuit not found with id " + lawsuitId);
        }
        User currentUser = userService.findOne(getCurrentUser().getPrincipal().toString());
        List<LawsuitUser> associations = userService.findAssociations(lawsuitId, currentUser.getId());
        if (associations.size() == 0) throw new Error("Lawsuit is not on your command.");
        return rawLawsuit
                .map(law -> {
                    law.setStateActor(currentUser.getEmail());
                    law.setStateClass(lawsuit.getStateClass());
                    law.setStateDescription(lawsuit.getStateDescription());
                    law.setOpened(lawsuit.isOpened());
                    return lawsuitService.save(law);
                }).orElseThrow(() -> new ResourceNotFoundException("Lawsuit not found with id " + lawsuitId));
    }

    @RequestMapping(value = "/trial/lawsuits", method = RequestMethod.POST, consumes = {"application/json"})
    public Optional<Lawsuit> addLawsuit(@RequestBody Lawsuit lawsuit) {
        Optional<Lawsuit> rawLawsuit = lawsuitService.findOne(lawsuit.getNumber());
        if (!rawLawsuit.isPresent()) {
            return lawsuitService.addAndSaveEditors(lawsuit);
        } else {
            return updateLawsuit(rawLawsuit, lawsuit);
        }
    }

    private Optional<Lawsuit> updateLawsuit(Optional<Lawsuit> rawLawsuit, Lawsuit dto) throws ResourceNotFoundException {
        User currentUser = userService.findOne(getCurrentUser().getPrincipal().toString());
        Lawsuit law = rawLawsuit.get();
        law.setDescription(dto.getDescription());
        law.setStateActor(currentUser.getEmail());
        law.setStateClass(dto.getStateClass());
        law.setStateDescription(dto.getStateDescription());
        law.setOpened(dto.isOpened());
        return lawsuitService.addAndSaveEditors(law);
    }

    @PutMapping("trial/lawsuits/{lawsuitId}")
    public Optional<Lawsuit> updateLawsuit(@PathVariable Long lawsuitId,
                                           @Valid @RequestBody Lawsuit lawsuit) {
        Optional<Lawsuit> rawLawsuit = lawsuitService.findById(lawsuitId);
        if (!rawLawsuit.isPresent()) {
            throw new ResourceNotFoundException("Lawsuit not found with id " + lawsuitId);
        }
        return updateLawsuit(rawLawsuit, lawsuit);
    }
}
