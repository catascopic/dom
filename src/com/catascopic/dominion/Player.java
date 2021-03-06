package com.catascopic.dominion;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import com.catascopic.dominion.event.TrashEvent;
import com.catascopic.dominion.zone.Deck;
import com.catascopic.dominion.zone.DiscardPile;
import com.catascopic.dominion.zone.Selectable;
import com.catascopic.dominion.zone.Selection;
import com.catascopic.dominion.zone.SingleSelection;
import com.catascopic.dominion.zone.TemporaryZone;
import com.catascopic.dominion.zone.UnorderedZone;
import com.google.common.base.Predicate;

public class Player {

	private static final int SELECT_MAX = 99;

	private final Game game;

	private int actions;
	private int coins;
	private int buys;

	private Deck deck;
	private DiscardPile discardPile;
	private UnorderedZone inPlay;
	private UnorderedZone hand;

	Player(Game game, Card... inPlay) {
		this.game = game;
		this.inPlay = new UnorderedZone(Arrays.asList(inPlay));
	}

	public Player(Game game) {
		this.game = game;
	}

	public Game game() {
		return game;
	}

	public UnorderedZone hand() {
		return hand;
	}

	public Deck deck() {
		return deck;
	}

	public DiscardPile discardPile() {
		return discardPile;
	}

	public UnorderedZone inPlay() {
		return inPlay;
	}

	// Vanilla bonuses

	public void addCoin(Source source) {
		addCoins(1, source);
	}

	public void addCoins(int amount, Source source) {
		coins += amount;
	}

	public void addAction(Source source) {
		addActions(1, source);
	}

	public void addActions(int amount, Source source) {
		actions += amount;
	}

	public void draw(Source source) {
		draw(1, source);
	}

	public void draw(int amount, Source source) {
		// TODO
	}

	public void addBuy(Source source) {
		addBuys(1, source);
	}

	private void addBuys(int amount, Source source) {
		buys += amount;
	}

	// Card selection

	public SingleSelection selectOne(Selectable zone,
			Predicate<Card> filter,
			Prompt prompt) {
		return selectExactly(zone, 1, filter, prompt);
	}

	public SingleSelection maySelectOne(Selectable zone,
			Predicate<Card> filter,
			Prompt prompt) {
		return selectUpTo(zone, 1, filter, prompt);
	}

	public Selection selectExactly(Selectable zone,
			int amount,
			Predicate<Card> filter,
			Prompt prompt) {
		return select(zone, amount, amount, filter, prompt);
	}

	public Selection selectAnyNumber(Selectable zone,
			Predicate<Card> filter,
			Prompt prompt) {
		return selectUpTo(zone, SELECT_MAX, filter, prompt);
	}

	public Selection selectUpTo(Selectable zone,
			int max,
			Predicate<Card> filter,
			Prompt prompt) {
		return select(zone, 0, max, filter, prompt);
	}

	public Selection select(Selectable zone,
			int min,
			int max,
			Predicate<Card> filter,
			Prompt prompt) {
		return null;
	}

	// Choices

	public Choice chooseOne(Prompt prompt, int choices) {
		return Choice.FIRST;
	}

	public Set<Choice> chooseTwo(Prompt prompt, int choices) {
		return EnumSet.of(Choice.FIRST, Choice.SECOND);
	}

	public boolean yesNo(Prompt prompt) {
		// TODO Auto-generated method stub
		return false;
	}

	/* Returns true if all conditions are met: 1. A card is selected; 2. The
	 * trashing isn't replaced by another effect; 3. The card hasn't been lost
	 * track of; 4. The card isn't already in the trash. */
	// TODO: Optional<Card>?
	public boolean trash(Source source, SingleSelection selection) {
		if (game.trash().accept(selection)) {
			TrashEvent event = new TrashEvent(this, selection.get());
			game.handleMoveEvent(event);
			return true;
		}
		return false;
	}

	public Collection<Card> trash(Source source, Selection selection) {
		return game.trash().accept(selection);
	}

	public Collection<Card> discard(Source source, Selection selection) {
		return discardPile.acceptTop(selection);
	}

	public boolean discard(Source source, SingleSelection selection) {
		// TODO Auto-generated method stub
		return true;
	}

	public void moveOntoDeck(Source source, Selection selection) {
		// TODO Auto-generated method stub
	}

	public boolean moveOntoDeck(Source source, SingleSelection selection) {
		// TODO Auto-generated method stub
		return true;
	}

	public void play(SingleSelection selection) {
		// TODO Auto-generated method stub
	}

	public void play(Source source, SingleSelection selection) {
		// TODO Auto-generated method stub
	}

	public boolean gainToDeck(Source source, SingleSelection selection) {
		// TODO: trigger gain
		return moveOntoDeck(source, selection);
	}

	public Iterable<Player> getAttacks(Activation activation) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean gain(Source source, SingleSelection selection) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean gain(Source source, PileName pile) {
		// TODO Auto-generated method stub
		return true;
	}

	public void gain(Source source, Name name) {
		// TODO Auto-generated method stub

	}

	public boolean gainToDeck(Source source, PileName pile) {
		return gainToDeck(source, game.supply().select(pile));
	}

	public Iterable<Player> opponents() {
		// TODO Auto-generated method stub
		return null;
	}

	public SingleSelection drawAndSelect(Activation activation) {
		// TODO Auto-generated method stub
		return null;
	}

	public void move(Activation activation, SingleSelection selection,
			TemporaryZone setAside) {
		// TODO Auto-generated method stub

	}

	public boolean gainToHand(Activation activation,
			SingleSelection selectOne) {
		// TODO Auto-generated method stub
		return true;
	}

}
