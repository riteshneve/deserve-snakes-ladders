package com.deserve.snakesladders.service;

import com.deserve.snakesladders.constant.GameConstants;
import com.deserve.snakesladders.constant.GameStatus;
import com.deserve.snakesladders.constant.PlayerColor;
import com.deserve.snakesladders.exception.InvalidMaximumPlayersCountException;
import com.deserve.snakesladders.exception.InvalidMinimumPlayersCountException;
import com.deserve.snakesladders.exception.InvalidSnakeException;
import com.deserve.snakesladders.model.Board;
import com.deserve.snakesladders.model.Dice;
import com.deserve.snakesladders.model.Game;
import com.deserve.snakesladders.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

public class GameServiceTest {

	@InjectMocks
	private GameService gameService;

	@Mock
	private BoardService boardService;

	@BeforeEach
	void init() {
		initMocks(this);
	}

	@Test
	void testCreateGame_whenPlayersAreValid_shouldCreateGame() throws InvalidMinimumPlayersCountException, InvalidMaximumPlayersCountException, InvalidSnakeException {

		List<Player> players = new ArrayList<>(Arrays.asList(new Player(PlayerColor.BLUE, 1)));

		Board board = new Board(GameConstants.BOARD_SIZE, null, null, new Dice(false));

		Mockito.when(boardService.createBoard(false)).thenReturn(board);

		Game game = gameService.createGame(players, false);

		Assertions.assertNotNull(game);
		Assertions.assertEquals(GameStatus.PENDING, game.getStatus());

	}

	@Test
	void testCreateGame_whenPlayersAreNotValid_shouldThrowException() {

		Assertions.assertThrows(
				InvalidMinimumPlayersCountException.class,
				() -> gameService.createGame(new ArrayList<>(), false)
		);

		List<Player> players = new ArrayList<>(Arrays.asList(new Player(PlayerColor.BLUE, 1), new Player(PlayerColor.BLUE, 1), new Player(PlayerColor.BLUE, 1), new Player(PlayerColor.BLUE, 1), new Player(PlayerColor.BLUE, 1)));

		Assertions.assertThrows(
				InvalidMaximumPlayersCountException.class,
				() -> gameService.createGame(players, false)
		);

	}

	@Test
	void testStartGame_whenGameIsValid_shouldSetStatusAndCurrentPlayer() throws InvalidMinimumPlayersCountException {

		Board board = new Board(GameConstants.BOARD_SIZE, null, null, new Dice(false));
		Game game = new Game(board, Arrays.asList(new Player(PlayerColor.BLUE, 1)), GameStatus.PENDING, null, null);

		gameService.startGame(game);
		Assertions.assertEquals(GameStatus.INPROGRESS, game.getStatus());
		Assertions.assertEquals(0, game.getCurrentPlayer());

	}

	@Test
	void testStartGame_whenGameDoesNotHavePlayers_shouldThrowException() {

		Board board = new Board(GameConstants.BOARD_SIZE, null, null, new Dice(false));

		Assertions.assertThrows(
				InvalidMinimumPlayersCountException.class,
				() -> {
					Game game = new Game(board, null, GameStatus.PENDING, null, null);
					gameService.startGame(game);
				}
		);

		Assertions.assertThrows(
				InvalidMinimumPlayersCountException.class,
				() -> {
					Game game = new Game(board, new ArrayList<>(), GameStatus.PENDING, null, null);
					gameService.startGame(game);
				}
		);

	}

	@Test
	void testEndGame_shouldSetStatusToFinishedAndWinner() {

		Board board = new Board(GameConstants.BOARD_SIZE, null, null, new Dice(false));
		Game game = new Game(board, null, GameStatus.INPROGRESS, null, null);
		Player winner = new Player(PlayerColor.BLUE, 100);

		gameService.endGame(game, winner);

		Assertions.assertEquals(GameStatus.FINISHED, game.getStatus());
		Assertions.assertNotNull(game.getWinner());
	}

