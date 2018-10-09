package br.com.softplan.process.converter;

import br.com.softplan.process.Converter;
import br.com.softplan.process.model.Process;
import br.com.softplan.process.response.ProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessResponseConverter implements Converter<ProcessResponse, Process> {

    private UserResponseConverter userResponseConverter;

    @Autowired
    public ProcessResponseConverter(UserResponseConverter userResponseConverter) {
        this.userResponseConverter = userResponseConverter;
    }

    @Override
    public Process encode(ProcessResponse response) {
        return null;
    }

    @Override
    public ProcessResponse decode(Process process) {
        ProcessResponse processResponse = new ProcessResponse();

        processResponse.setId(process.getId());
        processResponse.setName(process.getName());
        processResponse.setUsers(userResponseConverter.decode(process.getUsers()));

        return processResponse;
    }
}
