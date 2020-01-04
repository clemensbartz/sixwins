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
import de.clemens.games.sixwins.iterative.IterativeSimulation;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Run all simulations.
 * @author Clemens Bartz
 */
public final class Main {

    /**
     * Hidden constructor.
     */
    private Main() {
        // hidden
    }

    /**
     * Launch the application.
     * @param args the arguments
     */
    public static void main(final String[] args) {
        // Parse the command line
        final CommandLineParser commandLineParser = new DefaultParser();

        final Options options = CommandLineParserOptions.getOptions();

        final int numberOfPlayers;
        final int numberOfGames;
        final int numberOfSticksPerPlayer;
        final long maxSeed;

        try {
            final CommandLine commandLine = commandLineParser.parse(options, args);
            numberOfPlayers = Integer.parseInt(commandLine.getOptionValue(CommandLineParserOptions.CLI_OPTION_NUMBER_OF_PLAYERS));
            numberOfSticksPerPlayer = Integer.parseInt(commandLine.getOptionValue(CommandLineParserOptions.CLI_OPTION_NUMBER_OF_STICK_PER_PLAYER));
            numberOfGames = Integer.parseInt(commandLine.getOptionValue(CommandLineParserOptions.CLI_OPTION_NUMBER_OF_GAMES));
            maxSeed = Long.parseLong(commandLine.getOptionValue(CommandLineParserOptions.CLI_OPTION_MAX_SEED));
        } catch (final ParseException | NumberFormatException e) {
            System.err.println(e.getMessage());

            final HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("sixwins", options);

            System.exit(1);
            return;
        }

        System.out.println("Iterative solution:");
        printResults(new IterativeSimulation(numberOfPlayers, numberOfSticksPerPlayer, numberOfGames, maxSeed).simulate());
    }

    /**
     * Print the results of a winner Table.
     * @param winnerTable the winner Table
     */
    private static void printResults(final Map<ERiskAttitudes[], List<Integer>> winnerTable) {
        // Go through each risk attitude combination
        winnerTable.forEach((key, value1) -> {
            // Count the numbers in the list
            final Map<Integer, Long> winCount = value1.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            IntStream.range(0, key.length - 1).forEach(value -> {
                System.out.println("player #" + (value + 1) + ": " + key[value] + " " + winCount.get(value));
            });

            System.out.println("-----------------------");
        });
    }

}
