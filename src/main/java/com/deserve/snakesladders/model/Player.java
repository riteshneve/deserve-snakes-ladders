package com.deserve.snakesladders.model;

import com.deserve.snakesladders.constant.PlayerColor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Player {

	private PlayerColor color;
	private Integer position;

}
