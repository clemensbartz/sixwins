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

package de.clemens.games.sixwins;

import de.clemens.games.sixwins.enums.ERiskAttitudes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Contains commonly shared code between all simulations.
 * @since 2.0
 * @author Clemens Bartz
 */
public abstract class AbstractSimulation {

    /** The number of players. */
    private final int numberOfPlayers;
    /** The number of games. */
    private final int numberOfGames;
    /** The max seed. */
    private final long maxSeed;
    /** The number of sticks per player. */
    private final int numberOfStickPerPlayer;

    /**
     * Create a new simulation.
     * @param numberOfPlayers the number of players
     * @param numberOfStickPerPlayer the number of sticks per player
     * @param numberOfGames the number of games
     * @param maxSeed the max seed
     */
    public AbstractSimulation(final int numberOfPlayers, final int numberOfStickPerPlayer, final int numberOfGames, final long maxSeed) {
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfStickPerPlayer = numberOfStickPerPlayer;
        this.numberOfGames = numberOfGames;
        this.maxSeed = maxSeed;

        if (numberOfPlayers < 2 || numberOfPlayers > 6) {
            throw new IllegalArgumentException("number of players is not within range (2…6)");
        }

        if (maxSeed < 1) {
            throw new IllegalArgumentException("max seed has to be greater than 0");
        }

        if (numberOfGames < 1) {
            throw new IllegalArgumentException("number of games has to be greater than 0");
        }

        if (numberOfStickPerPlayer < 1) {
            throw new IllegalArgumentException("number of stick per player has to be greater than 0");
        }
    }

    /**
     *
     * @return the number of players
     */
    public final int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     *
     * @return the number of games
     */
    public final int getNumberOfGames() {
        return numberOfGames;
    }

    /**
     *
     * @return the number of stick per player
     */
    public final int getNumberOfStickPerPlayer() {
        return numberOfStickPerPlayer;
    }

    /**
     *
     * @return the max seed
     */
    public final long getMaxSeed() {
        return maxSeed;
    }

    private List<ERiskAttitudes[]> extend(final ERiskAttitudes[] eRiskAttitudes, final int depth) {
        return Arrays.stream(ERiskAttitudes.values()).map(
                eRiskAttitudesValue -> {
                    final ERiskAttitudes[] extendedRiskAttitudes = Arrays.copyOf(eRiskAttitudes, eRiskAttitudes.length);
                    extendedRiskAttitudes[depth - 1] = eRiskAttitudesValue;

                    return extendedRiskAttitudes;
                }
        ).collect(Collectors.toList());
    }

    /**
     * Create the risk attitudes list based on the depth.
     * @param depth the depth
     * @return a list of combinations
     */
    private List<ERiskAttitudes[]> getRiskAttitudes(final int depth) {
        if (depth == 1) {
            return Arrays.stream(ERiskAttitudes.values()).map(
                    eRiskAttitudes -> {
                        final ERiskAttitudes[] attitudes = new ERiskAttitudes[getNumberOfPlayers()];
                        attitudes[depth - 1] = eRiskAttitudes;

                        return attitudes;
                    }
            ).collect(Collectors.toList());
        }

        return getRiskAttitudes(depth - 1).stream().map(eRiskAttitudes -> extend(eRiskAttitudes, depth)).flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * @return the risk matrix
     */
    public final ERiskAttitudes[][] getRiskMatrix() {
        return getRiskAttitudes(getNumberOfPlayers()).toArray(ERiskAttitudes[][]::new);
    }

    /**
     * Run a simulation.
     * @return a map with an array of indexes (one value per game) associated to a matrix of risk attitudes (key)
     */
    public abstract Map<ERiskAttitudes[], List<Integer>> simulate();
}
