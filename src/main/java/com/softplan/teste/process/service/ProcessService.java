package com.softplan.teste.process.service;

import com.softplan.teste.process.exception.BadRequestException;
import com.softplan.teste.process.exception.ProcessNotFoundException;
import com.softplan.teste.process.exception.ResourceNotFoundException;
import com.softplan.teste.process.model.*;
import com.softplan.teste.process.model.Process;
import com.softplan.teste.process.payload.PagedResponse;
import com.softplan.teste.process.payload.ProcessRequest;
import com.softplan.teste.process.payload.ProcessResponse;
import com.softplan.teste.process.payload.ReviewRequest;
import com.softplan.teste.process.repository.OpinionRepository;
import com.softplan.teste.process.repository.ProcessRepository;
import com.softplan.teste.process.repository.UserRepository;
import com.softplan.teste.process.repository.ReviewRepository;
import com.softplan.teste.process.security.UserPrincipal;
import com.softplan.teste.process.util.AppConstants;
import com.softplan.teste.process.util.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProcessService {

    @Autowired
    private ProcessRepository processRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OpinionRepository opinionRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProcessService.class);
    public PagedResponse<ProcessResponse> getPendingProcesses(UserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        // Retrieve Processes
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Process> processes = processRepository.findPendingProcesess("PENDING", pageable);

        if(processes.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), processes.getNumber(),
                    processes.getSize(), processes.getTotalElements(), processes.getTotalPages(), processes.isLast());
        }

        // Map Processes to ProcessResponses containing review counts and process creator details
        List<Long> processIds = processes.map(Process::getId).getContent();
        Map<Long, Long> opinionReviewCountMap = getOpinionReviewCountMap(processIds);
        Map<Long, Long> processUserReviewMap = getProcessUserReviewMap(currentUser, processIds);
        Map<Long, User> creatorMap = getProcessCreatorMap(processes.getContent());

        List<ProcessResponse> processResponses = processes.map(process -> {
            return ModelMapper.mapProcessToProcessResponse(process,
                    opinionReviewCountMap,
                    creatorMap.get(process.getCreatedBy()),
                    processUserReviewMap == null ? null : processUserReviewMap.getOrDefault(process.getId(), null));
        }).getContent();

        return new PagedResponse<>(processResponses, processes.getNumber(),
                processes.getSize(), processes.getTotalElements(), processes.getTotalPages(), processes.isLast());
    }
    public PagedResponse<ProcessResponse> getAllProcesses(UserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        // Retrieve Processes
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Process> processes = processRepository.findAll(pageable);

        if(processes.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), processes.getNumber(),
                    processes.getSize(), processes.getTotalElements(), processes.getTotalPages(), processes.isLast());
        }

        // Map Processes to ProcessResponses containing review counts and process creator details
        List<Long> processIds = processes.map(Process::getId).getContent();
        Map<Long, Long> opinionReviewCountMap = getOpinionReviewCountMap(processIds);
        Map<Long, Long> processUserReviewMap = getProcessUserReviewMap(currentUser, processIds);
        Map<Long, User> creatorMap = getProcessCreatorMap(processes.getContent());

        List<ProcessResponse> processResponses = processes.map(process -> {
            return ModelMapper.mapProcessToProcessResponse(process,
                    opinionReviewCountMap,
                    creatorMap.get(process.getCreatedBy()),
                    processUserReviewMap == null ? null : processUserReviewMap.getOrDefault(process.getId(), null));
        }).getContent();

        return new PagedResponse<>(processResponses, processes.getNumber(),
                processes.getSize(), processes.getTotalElements(), processes.getTotalPages(), processes.isLast());
    }

    public PagedResponse<ProcessResponse> getProcessCreatedBy(String username, UserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        // Retrieve all processes created by the given username
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Process> processes = processRepository.findByCreatedBy(user.getId(), pageable);

        if (processes.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), processes.getNumber(),
                    processes.getSize(), processes.getTotalElements(), processes.getTotalPages(), processes.isLast());
        }

        // Map Processes to ProcessResponses containing review counts and process creator details
        List<Long> processIds = processes.map(Process::getId).getContent();
        Map<Long, Long> opinionReviewCountMap = getOpinionReviewCountMap(processIds);
        Map<Long, Long> processUserReviewMap = getProcessUserReviewMap(currentUser, processIds);

        List<ProcessResponse> processResponses = processes.map(process -> {
            return ModelMapper.mapProcessToProcessResponse(process,
                    opinionReviewCountMap,
                    user,
                    processUserReviewMap == null ? null : processUserReviewMap.getOrDefault(process.getId(), null));
        }).getContent();

        return new PagedResponse<>(processResponses, processes.getNumber(),
                processes.getSize(), processes.getTotalElements(), processes.getTotalPages(), processes.isLast());
    }

    public PagedResponse<ProcessResponse> getProcessesReviewedBy(String username, UserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        // Retrieve all processesIds in which the given username has reviewed
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Long> userReviewedProcessIds = reviewRepository.findReviewedProcessIdsByUserId(user.getId(), pageable);

        if (userReviewedProcessIds.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), userReviewedProcessIds.getNumber(),
                    userReviewedProcessIds.getSize(), userReviewedProcessIds.getTotalElements(),
                    userReviewedProcessIds.getTotalPages(), userReviewedProcessIds.isLast());
        }

        // Retrieve all process details from the reviewd process.
        List<Long> processIds = userReviewedProcessIds.getContent();

        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        List<Process> processes = processRepository.findByIdIn(processIds, sort);

        // Map Processes to ProcessResponses containing review counts and process creator details
        Map<Long, Long> opinionReviewCountMap = getOpinionReviewCountMap(processIds);
        Map<Long, Long> processUserReviewMap = getProcessUserReviewMap(currentUser, processIds);
        Map<Long, User> creatorMap = getProcessCreatorMap(processes);

        List<ProcessResponse> processResponses = processes.stream().map(process -> {
            return ModelMapper.mapProcessToProcessResponse(process,
                    opinionReviewCountMap,
                    creatorMap.get(process.getCreatedBy()),
                    processUserReviewMap == null ? null : processUserReviewMap.getOrDefault(process.getId(), null));
        }).collect(Collectors.toList());

        return new PagedResponse<>(processResponses, userReviewedProcessIds.getNumber(), userReviewedProcessIds.getSize(), userReviewedProcessIds.getTotalElements(), userReviewedProcessIds.getTotalPages(), userReviewedProcessIds.isLast());
    }


    public Process createProcess(ProcessRequest processRequest) {
        Process process = new Process();
        process.setQuestion(processRequest.getQuestion());

        processRequest.getOpinions().forEach(opinionRequest -> {
            process.addOpinion(new Opinion(opinionRequest.getText()));
        });

//        processRequest.getReviewers().forEach(reviewerRequest -> {
//            process.addReviewer(reviewerRequest);
//        });
        process.setStatus("PENDING");
        Instant now = Instant.now();
        Instant expirationDateTime = now.plus(Duration.ofDays(processRequest.getProcessLength().getDays()))
                .plus(Duration.ofHours(processRequest.getProcessLength().getHours()));

        process.setExpirationDateTime(expirationDateTime);

        return processRepository.save(process);
    }

    public ProcessResponse getProcessById(Long processId, UserPrincipal currentUser) {
        Process process = processRepository.findById(processId).orElseThrow(
                () -> new ResourceNotFoundException("Process", "id", processId));

        // Retrieve Reviews Counts of every opinion belonging to the current process
        List<OpinionReviewCount> reviews = reviewRepository.countByProcessIdGroupByOpinionId(processId);

        Map<Long, Long> opinionReviewsMap = reviews.stream()
                .collect(Collectors.toMap(OpinionReviewCount::getOpnionId, OpinionReviewCount::getReviewCount));

        // Retrieve process creator details
        User creator = userRepository.findById(process.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", process.getCreatedBy()));

        // Retrieve review done by logged in user
        Review userReview = null;
        if(currentUser != null) {
            userReview = reviewRepository.findByUserIdAndProcessId(currentUser.getId(), processId);
        }

        return ModelMapper.mapProcessToProcessResponse(process, opinionReviewsMap,
                creator, userReview != null ? userReview.getOpinion().getId(): null);
    }

    public ProcessResponse castReviewAndGetUpdatedProcess(Long processId, ReviewRequest reviewRequest, UserPrincipal currentUser) {
        Process process = processRepository.findById(processId)
                .orElseThrow(() -> new ResourceNotFoundException("Process", "id", processId));

        if(process.getExpirationDateTime().isBefore(Instant.now())) {
            throw new BadRequestException("Sorry! This Process has already expired");
        }

        User user = userRepository.getOne(currentUser.getId());

        Opinion opinionAux = new Opinion(reviewRequest.getOpinion());
        opinionAux.setProcess(process);
        Opinion opinion = opinionRepository.save(opinionAux);
        Review review = new Review();
        review.setProcess(process);
        review.setUser(user);
        review.setOpinion(opinion);

        try {
            review = reviewRepository.save(review);
        } catch (DataIntegrityViolationException ex) {
            logger.info("User {} has already reviewd in Process {}", currentUser.getId(), processId);
            throw new BadRequestException("Sorry! You have already sent your revision in this process");
        }

        //-- Review Saved, Return the updated Process Response now --

        // Retrieve Review Counts of every opinion belonging to the current process
        List<OpinionReviewCount> reviews = reviewRepository.countByProcessIdGroupByOpinionId(processId);

        Map<Long, Long> opinionReviewsMap = reviews.stream()
                .collect(Collectors.toMap(OpinionReviewCount::getOpnionId, OpinionReviewCount::getReviewCount));

        // Retrieve process creator details
        User creator = userRepository.findById(process.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("Process", "id", process.getCreatedBy()));

        return ModelMapper.mapProcessToProcessResponse(process, opinionReviewsMap, creator, review.getOpinion().getId());
    }

    public void deleteProcessById(Long processId, UserPrincipal currentUser) throws ProcessNotFoundException {
        Process process = processRepository.findById(processId).orElseThrow(
                () -> new ResourceNotFoundException("Process", "id", processId));

        if(process != null) {
            processRepository.deleteById(processId);
        } else {
            throw new ProcessNotFoundException("Process with id "+processId+" not found");
        }
    }

    public void finishProcess(Long processId, UserPrincipal currentUser) throws ProcessNotFoundException {
        Process process = processRepository.findById(processId).orElseThrow(
                () -> new ResourceNotFoundException("Process", "id", processId));

        if(process != null) {
            process.setStatus("FINISHED");
            processRepository.save(process);
        } else {
            throw new ProcessNotFoundException("Process with id "+processId+" not found");
        }
    }

    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

    private Map<Long, Long> getOpinionReviewCountMap(List<Long> processesIds) {
        // Retrieve Reviews Counts of every Opinion belonging to the given processesIds
        List<OpinionReviewCount> reviews = reviewRepository.countByProcessIdInGroupByOpinionId(processesIds);

        Map<Long, Long> opinionReviewsMap = reviews.stream()
                .collect(Collectors.toMap(OpinionReviewCount::getOpnionId, OpinionReviewCount::getReviewCount));

        return opinionReviewsMap;
    }

    private Map<Long, Long> getProcessUserReviewMap(UserPrincipal currentUser, List<Long> processesIds) {
        // Retrieve Reviews done by the logged in user to the given processesIds
        Map<Long, Long> processUserReviewMap = null;
        if(currentUser != null) {
            List<Review> userReviews = reviewRepository.findByUserIdAndProcessIdIn(currentUser.getId(), processesIds);

            processUserReviewMap = userReviews.stream()
                    .collect(Collectors.toMap(review -> review.getProcess().getId(), review -> review.getOpinion().getId()));
        }
        return processUserReviewMap;
    }

    Map<Long, User> getProcessCreatorMap(List<Process> processes) {
        // Get Process Creator details of the given list of process
        List<Long> creatorIds = processes.stream()
                .map(Process::getCreatedBy)
                .distinct()
                .collect(Collectors.toList());

        List<User> creators = userRepository.findByIdIn(creatorIds);
        Map<Long, User> creatorMap = creators.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        return creatorMap;
    }
}