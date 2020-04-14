package com.softplan.processmanagerapi.services;

import com.softplan.processmanagerapi.excpetion.BadRequestException;
import com.softplan.processmanagerapi.excpetion.ResourceNotFoundException;
import com.softplan.processmanagerapi.factory.OpinionFactory;
import com.softplan.processmanagerapi.factory.ProcessFactory;
import com.softplan.processmanagerapi.factory.UserFactory;
import com.softplan.processmanagerapi.models.Opinion;
import com.softplan.processmanagerapi.models.Process;
import com.softplan.processmanagerapi.models.User;
import com.softplan.processmanagerapi.payload.UserSummary;
import com.softplan.processmanagerapi.payload.request.OpinionRequest;
import com.softplan.processmanagerapi.payload.request.ProcessRequest;
import com.softplan.processmanagerapi.payload.response.OpinionResponse;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ProcessService {

    @Autowired
    private ProcessRepository processRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProcessFactory processFactory;

    @Autowired
    private OpinionFactory opinionFactory;

    @Autowired
    private UserFactory userFactory;

    public PagedResponse<ProcessResponse> getAllProcess(UserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page,size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Process> processes  = processRepository.findAll(pageable);
        if(processes.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), processes.getNumber(),
                    processes.getSize(), processes.getTotalElements(), processes.getTotalPages(), processes.isLast());
        }
        List<ProcessResponse> processResponseList = processes.map(this::mapProcessToProcessResponse).getContent();
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
        Process process = processFactory.getEntity(processRequest);
        process.setOpinions(getOpinions(processRequest.getOpinions(), process));
        process.setAssignedUsers(getAssignedUsers(processRequest.getAssignedUsersIds(), process));
        return processRepository.save(process);
    }

    private List<Opinion> getOpinions(List<OpinionRequest> opinionRequests, Process process) {
        List<Opinion> opinions = new ArrayList<>();
        opinionRequests.forEach(opinionRequest -> {
            Opinion opinion = opinionFactory.getEntity(opinionRequest);
            opinion.setUser(userRepository.findById(opinionRequest.getUserId()).orElseThrow(() ->
                    new ResourceNotFoundException("User", "OpinionRequest.User.id",opinionRequest.getUserId())));
            opinion.setProcess(process);
            opinions.add(opinion);

        });
        return opinions;
    }

    private Set<User> getAssignedUsers(Set<Long> assignedUsersIds, Process process) {
        Set<User> users = new HashSet<>();
        assignedUsersIds.forEach(userId -> {
            users.add(userRepository.findById(userId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User", "ProcessRequest.AssignedUser.Id",process.getCreatedBy())));
        });
        return users;
    }

    private ProcessResponse mapProcessToProcessResponse(Process process) {
        ProcessResponse processResponse = processFactory.getProcessResponse(process);
        User creator = userRepository.findById(process.getCreatedBy())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "getCreatedBy",process.getCreatedBy()));
        processResponse.setUserSummary(new UserSummary(creator.getId(),creator.getUsername(), creator.getName()));
        List<OpinionResponse> opinionResponses = new ArrayList<>();
        process.getOpinions().stream().forEach(opinion -> {
            OpinionResponse opinionResponse = opinionFactory.getOpinionResponse(opinion);
            opinionResponse.setUser(userFactory.getUserResponse(opinion.getUser()));
            opinionResponses.add(opinionResponse);
        });
        processResponse.setOpinions(opinionResponses);

        return processResponse;
    }

}
