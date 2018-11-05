package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProcessAlreadyFinishedDuringIncludeProcessOpinionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProcessAlreadyFinishedDuringIncludeProcessOpinionException(final String message) {
        super(message);
    }
}

