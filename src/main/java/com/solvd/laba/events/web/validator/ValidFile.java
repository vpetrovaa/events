package com.solvd.laba.events.web.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MultipartFileValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFile {

    String message() default "Only jpg or jpeg images less than 10000L are allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
