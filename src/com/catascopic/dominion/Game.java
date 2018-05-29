package com.catascopic.dominion;

import com.catascopic.dominion.modify.TemporaryContinuousEffect;
import com.catascopic.dominion.modify.Value;
import com.catascopic.dominion.zone.Selection;

public class Game {

	private int time; // = 0
	private AutoRemovingGroup<TemporaryContinuousEffect> continuousEffects =
			new AutoRemovingGroup<>();

	public int getTime() {
		return time;
	}

	public <E> E calculate(Value<E> value) {
		return value.get();
	}

	public void addContinuousEffect(
			TemporaryContinuousEffect continuousEffect) {
		continuousEffects.add(continuousEffect);
	}

	public Turn currentTurn() {
		// TODO Auto-generated method stub
		return null;
	}

}
