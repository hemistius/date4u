package de.materna.trainee.date4u.db.dto.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AdultValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Adult {
    String message() default "must be at least 18 Years ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
