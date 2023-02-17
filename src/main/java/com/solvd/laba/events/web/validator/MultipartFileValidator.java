package com.solvd.laba.events.web.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.stream.Stream;

public class MultipartFileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private static final long MAX_SIZE = 10000L;

    private static final Stream<String> extensions = Stream.of(".jpg", ".jpeg");

    @Override
    public void initialize(ValidFile constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if(value == null || !isValidExtension(value) || !isValidSize(value)){
            return false;
        }
        return true;
    }

    private boolean isValidSize(MultipartFile file){
        long currentSize = file.getSize();
        if(currentSize< MAX_SIZE){
            return true;
        }
        return false;
    }

    private boolean isValidExtension(MultipartFile file){
        return extensions.anyMatch(e -> e.contains(Objects.requireNonNull(file.getContentType())));
    }

}
