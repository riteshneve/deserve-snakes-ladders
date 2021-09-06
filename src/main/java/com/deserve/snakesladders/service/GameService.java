package com.deserve.snakesladders.service;

import com.deserve.snakesladders.constant.GameConstants;
import com.deserve.snakesladders.constant.GameStatus;
import com.deserve.snakesladders.exception.InvalidMaximumPlayersCountException;
import com.deserve.snakesladders.exception.InvalidMinimumPlayersCountException;
import com.deserve.snakesladders.model.Board;
import com.deserve.snakesladders.model.Game;
import com.deserve.snakesladders.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

	@Autowired
	private BoardService boardService;

	public Game createGame(List<Player> players) throws InvalidMinimumPlayersCountException, InvalidMaximumPlayersCountException {

		if (players.size() < GameConstants.MINIMUM_PLAYERS) {
			throw new InvalidMinimumPlayersCountException();
		}

		if (players.size() > GameConstants.MINIMUM_PLAYERS) {
			throw new InvalidMaximumPlayersCountException();
		}

		System.out.println("Creating game");

		Board board = boardService.createBoard();

		return new Game(board, players, GameStatus.PENDING, null, null);

	}

	public void startGame(Game game) throws InvalidMinimumPlayersCountException {

		System.out.println("Starting game");

		game.setStatus(GameStatus.INPROGRESS);

		if (game.getPlayers() != null && game.getPlayers().size() > 0) {

			game.setCurrentPlayer(0);

		} else {

			throw new InvalidMinimumPlayersCountException();

		}

	}

	public void endGame(Game game, Player winner) {

		System.out.println("Ending game");
		System.out.println("Winner is : " + winner.getColor());

		game.setStatus(GameStatus.FINISHED);
		game.setWinner(winner);

	}

	public boolean isGameFinished(Game game) {
		return GameStatus.FINISHED.equals(game.getStatus());
	}

	public Player getCurrentPlayerForGame(Game game) {

		return game.getPlayers().get(game.getCurrentPlayer());

	}

	public void changeCurrentPlayer(Game game) {

		game.setCurrentPlayer((game.getCurrentPlayer() + 1) % game.getPlayers().size());

	}

	public void rollDice(Game game) {

		Player currentPlayer = getCurrentPlayerForGame(game);

		boardService.rollDiceForPlayer(currentPlayer);

		if (boardService.isPlayerWon(currentPlayer)) {

			endGame(game, currentPlayer);

		} else {

			changeCurrentPlayer(game);

		}

	}

}
