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

package de.clemens.games.sixwins.factories;

import de.clemens.games.sixwins.entities.Player;
import de.clemens.games.sixwins.enums.ERiskAttitudes;

/**
 * Factory to produce players.
 * @author Clemens Bartz
 */
public final class PlayerFactory {

    /**
     * Create a new player factory.
     */
    public PlayerFactory() {

    }

    /**
     * Create a player with a risk attitude.
     * @param label the label
     * @param riskAttitudes the risk attitude
     * @return a player
     */
    public Player createPlayer(final String label, final ERiskAttitudes riskAttitudes) {
        return new Player(label, riskAttitudes.getPassPercentage());
    }
}
