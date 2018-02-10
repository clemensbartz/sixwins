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

import de.clemens.games.sixwins.utils.ComparableUtils;

import java.util.Stack;

/**
 * A person to play the game.
 * @author Clemens Bartz
 */
public class Player {

    /** The percentage when a player passes the dice to the next one. */
    protected final Double passPercentage;

    /** Contains the list of sticks the player has. */
    protected final Stack<Stick> sticks = new Stack<>();

    /** The label to identify the player. */
    private final String label;

    /**
     * Create a new player with a pass percentage.
     * @param label a label
     * @param passPercentage the pass percentage
     */
    public Player(final String label, final Double passPercentage) {
        this.passPercentage = passPercentage;
        this.label = label;
    }

    /**
     * Tells if the player passes at a certain percentage.
     * @param percentage the percentage to check
     * @return if the player passes
     */
    public boolean doesPass(final Double percentage) {
        if (passPercentage == null) {
            throw new RuntimeException("Pass percentage is null");
        }

        return ComparableUtils.isGreaterOrEqual(percentage, passPercentage);
    }

    /**
     *
     * @return the number of sticks currently held by the player
     */
    public int getNumberOfSticks() {
        return sticks.size();
    }

    /**
     * Add a stick to the stack.
     * @param stick the stick
     */
    public void addStick(final Stick stick) {
        sticks.push(stick);
    }

    /**
     * Add multiple sticks to the stack.
     * @param sticks the sticks
     */
    public void addSticks(final Stack<Stick> sticks) {
        this.sticks.addAll(sticks);
    }

    /**
     * Take one stick from the stack. It is also removed from the players stack.
     * @return the stick
     */
    public Stick takeStick() {
        return sticks.pop();
    }

    /**
     *
     * @return the label for the player
     */
    public final String getLabel() {
        return label;
    }
}
