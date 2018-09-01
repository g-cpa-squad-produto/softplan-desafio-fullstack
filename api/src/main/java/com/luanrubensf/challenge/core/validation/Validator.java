package com.luanrubensf.challenge.core.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Validator {

    @Autowired
    private javax.validation.Validator validator;

    public void validate(Object entity, Class<?>... groups) {
        Set<ConstraintViolation<Object>> validate = validator.validate(entity, groups);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
    }

    public <T> void validate(T candidate, AbstractValidation... validators) {
        validate(candidate, Default.class);

        if (validators == null) {
            return;
        }

        List<ValidationConstraint> constraints = Stream.of(validators)
                .map(v -> v.validate(candidate))
                .collect(Collectors.toList());

        constraints.stream()
                .filter(c -> !c.isSatisfied())
                .findFirst()
                .map(c -> {
                    throw new ValidationException(c.getMessage());
                });
    }
}
