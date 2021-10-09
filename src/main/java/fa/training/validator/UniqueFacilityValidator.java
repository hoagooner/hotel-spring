package fa.training.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import fa.training.services.FacilityService;

public class UniqueFacilityValidator implements ConstraintValidator<UniqueFacility, String> {

	@Autowired
	private FacilityService facilityServiceImpl;

	@Override
	public void initialize(UniqueFacility unique) {
		unique.message();
	}

	// so sanh name khi update
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		if (facilityServiceImpl != null && facilityServiceImpl.checkNameExists(name) != null) {
			return false;
		}
		return true;
	}

}
