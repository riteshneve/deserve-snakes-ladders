package com.deserve.snakesladders;

import com.deserve.snakesladders.constant.GameConstants;
import com.deserve.snakesladders.exception.InvalidMaximumPlayersCountException;
import com.deserve.snakesladders.exception.InvalidMinimumPlayersCountException;
import com.deserve.snakesladders.exception.InvalidPlayerException;
import com.deserve.snakesladders.exception.InvalidSnakeException;
import com.deserve.snakesladders.model.Game;
import com.deserve.snakesladders.model.Player;
import com.deserve.snakesladders.service.GameService;
import com.deserve.snakesladders.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SnakesAndLaddersApplication implements ApplicationRunner {

	@Autowired
	private GameService gameService;

	@Autowired
	private PlayerService playerService;

	public static void main(String[] args) {
		SpringApplication.run(SnakesAndLaddersApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {

		boolean isCrooked = false;

		List<String> customArgs = args.getOptionValues("isCrooked");

		if (customArgs != null && customArgs.size() > 0) {
			isCrooked = Boolean.parseBoolean(customArgs.get(0));
		}

		if (isCrooked) {
			System.out.println("Your dice is crooked!");
		}

		try {

			List<Player> players = playerService.createRandomPlayers(1);

			Game game = gameService.createGame(players, isCrooked);

			gameService.startGame(game);

			int turn = 1;

			while (!gameService.isGameFinished(game) && turn <= GameConstants.MAX_ALLOWED_TURNS) {

				gameService.rollDice(game);
				turn += 1;

			}

		} catch (InvalidPlayerException | InvalidMaximumPlayersCountException | InvalidMinimumPlayersCountException | InvalidSnakeException e) {

			System.out.println("ERROR : ");
			System.out.println(e.getMessage());

		}

	}

}
