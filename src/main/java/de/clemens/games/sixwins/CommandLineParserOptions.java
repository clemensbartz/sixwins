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

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * A parser for the command line arguments shared across different implementations.
 * @since 2.0
 * @author Clemens Bartz
 */
public final class CommandLineParserOptions {

    /** The command line argument for number of games. */
    public static String CLI_OPTION_NUMBER_OF_GAMES = "numberOfGames";
    /** The command line argument for number of players. */
    public static String CLI_OPTION_NUMBER_OF_PLAYERS = "numberOfPlayers";
    /** The command line argument for max seed. */
    public static String CLI_OPTION_MAX_SEED = "maxSeed";
    /** The command line argument for number of sticks per player.*/
    public static String CLI_OPTION_NUMBER_OF_STICK_PER_PLAYER = "numberOfStickPerPlayer";

    /**
     * Hidden constructor.
     */
    private CommandLineParserOptions() {
        // nothing to do
    }

    /**
     *
     * @return the options for command line interaction
     */
    public static Options getOptions() {
        final Options options = new Options();

        final Option numberOfPlayersOption = new Option("p", CLI_OPTION_NUMBER_OF_PLAYERS, true, "number of players");
        numberOfPlayersOption.setRequired(true);
        options.addOption(numberOfPlayersOption);

        final Option numberOfStickPerPlayerOption = new Option("n", CLI_OPTION_NUMBER_OF_STICK_PER_PLAYER, true , "number of sticks per player");
        numberOfStickPerPlayerOption.setRequired(true);
        options.addOption(numberOfStickPerPlayerOption);

        final Option numberOfGamesOption = new Option("g", CLI_OPTION_NUMBER_OF_GAMES, true, "number of games");
        numberOfGamesOption.setRequired(true);
        options.addOption(numberOfGamesOption);

        final Option maxSeedOption = new Option("s", CLI_OPTION_MAX_SEED, true, "max seed");
        maxSeedOption.setRequired(true);
        options.addOption(maxSeedOption);

        return options;
    }

}
