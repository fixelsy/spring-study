package com.study.springboot;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ContentValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return ContentDto.class.isAssignableFrom(arg0);	//������ ��ü�� Ŭ���� Ÿ�� ����
	}

	@Override
	public void validate(Object obj, Errors errors) {

		ContentDto dto = (ContentDto)obj;


		/**
		 * ���� ����(Validator)
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
		 * ���� ����(ValidationUtils)
		 */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer",	"ValidationUtils : writer is empty");
		String sWriter = dto.getWriter();
		if (sWriter.length() < 3) {
			errors.rejectValue("writer", "ValidationUtils : writer is too short");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "ValidationUtils : content is empty");

	}

}
