package com.deserve.snakesladders.service;

import com.deserve.snakesladders.constant.GameConstants;
import com.deserve.snakesladders.model.Board;
import com.deserve.snakesladders.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardService {

	@Autowired
	private DiceService diceService;

	public Board createBoard() {

		// TODO: Create snakes and ladders
		return new Board(GameConstants.BOARD_SIZE, new ArrayList<>(), new ArrayList<>());

	}

	public void rollDiceForPlayer(Player player) {

		int diceValue = 6;

		int count = 0;

		while (diceValue == 6 && count < 3) {

			diceValue = diceService.rollDice();

			changePlayerPositionBy(player, diceValue);

			System.out.println("Changed position of " + player.getColor().toString() + " to " + player.getPosition());

			count += 1;

		}

	}

	public void changePlayerPositionBy(Player player, int diceValue) {

		int nextPosition = player.getPosition() + diceValue;

		if (nextPosition <= 100) {
			// TODO: check snakes and ladders
			player.setPosition(nextPosition);
		}
	}

	public boolean isPlayerWon(Player player) {
		return player.getPosition() == GameConstants.BOARD_SIZE;
	}

}
