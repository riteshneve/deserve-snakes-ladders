package com.deserve.snakesladders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Board {

	Integer size;
	List<Snake> snakes;
	List<Ladder> ladders;

}
