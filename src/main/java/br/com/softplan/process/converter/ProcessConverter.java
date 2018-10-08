package br.com.softplan.process.converter;

import br.com.softplan.process.Converter;
import br.com.softplan.process.model.Process;
import br.com.softplan.process.response.ProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessConverter implements Converter<ProcessResponse, Process> {

    private UserConverter userConverter;

    @Autowired
    public ProcessConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
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
        processResponse.setUsersResponse(userConverter.decode(process.getUsers()));

        return processResponse;
    }
}
