package unpg.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unpg.Model.Process;
import unpg.Model.User;
import unpg.Repository.ProcessRepository;
import unpg.Repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/processes")

public class ProcessController {

    @Autowired
    private ProcessRepository processRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Process create(@RequestBody Process request) {

        Set<User> users = new HashSet<User>();
        for (User user : request.getUsers()) {
            System.out.println(user);
            users.add(userRepository.findByUserId(user.getUserId()));
        }

        Process process = new Process(request.getProcessId(), request.getNumber(), request.getReport(), users);
        return processRepository.save(process);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Process> get() {
        return processRepository.findAll();
    }

    @RequestMapping("/{id}")
    public Optional<Process> getById(@PathVariable("id") int id) {
        return processRepository.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable("id") int idProcess) {
        processRepository.deleteById(idProcess);
        return "Processo removido com sucesso " + idProcess;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Process edit(@PathVariable("id") int idProcess, @RequestBody Process request) throws Exception {
        Process processEdited = processRepository.findById(idProcess)
                .orElseThrow(() -> new Exception("Processo não encontrado"));
        processEdited.setNumber(request.getNumber());
        processEdited.setReport(request.getReport());

        processRepository.save(processEdited);

        return processEdited;
    }
}
