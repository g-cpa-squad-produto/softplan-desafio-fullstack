package br.com.softplan.process.converter;

import br.com.softplan.process.Converter;
import br.com.softplan.process.model.Process;
import br.com.softplan.process.request.ProcessRequest;
import br.com.softplan.process.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ProcessRequestConverter implements Converter<ProcessRequest, Process> {

    private UserService userService;

    @Autowired
    public ProcessRequestConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Process encode(ProcessRequest request) {
        Process process = new Process();

        process.setId(request.getId());
        process.setName(request.getName());
        process.setUsers(new HashSet<>(userService.findByIdIn(request.getUsers())));

        return process;
    }

    @Override
    public ProcessRequest decode(Process input) {
        return null;
    }
}
