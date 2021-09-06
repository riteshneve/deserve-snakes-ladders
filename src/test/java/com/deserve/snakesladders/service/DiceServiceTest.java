package com.deserve.snakesladders.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.mockito.MockitoAnnotations.initMocks;

public class DiceServiceTest {

	@InjectMocks
	private DiceService diceService;

	@BeforeEach
	void init() {
		initMocks(this);
	}

	@Test
	void testRollDice_shouldReturnValueBetween1And6() {

		int value = diceService.rollDice();
		Assertions.assertTrue(value <= 6);
		Assertions.assertTrue(value >= 1);

		value = diceService.rollDice();
		Assertions.assertTrue(value <= 6);
		Assertions.assertTrue(value >= 1);

		value = diceService.rollDice();
		Assertions.assertTrue(value <= 6);
		Assertions.assertTrue(value >= 1);

	}
}
