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

package de.clemens.games.sixwins.entities;

import de.clemens.games.sixwins.factories.StickFactory;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for Player.
 * @author Clemens Bartz
 */
public class PlayerTest {

    @Test
    public void test1() {
        final String label = "The Magic Words are Squeamish Ossifrage";
        final Double percentage = Math.random();
        final int numberOfSticks = 20;

        final Player player = new Player(label, percentage);

        assertEquals("Label does not match", label, player.getLabel());
        assertTrue("Percentage does not pass", player.doesPass(percentage));

        final Stack<Stick> sticks = new StickFactory().createStack(numberOfSticks);
        player.addSticks(sticks);

        for (int i=player.getNumberOfSticks(); i>0; i--) {
            final Stick stick = player.takeStick();

            assertTrue("Stick was previously not added", sticks.contains(stick));
            sticks.remove(stick);
        }

        assertEquals("Player does still have sticks", 0, player.getNumberOfSticks());
    }
}