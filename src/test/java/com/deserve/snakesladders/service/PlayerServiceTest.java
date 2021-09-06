package com.deserve.snakesladders.service;

import com.deserve.snakesladders.constant.PlayerColor;
import com.deserve.snakesladders.exception.InvalidMaximumPlayersCountException;
import com.deserve.snakesladders.exception.InvalidMinimumPlayersCountException;
import com.deserve.snakesladders.exception.InvalidPlayerException;
import com.deserve.snakesladders.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

public class PlayerServiceTest {

	@InjectMocks
	private PlayerService playerService;

	@BeforeEach
	void init() {
		initMocks(this);
	}

	@Test
	void testCreatePlayerWithColor_whenColorIsValid_shouldCreatePlayer() throws InvalidPlayerException {

		Player player = playerService.createPlayerWithColor(PlayerColor.BLUE);
		Assertions.assertNotNull(player);

	}

	@Test
	void testCreatePlayerWithColor_whenColorIsNotValid_shouldThrowException() {

		Assertions.assertThrows(
				InvalidPlayerException.class,
				() -> playerService.createPlayerWithColor(null)
		);

	}

	@Test
	void testCreateRandomPlayers_whenNumberOfPlayersIsValid_shouldCreatePlayers() throws InvalidMaximumPlayersCountException, InvalidMinimumPlayersCountException, InvalidPlayerException {

		List<Player> players = playerService.createRandomPlayers(2);
		Assertions.assertEquals(2, players.size());

	}

	@Test
	void testCreateRandomPlayers_whenNumberOfPlayersIsLessThanMin_shouldThrowException() {

		Assertions.assertThrows(
				InvalidMinimumPlayersCountException.class,
				() -> playerService.createRandomPlayers(0)
		);

	}

	@Test
	void testCreateRandomPlayers_whenNumberOfPlayersIsMoreThanMax_shouldThrowException() {

		Assertions.assertThrows(
				InvalidMaximumPlayersCountException.class,
				() -> playerService.createRandomPlayers(5)
		);

	}
}
