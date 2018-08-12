package br.com.fwom.api.services.impl;

import br.com.fwom.api.exceptions.ResourceNotFoundException;
import br.com.fwom.api.models.Lawsuit;
import br.com.fwom.api.models.LawsuitUser;
import br.com.fwom.api.models.User;
import br.com.fwom.api.repositories.LawsuitRepository;
import br.com.fwom.api.repositories.LawsuitUserRepository;
import br.com.fwom.api.repositories.UserRepository;
import br.com.fwom.api.services.LawsuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service(value = "lawsuitService")
public class LawsuitServiceImpl implements LawsuitService {

    @Autowired
    private LawsuitUserRepository lawsuitUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LawsuitRepository lawsuitRepository;

    public List<Lawsuit> findAll() {
        List<Lawsuit> list = new ArrayList<>();
        lawsuitRepository.findAll().iterator().forEachRemaining(list::add);
        list.forEach(lawsuit -> {
            List<LawsuitUser> lawsuitUsers = lawsuitUserRepository.findAllByLawsuitId(lawsuit.getId());
            lawsuit.setEditorUsers(lawsuitUsers.stream().distinct().map(lawsuitUser -> {
                return lawsuitUser.getUserId();
            }).collect(Collectors.toList()));
        });
        return list;
    }

    public Set<Lawsuit> findAllOpened(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        List<LawsuitUser> lawsuitASsociated = lawsuitUserRepository.findAllByUserId(user.getId());
        return new HashSet<>(lawsuitRepository.findAllById(lawsuitASsociated.stream().distinct().map(LawsuitUser::getLawsuitId).collect(Collectors.toSet())));
    }

    @Override
    public Optional<Lawsuit> addAndSaveEditors(Lawsuit lawsuit) {
        Lawsuit savedLawsuit = lawsuitRepository.save(lawsuit);
        List<User> editorUsers = new ArrayList<>();
        lawsuit.getEditorUsers().forEach(userId -> {
            Optional<User> editor = userRepository.findById(userId);
            editor.ifPresent(user -> {
                lawsuitUserRepository.save(new LawsuitUser(user.getId(), savedLawsuit.getId()));
            });
        });
        Optional<Lawsuit> created = lawsuitRepository.findById(savedLawsuit.getId());
        return Optional.of(created.map(lawsuit1 -> {
            List<LawsuitUser> lawsuitUsers = lawsuitUserRepository.findAllByLawsuitId(lawsuit1.getId());
            lawsuit1.setEditorUsers(lawsuitUsers.stream().distinct().map(LawsuitUser::getUserId).collect(Collectors.toList()));
            return lawsuit1;
        }).orElseThrow(() -> new Error("Not Found")));
    }

    @Override
    public Optional<Lawsuit> findOne(String number) {
        Optional<Lawsuit> lawsuit = lawsuitRepository.findByNumber(number);
        return Optional.of(lawsuit.map(lawsuit1 -> {
            List<LawsuitUser> lawsuitUsers = lawsuitUserRepository.findAllByLawsuitId(lawsuit1.getId());
            lawsuit1.setEditorUsers(lawsuitUsers.stream().distinct().map(LawsuitUser::getUserId).collect(Collectors.toList()));
            return lawsuit1;
        }).orElseThrow(() -> new ResourceNotFoundException("Not Found")));
    }

    @Override
    public void delete(long id) {
        lawsuitRepository.deleteById(id);
    }

    @Override
    public Optional<Lawsuit> findById(Long id) {
        Optional<Lawsuit> lawsuit = lawsuitRepository.findById(id);
        return Optional.of(lawsuit.map(lawsuit1 -> {
            List<LawsuitUser> lawsuitUsers = lawsuitUserRepository.findAllByLawsuitId(lawsuit1.getId());
            lawsuit1.setEditorUsers(lawsuitUsers.stream().distinct().map(LawsuitUser::getUserId).collect(Collectors.toList()));
            return lawsuit1;
        }).orElseThrow(() -> new ResourceNotFoundException("Not Found")));
    }

    @Override
    public Lawsuit save(Lawsuit Lawsuit) {
        return lawsuitRepository.save(Lawsuit);
    }
}