	@Test
	void testIsGameFinished_whenGameIsFinshed_shouldReturnTrue() {

		Board board = new Board(GameConstants.BOARD_SIZE, null, null, new Dice(false));
		Game game = new Game(board, null, GameStatus.FINISHED, null, null);

		boolean isGameFinished = gameService.isGameFinished(game);
		Assertions.assertTrue(isGameFinished);

	}

	@Test
	void testIsGameFinished_whenGameIsNotFinshed_shouldReturnFalse() {

		Board board = new Board(GameConstants.BOARD_SIZE, null, null, new Dice(false));
		Game game = new Game(board, null, GameStatus.INPROGRESS, null, null);

		boolean isGameFinished = gameService.isGameFinished(game);
		Assertions.assertFalse(isGameFinished);

	}

	@Test
	void testGetCurrentPlayerFromGame_shouldReturnCurrentPlayer() {

		List<Player> players = new ArrayList<>(Arrays.asList(new Player(PlayerColor.BLUE, 1)));

		Board board = new Board(GameConstants.BOARD_SIZE, null, null, new Dice(false));
		Game game = new Game(board, players, GameStatus.INPROGRESS, null, 0);

		Player player = gameService.getCurrentPlayerForGame(game);
		Assertions.assertEquals(players.get(0), player);

	}

	@Test
	void testChangeCurrentPlayer_whenNextPlayerIsAvailable_shouldReturnNextPlayer() {

		List<Player> players = new ArrayList<>(Arrays.asList(new Player(PlayerColor.BLUE, 1), new Player(PlayerColor.GREEN, 1)));

		Board board = new Board(GameConstants.BOARD_SIZE, null, null, new Dice(false));
		Game game = new Game(board, players, GameStatus.INPROGRESS, null, 0);

		gameService.changeCurrentPlayer(game);

		Assertions.assertEquals(1, game.getCurrentPlayer());

	}

	@Test
	void testChangeCurrentPlayer_whenNextPlayerIsNotAvailable_shouldReturnFirstPlayer() {

		List<Player> players = new ArrayList<>(Arrays.asList(new Player(PlayerColor.BLUE, 1), new Player(PlayerColor.GREEN, 1)));

		Board board = new Board(GameConstants.BOARD_SIZE, null, null, new Dice(false));
		Game game = new Game(board, players, GameStatus.INPROGRESS, null, 1);

		gameService.changeCurrentPlayer(game);

		Assertions.assertEquals(0, game.getCurrentPlayer());

	}

	@Test
	void testRollDice_whenPlayerWins_shouldEndGame() {

		List<Player> players = new ArrayList<>(Arrays.asList(new Player(PlayerColor.BLUE, 1), new Player(PlayerColor.GREEN, 1)));

		Board board = new Board(GameConstants.BOARD_SIZE, null, null, new Dice(false));
		Game game = new Game(board, players, GameStatus.INPROGRESS, null, 0);

		Mockito.doNothing().when(boardService).rollDiceForPlayer(board, players.get(0));
		Mockito.when(boardService.isPlayerWon(players.get(0))).thenReturn(true);

		gameService.rollDice(game);

		Assertions.assertEquals(GameStatus.FINISHED, game.getStatus());

	}

	@Test
	void testRollDice_whenNoOneWins_shouldGoToNextPlayer() {

		List<Player> players = new ArrayList<>(Arrays.asList(new Player(PlayerColor.BLUE, 1), new Player(PlayerColor.GREEN, 1)));

		Board board = new Board(GameConstants.BOARD_SIZE, null, null, new Dice(false));
		Game game = new Game(board, players, GameStatus.INPROGRESS, null, 0);

		Mockito.doNothing().when(boardService).rollDiceForPlayer(board, players.get(0));
		Mockito.when(boardService.isPlayerWon(players.get(0))).thenReturn(false);

		gameService.rollDice(game);

		Assertions.assertEquals(1, game.getCurrentPlayer());

	}

}
