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

import de.clemens.games.sixwins.enums.ERiskAttitudes;

import java.util.Stack;

/**
 * @author Clemens Bartz
 */
final class Player {

    /** The id of the player. */
    private final int id;
    /** The risk attitude of that player. */
    private final ERiskAttitudes riskAttitude;
    /** The stack of sticks. */
    private final Stack<Stick> sticks = new Stack<>();

    /**
     * Create a new player.
     * @param id the id of that player
     * @param riskAttitude the risk attitude of that player
     */
    Player(final int id, final ERiskAttitudes riskAttitude) {
        this.id = id;
        this.riskAttitude = riskAttitude;
    }

    /**
     * Tells if the player passes at a certain percentage.
     * @param percentage the percentage to check
     * @return if the player passes
     */
    boolean doesPass(final Double percentage) {
        return percentage.compareTo(riskAttitude.getPassPercentage()) >= 0;
    }

    /**
     *
     * @return the number of sticks currently held by the player
     */
    int getNumberOfSticks() {
        return sticks.size();
    }

    /**
     * Add a stick to the stack.
     * @param stick the stick
     */
    void addStick(final Stick stick) {
        sticks.push(stick);
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
     * @return the id of the player
     */
    public int getId() {
        return id;
    }
}
