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
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for PlayerFactory.
 * @author Clemens Bartz
 */
public class PlayerFactoryTest {

    @Test
    public void test1() {
        final PlayerFactory playerFactory = new PlayerFactory();

        final String label = "The Magic Words are Squeamish Ossifrage";
        final ERiskAttitudes attitude = ERiskAttitudes.RISK_AVERSE;

        final Player player = playerFactory.createPlayer(label, attitude);

        assertEquals("Label does not match", label, player.getLabel());
        assertTrue("Risk profile does not match", player.doesPass(attitude.getPassPercentage()));
    }

    @Test
    public void test2() {
        final PlayerFactory playerFactory = new PlayerFactory();

        final String label = "The Magic Words are Squeamish Ossifrage";
        final ERiskAttitudes attitude = ERiskAttitudes.RISK_LOVING;

        final Player player = playerFactory.createPlayer(label, attitude);

        assertEquals("Label does not match", label, player.getLabel());
        assertTrue("Risk profile does not match", player.doesPass(attitude.getPassPercentage()));
    }
}