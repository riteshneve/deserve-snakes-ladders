package com.deserve.snakesladders.service;

import com.deserve.snakesladders.constant.GameConstants;
import com.deserve.snakesladders.constant.PlayerColor;
import com.deserve.snakesladders.exception.InvalidMaximumPlayersCountException;
import com.deserve.snakesladders.exception.InvalidMinimumPlayersCountException;
import com.deserve.snakesladders.exception.InvalidPlayerException;
import com.deserve.snakesladders.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

	public Player createPlayerWithColor(PlayerColor color) throws InvalidPlayerException {
		if (color == null) {
			throw new InvalidPlayerException("Player color is not present");
		}
		return new Player(color, 1);
	}

	public List<Player> createRandomPlayers(int numberOfPlayers) throws InvalidMaximumPlayersCountException, InvalidMinimumPlayersCountException, InvalidPlayerException {

		if (numberOfPlayers >  GameConstants.MAXIMUM_PLAYERS) {
			throw new InvalidMaximumPlayersCountException();
		}

		if (numberOfPlayers <  GameConstants.MINIMUM_PLAYERS) {
			throw new InvalidMinimumPlayersCountException();
		}

		List<Player> players = new ArrayList<>();

		for (int i = 0; i < numberOfPlayers; i++) {

			players.add(createPlayerWithColor(PlayerColor.values()[i]));

		}

		return players;
	}
}
