package com.isadora.backendapi.controller;

import com.isadora.backendapi.services.MapValidationErrorService;
import com.isadora.backendapi.services.ParecerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parecer")
@CrossOrigin(origins = "*")
public class ParecerController {

    @Autowired
    private ParecerService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;
}
