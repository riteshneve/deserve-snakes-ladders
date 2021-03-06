package com.deserve.snakesladders.service;

import com.deserve.snakesladders.constant.GameConstants;
import com.deserve.snakesladders.constant.PlayerColor;
import com.deserve.snakesladders.exception.InvalidSnakeException;
import com.deserve.snakesladders.model.Board;
import com.deserve.snakesladders.model.Dice;
import com.deserve.snakesladders.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.MockitoAnnotations.initMocks;

public class BoardServiceTest {

	@InjectMocks
	private BoardService boardService;

	@Mock
	private DiceService diceService;

	@Mock
	private SnakeService snakeService;

	@BeforeEach
	void init() {
		initMocks(this);
	}

	@Test
	void testCreatedBoard_shouldCreateBoard() throws InvalidSnakeException {

		Board board = boardService.createBoard(false);
		Assertions.assertNotNull(board);

	}

	@Test
	void testRollDiceForPlayer_whenDiceRollsLessThan6_shouldChangeLocation() {

		Player player = new Player(PlayerColor.BLUE, 1);
		Board board = new Board(GameConstants.BOARD_SIZE, new ArrayList<>(), new ArrayList<>(), new Dice(false));

		Mockito.when(diceService.rollDice(board.getDice())).thenReturn(3);

		boardService.rollDiceForPlayer(board, player);

		Assertions.assertEquals(player.getPosition(), 4);

	}

	@Test
	void testRollDiceForPlayer_whenDiceRolls6_shouldChangeLocationThreeTimes() {

		Player player = new Player(PlayerColor.BLUE, 1);
		Board board = new Board(GameConstants.BOARD_SIZE, new ArrayList<>(), new ArrayList<>(), new Dice(false));

		Mockito.when(diceService.rollDice(board.getDice())).thenReturn(6);

		boardService.rollDiceForPlayer(board, player);

		Assertions.assertEquals(player.getPosition(), 19);

	}

	@Test
	void testChangePlayerPositionBy_whenNextPositionIsLessThan100_shouldChangePosition() {

		Player player = new Player(PlayerColor.BLUE, 19);
		Board board = new Board(GameConstants.BOARD_SIZE, new ArrayList<>(), new ArrayList<>(), new Dice(false));
		Mockito.doNothing().when(snakeService).applySnakeToPlayer(new ArrayList<>(), player);
		boardService.changePlayerPositionBy(board, player, 5);
		Assertions.assertEquals(player.getPosition(), 24);

	}

	@Test
	void testChangePlayerPositionBy_whenNextPositionIsMoreThan100_shouldNotChangePosition() {

		Player player = new Player(PlayerColor.BLUE, 99);
		Board board = new Board(GameConstants.BOARD_SIZE, new ArrayList<>(), new ArrayList<>(), new Dice(false));
		Mockito.doNothing().when(snakeService).applySnakeToPlayer(new ArrayList<>(), player);
		boardService.changePlayerPositionBy(board, player, 5);
		Assertions.assertEquals(player.getPosition(), 99);

	}

	@Test
	void isPlayerWon_shouldReturnReturnTrue_whenPositionIs100() {

		Player player = new Player(PlayerColor.BLUE, 100);
		boolean isWon = boardService.isPlayerWon(player);
		Assertions.assertTrue(isWon);

	}

	@Test
	void isPlayerWon_shouldReturnReturnFalse_whenPositionIsNot100() {

		Player player = new Player(PlayerColor.BLUE, 90);
		boolean isWon = boardService.isPlayerWon(player);
		Assertions.assertFalse(isWon);

	}

}
