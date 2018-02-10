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

package de.clemens.games.sixwins.utils;

import de.clemens.games.sixwins.singletons.RandomNumberGenerator;

/**
 * Represents the utils to roll a dice.
 * @author Clemens Bartz
 */
public final class DiceUtils {

    /** The minimum number for a dice. */
    private static final int DICEMIN = 1;

    /** The maximum number for a dice. */
    private static final int DICEMAX = 6;

    /**
     * Hidden constructor.
     */
    private DiceUtils() {

    }

    /**
     * Roll the dice.
     * @return the value of the dice
     */
    public static Integer rollDice() {
        final RandomNumberGenerator rng = RandomNumberGenerator.getInstance();
        return rng.nextInt(DICEMAX) + DICEMIN;
    }
}
