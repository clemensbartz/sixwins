/*
 *     Six Wins
 *     Copyright (C) 2018  Clemens Bartz
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.clemens.games.sixwins.entities;

import java.util.Queue;

/**
 * Represents a full game.
 * This game can be advanced with rolling a dice.
 * @author Clemens Bartz
 */
public final class Game {

    /** The minimum number of players. */
    private static final int PLAYERNUMMIN = 2;

    /** The maximum value for a dice. */
    private static final int DICEMAX = 6;

    /** The minimum value for a dice. */
    private static final int DICEMIN = 1;

    /** The dice value when a stick should go to the box. */
    private static final int BOXVALUE = 6;

    /** The round we are in. */
    private Integer round = 1;

    /** The number of dices for a player. */
    private Integer numberOfDices = 0;

    /** The box to hold the sticks when a six was diced. */
    private final Box box;

    /** The playfield. */
    private final Playfield playfield;

    /** The list of players. */
    private final Queue<Player> players;

    /** The currently active player. */
    private Player activePlayer;

    /** Game is finished. */
    private boolean isFinished = false;

    /**
     * Create a new game.
     * @param players the players to play the game (the first player will start)
     */
    public Game(final Queue<Player> players) {
        if (players == null || players.size() < PLAYERNUMMIN) {
            throw new IllegalArgumentException("There need to be at least " + Integer.toString(PLAYERNUMMIN) + " players to play the game.");
        }

        this.box = new Box();
        this.playfield = new Playfield();
        this.activePlayer = players.poll();
        this.players = players;
    }

    /**
     * Advance the game.
     * @param diceValue the value of the dice.
     * @return the player who has won, or <code>null</code> if the game can advance
     */
    public Player advance(final int diceValue) {
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

        final Double occupationPercentage = playfield.getOccupationPercentage();

        // Make the game (a little) more fair
        /*if (round <= 1) {
            passToNextPlayer = true;
        }*/

        // Check if the player (out of preference) wants to pass to the next
        if (!passToNextPlayer && activePlayer.doesPass(occupationPercentage)) {
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

    /**
     *
     * @return the round we are currently in
     */
    public Integer getRound() {
        return round;
    }

    /**
     *
     * @return the number of dices that have been thrown this round
     */
    public Integer getNumberOfDices() {
        return numberOfDices;
    }
}
