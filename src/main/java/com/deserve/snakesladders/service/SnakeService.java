package com.deserve.snakesladders.service;

import com.deserve.snakesladders.model.Snake;
import com.deserve.snakesladders.utils.RandomNumberGeneratorUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SnakeService {

	public Snake createSnake(Integer start, Integer end) {
		return new Snake(start, end);
	}

	public Snake createRandomSnake() {

		int head = RandomNumberGeneratorUtil.generateRandomNumberBetween(11, 99);
		int tail = RandomNumberGeneratorUtil.generateRandomNumberBetween(1, head);
		return createSnake(head, tail);

	}

	public List<Snake> createRandomSnakes(int count) {

		List<Snake> snakes = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			snakes.add(createRandomSnake());
		}

		return snakes;

	}

}
