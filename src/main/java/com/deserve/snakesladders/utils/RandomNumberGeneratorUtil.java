package com.deserve.snakesladders.utils;

public class RandomNumberGeneratorUtil {

	public static int generateRandomNumberBetween(int lower, int upper) {
		return (int) (Math.random() * (upper - lower)) + lower;
	}
}
