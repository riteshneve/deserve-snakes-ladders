package com.deserve.snakesladders.service;

import com.deserve.snakesladders.constant.GameConstants;
import com.deserve.snakesladders.exception.InvalidSnakeException;
import com.deserve.snakesladders.model.Board;
import com.deserve.snakesladders.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardService {

	@Autowired
	private DiceService diceService;

	@Autowired
	private SnakeService snakeService;

	public Board createBoard(Boolean isCrooked) throws InvalidSnakeException {

		return new Board(GameConstants.BOARD_SIZE, snakeService.createRandomSnakes(10), new ArrayList<>(), diceService.createDice(isCrooked));

	}

	public void rollDiceForPlayer(Board board, Player player) {

		int diceValue = 6;

		int count = 0;

		while (diceValue == 6 && count < 3) {

			diceValue = diceService.rollDice(board.getDice());

			changePlayerPositionBy(board, player, diceValue);

			System.out.println("Changed position of " + player.getColor().toString() + " to " + player.getPosition());

			count += 1;

		}

	}

	public void changePlayerPositionBy(Board board, Player player, int diceValue) {

		int nextPosition = player.getPosition() + diceValue;

		if (nextPosition <= 100) {

			player.setPosition(nextPosition);

			snakeService.applySnakeToPlayer(board.getSnakes(), player);

		}
	}

	public boolean isPlayerWon(Player player) {
		return player.getPosition() == GameConstants.BOARD_SIZE;
	}

}
