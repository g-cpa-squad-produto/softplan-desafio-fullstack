package br.com.softplan.process.converter;

import br.com.softplan.process.Converter;
import br.com.softplan.process.model.Process;
import br.com.softplan.process.request.ProcessRequest;
import org.springframework.stereotype.Component;

@Component
public class ProcessConverter implements Converter<ProcessRequest, Process> {

    @Override
    public Process encode(ProcessRequest input) {
        return null;
    }

    @Override
    public ProcessRequest decode(Process input) {
        return null;
    }
}
