package com.deserve.snakesladders.service;

import com.deserve.snakesladders.model.Dice;
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
	void testCreateDice_shouldCreateDice() {
		Assertions.assertNotNull(diceService.createDice(false));
	}

	@Test
	void testRollDice_whenNotCrooked_shouldReturnValueBetween1And6() {

		Dice dice = new Dice(false);

		int value = diceService.rollDice(dice);
		Assertions.assertTrue(value <= 6);
		Assertions.assertTrue(value >= 1);

		value = diceService.rollDice(dice);
		Assertions.assertTrue(value <= 6);
		Assertions.assertTrue(value >= 1);

		value = diceService.rollDice(dice);
		Assertions.assertTrue(value <= 6);
		Assertions.assertTrue(value >= 1);

	}

	@Test
	void testRollDice_whenCrooked_shouldReturnEvenValueBetween1And6() {

		Dice dice = new Dice(true);

		int value = diceService.rollDice(dice);
		Assertions.assertTrue(value >= 1 && value <= 6 && value % 2 == 0);

		value = diceService.rollDice(dice);
		Assertions.assertTrue(value >= 1 && value <= 6 && value % 2 == 0);

		value = diceService.rollDice(dice);
		Assertions.assertTrue(value >= 1 && value <= 6 && value % 2 == 0);

	}
}
