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

/**
 * @author Clemens Bartz
 */
final class Playfield {

    /** The number of fields in the game. */
    private static final Integer NUMBER_OF_FIELDS = 5;

    /** The array to hold all the sticks. */
    private final Stick[] sticks = new Stick[NUMBER_OF_FIELDS];

    /**
     * Pop the stick for a dice value.
     * @param pos the dice value
     * @return the stick or <code>null</code>, if no stick was present
     */
    public Stick pop(final int pos) {
        if (pos < 1 || pos > NUMBER_OF_FIELDS) {
            throw new IllegalArgumentException("There are only " + Integer.toString(NUMBER_OF_FIELDS) + " on the field.");
        }

        final int stickNumber = pos - 1;

        if (sticks[stickNumber] != null) {
            final Stick stick = sticks[stickNumber];
            sticks[stickNumber] = null;

            return stick;
        } else {
            return null;
        }
    }

    /**
     * Push a stick for the dice value.
     * @param pos the dice value
     * @param stick the stick to push
     */
    public void push(final int pos, final Stick stick) {
        if (pos < 1 || pos > NUMBER_OF_FIELDS) {
            throw new IllegalArgumentException("There are only " + Integer.toString(NUMBER_OF_FIELDS) + " on the field.");
        }

        final int stickNumber = pos - 1;

        if (sticks[stickNumber] != null) {
            throw new RuntimeException("This spot is already occupied.");
        }

        sticks[stickNumber] = stick;
    }

    /**
     *
     * @return the percentage how many fields are occupied
     */
    public Double getOccupationPercentage() {
        int occupiedFields = 0;

        for (Stick stick : sticks) {
            if (stick != null) {
                occupiedFields++;
            }
        }

        return (double) occupiedFields / (NUMBER_OF_FIELDS.doubleValue());
    }
}
