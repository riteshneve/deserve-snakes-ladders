package com.deserve.snakesladders.service;

import com.deserve.snakesladders.utils.RandomNumberGeneratorUtil;
import org.springframework.stereotype.Service;

@Service
public class DiceService {

	public int rollDice() {

		return RandomNumberGeneratorUtil.generateRandomNumberBetween(1, 6);

	}
}
