package a.b.c.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import a.b.c.dto.TestDto;


public class Eventvalidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return TestDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TestDto dto = (TestDto) target;
		
		if(dto.getId() < 0 ) 
            // field, errorCode, default_Message
			errors.rejectValue("id", "01", "id�� 0���� ���� �� �����ϴ�.");
		
		if(dto.getName() == null ) 
            errors.rejectValue("name", "02", "�̸��� ����� �� �����ϴ�.");
		
	}

}
