/*
 * Six Wins
 *  Copyright (C) 2020  Clemens Bartz
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.clemens.games.sixwins.entities;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a game that is being played.
 * @since 2.0
 * @author Clemens Bartz
 */
final class Game {

    /** The maximum value for a dice. */
    private static final int DICEMAX = 6;
    /** The minimum value for a dice. */
    private static final int DICEMIN = 1;
    /** The dice value when a stick should go to the box. */
    private static final int BOXVALUE = 6;

    /** The queue with all player indices. */
    private final Queue<Player> players;
    /** The dice to be used. */
    private final Random dice;
    /** The round we are in. */
    private Integer round = 1;
    /** The number of dices for a player. */
    private Integer numberOfDices = 0;
    /** The box to hold the sticks when a six was diced. */
    private final Box box;
    /** The playfield. */
    private final Playfield playfield;
    /** The currently active player. */
    private Player activePlayer;
    /** Game is finished. */
    private boolean isFinished = false;

    /**
     * Construct a new game.
     * @param riskAttitudes the risk attitudes of the players
     * @param random the random number generator for this game
     */
    Game(final ERiskAttitudes[] riskAttitudes, final Random random) {
        this.players = IntStream.range(0, riskAttitudes.length).mapToObj(value -> new Player(value, riskAttitudes[value])).collect(Collectors.toCollection(LinkedList::new));
        /** The risk attitudes of the players. */
        this.dice = random;
        this.box = new Box();
        this.playfield = new Playfield();
        this.activePlayer = players.poll();
    }

    /**
     *
     * @return the id of the winner
     */
    int getWinner() {
        Player winner;

        do {
            final int diceValue = dice.nextInt(DICEMAX) + DICEMIN;

            winner = advance(diceValue);
        } while (winner == null);

        return winner.getId();
    }

    /**
     * Advance the game.
     * @param diceValue the value of the dice.
     * @return the player who has won, or <code>null</code> if the game can advance
     */
    private Player advance(final int diceValue) {
        boolean passToNextPlayer = false;

        // Check if game is finished
        if (isFinished) {
            return activePlayer;
        }

        // Check if active player does not have any sticks
        if (activePlayer.getNumberOfSticks() <= 0) {
            isFinished = true;
            return activePlayer;
        }

        // Check if dice value is in range
        if (diceValue < DICEMIN || diceValue > DICEMAX) {
            throw new RuntimeException("Wrong dice value");
        }

        // Increase the number of dices
        numberOfDices++;

        // Check if it was a six
        if (diceValue == BOXVALUE) {
            final Stick playersStick = activePlayer.takeStick();
            box.addStick(playersStick);
        } else {
            final Stick playfieldStick = playfield.pop(diceValue);

            if (playfieldStick == null) {
                // If there was no value, put it there
                final Stick playerStick = activePlayer.takeStick();
                playfield.push(diceValue, playerStick);
            } else {
                // If there already was a stick, give it to the player and give them no choice
                passToNextPlayer = true;
                activePlayer.addStick(playfieldStick);
            }
        }

        // Check if active player does not have any sticks
        if (activePlayer.getNumberOfSticks() <= 0) {
            isFinished = true;
            return activePlayer;
        }

        // Make the game (a little) more fair
        /*if (round <= 1) {
            passToNextPlayer = true;
        }*/

        // Check if the player (out of preference) wants to pass to the next
        if (!passToNextPlayer && activePlayer.doesPass(playfield.getOccupiedFields())) {
            passToNextPlayer = true;
        }

        // Check if we want to pass it on
        if (passToNextPlayer) {
            players.add(activePlayer);

            round++;
            numberOfDices = 0;

            activePlayer = players.poll();
        }

        // Check if active player does not have any sticks
        if (activePlayer.getNumberOfSticks() <= 0) {
            isFinished = true;
            return activePlayer;
        }

        // Otherwise return null
        return null;
    }


}
