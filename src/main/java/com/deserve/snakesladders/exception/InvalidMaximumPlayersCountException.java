package com.deserve.snakesladders.exception;

public class InvalidMaximumPlayersCountException extends Exception {

	public InvalidMaximumPlayersCountException() {
		super("Maximum 4 players can play");
	}

}
