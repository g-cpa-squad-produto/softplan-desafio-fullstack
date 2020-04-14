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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/process")
public class ProcessController {

    @Autowired
    ProcessService processService;

    @GetMapping
    public ResponseEntity<PagedResponse<ProcessResponse>> getProcess(@CurrentUser UserPrincipal currentUser,
                                                     @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                     @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return ResponseEntity.ok(processService.getAllProcess(currentUser, page, size));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER_SCREENER','USER_FINISHER')")
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
