package com.deserve.snakesladders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Board {

	private Integer size;
	private List<Snake> snakes;
	private List<Ladder> ladders;
	private Dice dice;

}
