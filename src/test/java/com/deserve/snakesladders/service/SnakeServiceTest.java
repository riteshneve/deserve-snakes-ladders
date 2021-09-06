package com.deserve.snakesladders.service;

import com.deserve.snakesladders.constant.GameConstants;
import com.deserve.snakesladders.constant.PlayerColor;
import com.deserve.snakesladders.exception.InvalidSnakeException;
import com.deserve.snakesladders.model.Player;
import com.deserve.snakesladders.model.Snake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.*;

public class SnakeServiceTest {

	@InjectMocks
	private SnakeService snakeService;

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testCreateSnake_whenHeadAndTailAreValid_shouldCreateSnake() throws InvalidSnakeException {
		Snake snake = snakeService.createSnake(50, 30);
		Assertions.assertNotNull(snake);
	}

	@Test
	void testCreateSnake_whenHeadIsLessThanTail_shouldThrowException() {
		Assertions.assertThrows(
				InvalidSnakeException.class,
				() -> snakeService.createSnake(50, 60)
		);
	}

	@Test
	void testCreateSnake_whenHeadIsGreaterThanOrEqualsMaxSize_shouldThrowException() {
		Assertions.assertThrows(
				InvalidSnakeException.class,
				() -> snakeService.createSnake(GameConstants.BOARD_SIZE, 60)
		);
	}

	@Test
	void testCreateSnake_whenTailIsLessThan1_shouldThrowException() {
		Assertions.assertThrows(
				InvalidSnakeException.class,
				() -> snakeService.createSnake(50, 0)
		);
	}

	@Test
	void createRandomSnake_shouldCreateSnake() throws InvalidSnakeException {
		Assertions.assertNotNull(snakeService.createRandomSnake());
	}

	@Test
	void testIsNewSnakeAllowedAtPosition_whenHeadAndTailPositionAreUnused_shouldReturnTrue() {
		Set<Integer> usedPositions = new HashSet<>(Arrays.asList(89, 78, 54, 50));
		Snake newSnake = new Snake(70, 25);
		Assertions.assertTrue(snakeService.isNewSnakeAllowedAtPosition(usedPositions, newSnake));
	}

	@Test
	void testIsNewSnakeAllowedAtPosition_whenHeadIsUsed_shouldReturnFalse() {
		Set<Integer> usedPositions = new HashSet<>(Arrays.asList(89, 78, 54, 50));
		Snake newSnake = new Snake(89, 25);
		Assertions.assertFalse(snakeService.isNewSnakeAllowedAtPosition(usedPositions, newSnake));
	}

	@Test
	void testIsNewSnakeAllowedAtPosition_whenTailIsUsed_shouldReturnFalse() {
		Set<Integer> usedPositions = new HashSet<>(Arrays.asList(89, 78, 54, 50));
		Snake newSnake = new Snake(56, 50);
		Assertions.assertFalse(snakeService.isNewSnakeAllowedAtPosition(usedPositions, newSnake));
	}

	@Test
	void testCreateRandomSnakes_shouldReturnSnakesEqualToCount() throws InvalidSnakeException {

		List<Snake> snakes= snakeService.createRandomSnakes(10);
		Assertions.assertEquals(10, snakes.size());

	}

	@Test
	void testApplySnakeToPlayer_whenSnakeIsPresentAtPosition_shouldChangePositionToTail() {

		Snake snake = new Snake(60, 15);
		Player player = new Player(PlayerColor.BLUE, 60);
		snakeService.applySnakeToPlayer(Arrays.asList(snake), player);
		Assertions.assertEquals(15, player.getPosition());

	}

	@Test
	void testApplySnakeToPlayer_whenSnakeIsNotPresentAtPosition_shouldNotChangePosition() {

		Snake snake = new Snake(60, 15);
		Player player = new Player(PlayerColor.BLUE, 48);
		snakeService.applySnakeToPlayer(Arrays.asList(snake), player);
		Assertions.assertEquals(48, player.getPosition());

	}
}
