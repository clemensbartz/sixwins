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

package de.clemens.games.sixwins.facades;

import de.clemens.games.sixwins.entities.Game;
import de.clemens.games.sixwins.entities.Player;
import de.clemens.games.sixwins.enums.ERiskAttitudes;
import de.clemens.games.sixwins.factories.PlayerFactory;
import de.clemens.games.sixwins.factories.StickFactory;
import de.clemens.games.sixwins.singletons.RandomNumberGenerator;
import de.clemens.games.sixwins.utils.DiceUtils;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * This class is used to simulate a game.
 * @author Clemens Bartz
 */
public class SimulationFacade {

    /**
     * Simulate a run.
     * @param seed the current seed
     * @param playerLabels the labes for the players
     * @param winningTable the table to store the winning
     * @param runs how many runs to run
     * @param numberOfStickPerPlayer how many sticks each player should get
     * @param riskAttitudes the attitudes
     */
    public void simulate(final Long seed, final String[] playerLabels, final Map<String, Integer> winningTable, final Integer runs, final Integer numberOfStickPerPlayer, final ERiskAttitudes[] riskAttitudes) {
        RandomNumberGenerator.getInstance().setSeed(seed);

        for (int i=0; i<runs; i++) {
            final String playerName = playGame(playerLabels, numberOfStickPerPlayer, riskAttitudes);

            final int wins = winningTable.get(playerName) + 1;
            winningTable.put(playerName, wins);
        }
    }

    /**
     * Play a game.
     * @param playerLabels the labels for the players
     * @param numberOfStickPerPlayer the number of sticks per player
     * @param riskAttitudes the risk attitudes
     * @return the winning player
     */
    private String playGame(final String[] playerLabels, final Integer numberOfStickPerPlayer, final ERiskAttitudes[] riskAttitudes) {
        if (riskAttitudes.length != playerLabels.length) {
            throw new IllegalArgumentException("Number of risk attitudes and players does not match.");
        }

        final PlayerFactory playerFactory = new PlayerFactory();
        final StickFactory stickFactory = new StickFactory();

        // Create a queue for two players
        final Queue<Player> players = new ArrayBlockingQueue<>(playerLabels.length);
        for (int i=0; i<playerLabels.length; i++) {
            final String label = playerLabels[i];
            final ERiskAttitudes attitude = riskAttitudes[i];

            players.add(playerFactory.createPlayer(label, attitude));
        }

        // Add sticks to the player
        for (Player player : players) {
            player.addSticks(stickFactory.createStack(numberOfStickPerPlayer));
        }

        // Create a new game
        final Game game = new Game(players);

        Player winningPlayer;
        do {
            final int diceValue = DiceUtils.rollDice();

            winningPlayer = game.advance(diceValue);
        } while (winningPlayer == null);

        return winningPlayer.getLabel();
    }
}
