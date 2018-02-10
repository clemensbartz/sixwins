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

package de.clemens.games.sixwins.singletons;

import java.util.Random;

/**
 * This class is used to generate pseudo-random numbers. You
 * can set the seed before doing anything to generate the same
 * results.
 * @author Clemens Bartz
 */
public final class RandomNumberGenerator {

    /** The instance. */
    private static RandomNumberGenerator instance;

    /** The internal RNG. */
    private final Random random;

    /**
     * Get the current RNG.
     * @return the RNG
     */
    public static RandomNumberGenerator getInstance() {
        if (instance == null) {
            instance = new RandomNumberGenerator();
        }

        return instance;
    }

    /**
     * Create a new RNG.
     */
    private RandomNumberGenerator() {
        random = new Random();
    }

    /**
     * Set the seed value.
     * @param seed the new seed value
     */
    public void setSeed(final Long seed) {
        random.setSeed(seed);
    }

    /**
     * Get the next int.
     * @param bound the exclusive max.
     * @return a number between 0 (inclusive) and <code>bound</code> (exclusive)
     */
    public int nextInt(final int bound) {
        return random.nextInt(bound);
    }
}
