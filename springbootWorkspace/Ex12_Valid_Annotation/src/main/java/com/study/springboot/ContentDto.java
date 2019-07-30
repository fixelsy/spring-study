package com.study.springboot;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ContentDto {
	private int id;

	@NotNull(message="Writer is null.")
	@NotEmpty(message="Writer is empty.")
	@Size(min=3, max=10, message="Writer min3, max10.")
	private String writer;

	@NotNull(message="Content is null.")
	@NotEmpty(message="Content is empty.")
	private String content;
}
