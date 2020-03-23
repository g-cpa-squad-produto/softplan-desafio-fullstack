package com.softplan.teste.process.controller;

import com.softplan.teste.process.exception.ProcessNotFoundException;
import com.softplan.teste.process.model.Process;
import com.softplan.teste.process.payload.*;
import com.softplan.teste.process.repository.ProcessRepository;
import com.softplan.teste.process.repository.UserRepository;
import com.softplan.teste.process.repository.ReviewRepository;
import com.softplan.teste.process.security.CurrentUser;
import com.softplan.teste.process.security.UserPrincipal;
import com.softplan.teste.process.service.ProcessService;
import com.softplan.teste.process.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/process")
public class ProcessController {

    @Autowired
    private ProcessRepository processRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProcessService processService;

    private static final Logger logger = LoggerFactory.getLogger(ProcessController.class);

    @PreAuthorize("hasRole('ROLE_TRIAGE_OFFICER')")
    @GetMapping
    public PagedResponse<ProcessResponse> getProcesses(@CurrentUser UserPrincipal currentUser,
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return processService.getAllProcesses(currentUser, page, size);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_TRIAGE_OFFICER')")
    public ResponseEntity<?> createProcess(@Valid @RequestBody ProcessRequest processRequest) {
        Process process = processService.createProcess(processRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{processId}")
                .buildAndExpand(process.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Process Created Successfully"));
    }

    @GetMapping("/{processId}")
    public ProcessResponse getProcessById(@CurrentUser UserPrincipal currentUser,
                                    @PathVariable Long processId) {
        return processService.getProcessById(processId, currentUser);
    }

    @PreAuthorize("hasRole('ROLE_FINISHER')")
    @GetMapping("/pending")
    public PagedResponse<ProcessResponse> getPendingProcesses(@CurrentUser UserPrincipal currentUser, @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return processService.getPendingProcesses(currentUser,page,size);
    }
    @DeleteMapping("/{processId}")
    @PreAuthorize("hasRole('USER')")
    public void deleteProcessById(@CurrentUser UserPrincipal currentUser,
                                          @PathVariable Long processId) throws ProcessNotFoundException {
        processService.deleteProcessById(processId, currentUser);
    }
    @PostMapping("/{processId}/reviews")
    @PreAuthorize("hasRole('ROLE_FINISHER')")
    public ProcessResponse castReview(@CurrentUser UserPrincipal currentUser,
                                 @PathVariable Long processId,
                                 @Valid @RequestBody ReviewRequest reviewRequest) {
        return processService.castReviewAndGetUpdatedProcess(processId, reviewRequest, currentUser);
    }

    @PostMapping("/{processId}/finish")
    @PreAuthorize("hasRole('ROLE_FINISHER')")
    public void finishProcess(@CurrentUser UserPrincipal currentUser, @PathVariable Long processId) throws ProcessNotFoundException {
        processService.finishProcess(processId, currentUser);
    }

}