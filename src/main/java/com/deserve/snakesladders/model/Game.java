package com.deserve.snakesladders.model;

import com.deserve.snakesladders.constant.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Game {

	private Board board;
	private List<Player> players;
	private GameStatus status;
	private Player winner;
	private Integer currentPlayer;

}
