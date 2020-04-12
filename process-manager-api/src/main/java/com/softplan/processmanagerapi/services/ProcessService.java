package com.softplan.processmanagerapi.services;

import com.softplan.processmanagerapi.excpetion.BadRequestException;
import com.softplan.processmanagerapi.excpetion.ResourceNotFoundException;
import com.softplan.processmanagerapi.factory.ProcessFactory;
import com.softplan.processmanagerapi.models.Opinion;
import com.softplan.processmanagerapi.models.Process;
import com.softplan.processmanagerapi.models.User;
import com.softplan.processmanagerapi.payload.request.OpinionRequest;
import com.softplan.processmanagerapi.payload.request.ProcessRequest;
import com.softplan.processmanagerapi.payload.response.PagedResponse;
import com.softplan.processmanagerapi.payload.response.ProcessResponse;
import com.softplan.processmanagerapi.repository.ProcessRepository;
import com.softplan.processmanagerapi.repository.UserRepository;
import com.softplan.processmanagerapi.security.UserPrincipal;
import com.softplan.processmanagerapi.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProcessService {

    @Autowired
    ProcessRepository processRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProcessFactory processFactory;

    public PagedResponse<ProcessResponse> getAllProcess(UserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page,size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Process> processes  = processRepository.findAll(pageable);

        if(processes.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), processes.getNumber(),
                    processes.getSize(), processes.getTotalElements(), processes.getTotalPages(), processes.isLast());
        }

        List<ProcessResponse> processResponseList = processes.map(process -> {
            User creator = userRepository.findById(process.getCreatedBy())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User", "getCreatedBy",process.getCreatedBy()));
            return processFactory.getProcessResponse(process, creator);
        }).getContent();

        return new PagedResponse<>(processResponseList,processes.getNumber(),
                processes.getSize(), processes.getTotalElements(), processes.getTotalPages(), processes.isLast());
    }

    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("O número de páginas não pode ser menor que 0.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("O número de páginas não pode ser maior que " + AppConstants.MAX_PAGE_SIZE);
        }
    }

    public Process createProcess(ProcessRequest processRequest) {
        Process process = new Process();
        process.setSubstantiation(processRequest.getSubstantiation());
        process.setStatus(processRequest.getStatus());
        List<Opinion> opinions = new ArrayList<>();
        processRequest.getOpinions().forEach(opinionRequest -> {
            opinions.add(mapOpinionRequestToOpinion(opinionRequest, process));
        });
        process.setOpinions(opinions);
        Set<User> users = new HashSet<>();
        processRequest.getAssignedUsersIds().forEach(userId -> {
            users.add(userRepository.findById(userId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User", "ProcessRequest.AssignedUser.Id",process.getCreatedBy())));
        });
        process.setAssignedUsers(users);
        return processRepository.save(process);
    }

    private Opinion mapOpinionRequestToOpinion(OpinionRequest opinionRequest, Process process){
        Opinion opinion = new Opinion();
        opinion.setOpinion(opinionRequest.getOpinion());
        opinion.setUser(userRepository.findById(opinionRequest.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "OpinionRequest.User.id",process.getCreatedBy())));
        opinion.setProcess(process);
        return opinion;
    }
}
