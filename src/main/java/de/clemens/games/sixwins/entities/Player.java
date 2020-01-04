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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

/**
 * @author Clemens Bartz
 */
final class Player {

    /** The fraction to add. */
    private static final double FRACTION = 1e-8;

    /** The id of the player. */
    private final int id;
    /** The pass value at which the player will not continue. */
    private final int passValue;
    /** The stack of sticks. */
    private final Stack<Stick> sticks = new Stack<>();

    /**
     * Create a new player.
     * @param id the id of that player
     * @param riskAttitude the risk attitude of that player
     */
    Player(final int id, final ERiskAttitudes riskAttitude) {
        this.id = id;

        final double halfNumberOfFields = (double) Playfield.NUMBER_OF_FIELDS / (double) 2;

        switch (riskAttitude) {
            case AVERSE:
                passValue = round(halfNumberOfFields, RoundingMode.DOWN);
                break;
            case NEUTRAL:
                if (Playfield.NUMBER_OF_FIELDS % 2 == 0) {
                    passValue = Playfield.NUMBER_OF_FIELDS / 2;
                } else {
                    passValue = round(halfNumberOfFields, RoundingMode.DOWN);
                }
                break;
            case SEEKING:
                passValue = round(halfNumberOfFields + FRACTION, RoundingMode.UP);
                break;
            default:
                throw new IllegalArgumentException("unsupported risk attitude");
        }
    }

    /**
     * Round a double.
     * @param d the double
     * @param roundingMode the mode to round
     * @return an int
     */
    private int round(final double d, final RoundingMode roundingMode) {
        return BigDecimal.valueOf(d).setScale(0, roundingMode).intValue();
    }

    /**
     * Tells if the player passes at a certain number of occupied fields.
     * @param occupiedFields the number of occupied fields
     * @return if the player passes
     */
    boolean doesPass(final int occupiedFields) {
        return occupiedFields >= passValue;
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
