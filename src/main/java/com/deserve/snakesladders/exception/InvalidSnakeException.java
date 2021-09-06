package com.deserve.snakesladders.exception;

public class InvalidSnakeException extends Exception {

	public InvalidSnakeException(Integer head, Integer tail) {
		super("Invalid snake with head " + head + " and tail " + tail);
	}

}
