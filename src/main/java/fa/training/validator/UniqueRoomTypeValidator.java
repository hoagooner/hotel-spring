package fa.training.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import fa.training.services.RoomTypeService;

public class UniqueRoomTypeValidator implements ConstraintValidator<UniqueRoomType, String> {

	@Autowired
	private RoomTypeService roomTypeService;

	@Override
	public void initialize(UniqueRoomType unique) {
		unique.message();
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		if (roomTypeService != null && roomTypeService.checkNameExists(name) != null) {
			return false;
		}
		return true;
	}

}