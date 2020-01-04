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

package de.clemens.games.sixwins.iterative;

import de.clemens.games.sixwins.AbstractSimulation;
import de.clemens.games.sixwins.entities.Session;
import de.clemens.games.sixwins.entities.ERiskAttitudes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Running the simulation for interactive components.
 * @since 2.0
 * @author Clemens Bartz
 */
public final class IterativeSimulation extends AbstractSimulation {

    /**
     * Create a interactive new simulation.
     *
     * @param numberOfPlayers the number of players
     * @param numberOfStickPerPlayer the number of sticks per player
     * @param numberOfGames   the number of games
     * @param maxSeed         the max seed
     */
    public IterativeSimulation(final int numberOfPlayers, final int numberOfStickPerPlayer, final int numberOfGames, final long maxSeed) {
        super(numberOfPlayers, numberOfStickPerPlayer, numberOfGames, maxSeed);
    }

    @Override
    public Map<ERiskAttitudes[], List<Integer>> simulate() {
        final ERiskAttitudes[][] riskMatrix = getRiskMatrix();
        final Map<ERiskAttitudes[], List<Integer>> winningTable = new HashMap<>(riskMatrix.length);

        // Go through each risk matrix
        for (ERiskAttitudes[] eRiskAttitudes : riskMatrix) {
            final List<Integer> winnerIndexes = new Stack<>();

            // Go through all the seeds
            for (long seed = 0; seed < getMaxSeed(); seed++) {
                // Create a session
                final Session session = new Session.Builder().withRiskAttitudes(eRiskAttitudes).withSeed(seed).withNumberOfSticksPerPlayer(getNumberOfStickPerPlayer()).build();
                // Play all games
                for (int game = 0; game < getNumberOfGames(); game++) {
                    winnerIndexes.add(session.playGame());
                }
            }

            winningTable.put(eRiskAttitudes, winnerIndexes);
        }

        return winningTable;
    }
}
