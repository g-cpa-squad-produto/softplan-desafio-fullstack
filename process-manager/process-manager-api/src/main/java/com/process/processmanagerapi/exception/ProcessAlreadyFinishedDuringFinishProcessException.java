package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProcessAlreadyFinishedDuringFinishProcessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProcessAlreadyFinishedDuringFinishProcessException(final String message) {
        super(message);
    }
}

