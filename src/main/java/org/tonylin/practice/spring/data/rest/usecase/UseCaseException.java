package org.tonylin.practice.spring.data.rest.usecase;

public class UseCaseException extends Exception {
	private static final long serialVersionUID = 1L;

	public UseCaseException(String msg) {
		super(msg);
	}
}
