package com.softplan.processmanagerapi.controllers;

import com.softplan.processmanagerapi.models.Process;
import com.softplan.processmanagerapi.payload.request.ProcessRequest;
import com.softplan.processmanagerapi.payload.response.ApiResponse;
import com.softplan.processmanagerapi.payload.response.PagedResponse;
import com.softplan.processmanagerapi.payload.response.ProcessResponse;
import com.softplan.processmanagerapi.security.CurrentUser;
import com.softplan.processmanagerapi.security.UserPrincipal;
import com.softplan.processmanagerapi.services.ProcessService;
import com.softplan.processmanagerapi.util.AppConstants;
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
    ProcessService processService;

    @GetMapping
    public PagedResponse<ProcessResponse> getProcess(@CurrentUser UserPrincipal currentUser,
                                                     @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                     @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return processService.getAllProcess(currentUser, page, size);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER_SCREENER')")
    public ResponseEntity<?> createProcess(@CurrentUser UserPrincipal currentUser,
                                           @Valid @RequestBody ProcessRequest processRequest) {
        Process process = processService.createProcess(processRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{processId}")
                .buildAndExpand(process.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Processo criado com sucesso!"));
    }
}
