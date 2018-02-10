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

package de.clemens.games.sixwins;

import de.clemens.games.sixwins.enums.ERiskAttitudes;
import de.clemens.games.sixwins.facades.SimulationFacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Console application entry point.
 * @author Clemens Bartz
 */
public final class Main {

    /**
     * Hidden constructor.
     */
    private Main() {

    }

    /** The nummber of combinations. */
    private static final ERiskAttitudes[][] E_RISK_ATTITUDES = {
            {ERiskAttitudes.RISK_AVERSE,ERiskAttitudes.RISK_AVERSE},
            {ERiskAttitudes.RISK_AVERSE,ERiskAttitudes.RISK_LOVING},
            {ERiskAttitudes.RISK_LOVING,ERiskAttitudes.RISK_AVERSE},
            {ERiskAttitudes.RISK_LOVING,ERiskAttitudes.RISK_LOVING}
    };

    /**
     * Launch a console application.
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        final Integer maxSeed = 1000;
        final Integer runs = 1000;
        final Integer numberOfStickPerPlayer = 20;
        final String[] playerLabels = {"Player 1", "Player 2"};

        final List<Map<String, Integer>> winningTables = getWinningTables(playerLabels);

        if (winningTables.size() != E_RISK_ATTITUDES.length) {
            throw new RuntimeException("Cannot store enough results");
        }

        final SimulationFacade simulationFacade = new SimulationFacade();

        // Go through all seeds
        for (int seed=0; seed<maxSeed; seed++) {

            // Perform simulation for all risk combinations
            for (int i=0; i<E_RISK_ATTITUDES.length; i++) {
                final ERiskAttitudes[] riskAttitudes = E_RISK_ATTITUDES[i];
                final Map<String, Integer> winningTable = winningTables.get(i);

                simulationFacade.simulate((long) seed, playerLabels, winningTable, runs, numberOfStickPerPlayer, riskAttitudes);
            }
        }

        for (final Map<String, Integer> winningTable : winningTables) {
            printResult(playerLabels, winningTable);
        }
    }

    /**
     * Print the results.
     * @param playerLabels the labes for the players
     * @param winningTable the winning table
     */
    private static void printResult(final String[] playerLabels, final Map<String, Integer> winningTable) {
        for (final String label : playerLabels) {
            System.out.println(label + ": " + winningTable.get(label));
        }
    }

    /**
     * Create the winning tables.
     * @param playerLabels the labels for the players
     * @return a list of the winning tables
     */
    private static List<Map<String, Integer>> getWinningTables(final String[] playerLabels) {
        final List<Map<String, Integer>> winningTables = new ArrayList<>(E_RISK_ATTITUDES.length);

        for (int i=0; i<E_RISK_ATTITUDES.length; i++) {
            final Map<String, Integer> winningTable = new HashMap<>(playerLabels.length);

            for (String label : playerLabels) {
                winningTable.put(label, 0);
            }

            winningTables.add(winningTable);
        }

        return winningTables;
    }
}
