package com.deserve.snakesladders.service;

import com.deserve.snakesladders.constant.GameConstants;
import com.deserve.snakesladders.exception.InvalidSnakeException;
import com.deserve.snakesladders.model.Player;
import com.deserve.snakesladders.model.Snake;
import com.deserve.snakesladders.utils.RandomNumberGeneratorUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SnakeService {

	public Snake createSnake(Integer head, Integer tail) throws InvalidSnakeException {

		if (head >= GameConstants.BOARD_SIZE) {
			throw new InvalidSnakeException(head, tail);
		}

		if (tail < 1) {
			throw new InvalidSnakeException(head, tail);
		}

		if (head <= tail) {
			throw new InvalidSnakeException(head, tail);
		}

		return new Snake(head, tail);
	}

	public Snake createRandomSnake() throws InvalidSnakeException {

		int head = RandomNumberGeneratorUtil.generateRandomNumberBetween(11, 99);
		int tail = RandomNumberGeneratorUtil.generateRandomNumberBetween(1, head);
		return createSnake(head, tail);

	}

	public boolean isNewSnakeAllowedAtPosition(Set<Integer> usedPositions, Snake snake) {
		return !usedPositions.contains(snake.getHead()) && !usedPositions.contains(snake.getTail());
	}

	public List<Snake> createRandomSnakes(int count) throws InvalidSnakeException {

		Set<Integer> usedPositions = new HashSet<>();

		List<Snake> snakes = new ArrayList<>();

		int i = 0;

		while (i < count) {

			Snake newSnake = createRandomSnake();

			if (isNewSnakeAllowedAtPosition(usedPositions, newSnake)) {

				snakes.add(newSnake);
				usedPositions.add(newSnake.getHead());
				usedPositions.add(newSnake.getTail());

				i++;
			}

		}

		return snakes;

	}

	public void applySnakeToPlayer(List<Snake> snakes, Player player) {

		for (Snake snake: snakes) {
			if (snake.getHead().equals(player.getPosition())) {
				System.out.println("Snake bit at " + player.getPosition());
				player.setPosition(snake.getTail());
			}
		}

	}

}
