package com.isadora.backendapi.services;

import com.isadora.backendapi.repositories.ParecerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParecerService {

    @Autowired
    private ParecerRepository parecerRepository;
}
