package fa.training.validator;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.springframework.stereotype.Component;

@Documented
@Target({ ElementType.METHOD, ElementType.FIELD })
@Constraint(validatedBy =UniqueFacilityValidator.class)
@Retention(RUNTIME)
public @interface UniqueFacility {
	
	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
