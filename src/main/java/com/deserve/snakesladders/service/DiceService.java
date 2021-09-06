package com.deserve.snakesladders.service;

import com.deserve.snakesladders.model.Dice;
import com.deserve.snakesladders.utils.RandomNumberGeneratorUtil;
import org.springframework.stereotype.Service;

@Service
public class DiceService {

	public Dice createDice(Boolean isCrooked) {
		return new Dice(isCrooked);
	}

	public int rollDice(Dice dice) {

		if (dice.getIsCrooked()) {
			return RandomNumberGeneratorUtil.generateRandomNumberBetween(1, 3) * 2;
		} else {
			return RandomNumberGeneratorUtil.generateRandomNumberBetween(1, 6);
		}

	}
}
