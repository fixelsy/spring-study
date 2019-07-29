package com.study.springboot;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ContentValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return ContentDto.class.isAssignableFrom(arg0);	//검증할 객체의 클래스 타입 정보
	}

	@Override
	public void validate(Object obj, Errors errors) {

		ContentDto dto = (ContentDto)obj;


		/**
		 * 내용 검증(Validator)
		 */
		/*
		String sWriter = dto.getWriter();
		if (sWriter == null || sWriter.trim().isEmpty()) {
			errors.rejectValue("writer", "Validator : Writer is null or empty");
		}

		String sContent = dto.getContent();
		if (sContent == null || sContent.trim().isEmpty()) {
			errors.rejectValue("content", "Validator : Content is null or empty");
		}
		*/

		/**
		 * 내용 검증(ValidationUtils)
		 */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer",	"ValidationUtils : writer is empty");
		String sWriter = dto.getWriter();
		if (sWriter.length() < 3) {
			errors.rejectValue("writer", "ValidationUtils : writer is too short");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "ValidationUtils : content is empty");

	}

}
