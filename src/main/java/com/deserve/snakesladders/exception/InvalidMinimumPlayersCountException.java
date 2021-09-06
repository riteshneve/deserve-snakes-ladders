package com.deserve.snakesladders.exception;

public class InvalidMinimumPlayersCountException extends Exception {

	public InvalidMinimumPlayersCountException() {
		super("Minimum 1 player is required");
	}

}
