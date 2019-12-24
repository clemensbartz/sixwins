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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

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
        final long now = System.currentTimeMillis();

        final int maxSeed = 1000;
        final Integer runs = 1000;
        final Integer numberOfStickPerPlayer = 20;
        final String[] playerLabels = {"Player 1", "Player 2"};

        final SimulationFacade simulationFacade = new SimulationFacade();

        StreamSupport.stream(Arrays.spliterator(E_RISK_ATTITUDES), true).forEach(
                eRiskAttitudes -> {
                    final Map<String, Long> results = StreamSupport.longStream(LongStream.range(0, maxSeed-1).spliterator(), true).mapToObj(
                            value -> simulationFacade.simulate(value, playerLabels, runs, numberOfStickPerPlayer, eRiskAttitudes)
                    ).flatMap(Collection::stream).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                    printResult(playerLabels, results);
                }
        );
    }

    /**
     * Print the results.
     * @param playerLabels the labes for the players
     * @param winningTable the winning table
     */
    private static void printResult(final String[] playerLabels, final Map<String, Long> winningTable) {
        for (final String label : playerLabels) {
            System.out.println(label + ": " + winningTable.get(label));
        }
    }
}
