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

import java.util.Random;

/**
 * A session consists of a number of {@link Game games} that are run. A session will start of at a
 * {@link Random random seed} to keep the session consistent.
 * @since 2.0
 * @author Clemens Bartz
 */
public final class Session {

    /**
     * Builder to create a session.
     */
    public static final class Builder {

        /** The risk attitudes. */
        private ERiskAttitudes[] riskAttitudes;
        /** The seed. */
        private long seed = 0;
        /** The number of stick per player. */
        private int numberOfSticksPerPlayer = 20;

        /**
         * Create a new builder.
         */
        public Builder() {

        }

        /**
         * Set the risk attitudes.
         * @param riskAttitudes the risk attitudes
         * @return this builder
         */
        public Builder withRiskAttitudes(final ERiskAttitudes[] riskAttitudes) {
            this.riskAttitudes = riskAttitudes;

            return this;
        }

        /**
         * Set the seed.
         * @param seed the seed
         * @return this builder
         */
        public Builder withSeed(final long seed) {
            this.seed = seed;

            return this;
        }

        /**
         * Set the number of sticks per player.
         * @param numberOfSticksPerPlayer the number of sticks per player
         * @return this builder
         */
        public Builder withNumberOfSticksPerPlayer(final int numberOfSticksPerPlayer) {
            this.numberOfSticksPerPlayer = numberOfSticksPerPlayer;

            return this;
        }

        /**
         *
         * @return the session
         */
        public Session build() {
            if (riskAttitudes == null) {
                throw new IllegalArgumentException("risk attitudes cannot be null");
            }

            if (seed < 0) {
                throw new IllegalArgumentException("seed cannot be null");
            }

            if (numberOfSticksPerPlayer < 1) {
                throw new IllegalArgumentException("number of sticks per player has to greater than 0");
            }

            return new Session(riskAttitudes, seed);
        }
    }

    /** The risk attitudes to be used in this session. */
    private final ERiskAttitudes[] riskAttitudes;
    /** The seed to start the random. */
    private final Random random;

    /**
     * Create a new session.
     * @param riskAttitudes the risk attitudes of the players
     * @param seed the initial seed for the random number generator
     */
    private Session(final ERiskAttitudes[] riskAttitudes, final long seed) {
        this.riskAttitudes = riskAttitudes;
        this.random = new Random(seed);
    }

    /**
     * Play a game.
     * @return the index of the person that won
     */
    public int playGame() {
        final Game game = new Game(riskAttitudes, random);

        return game.getWinner();
    }
}
