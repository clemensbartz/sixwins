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

package de.clemens.games.sixwins.utils;

import de.clemens.games.sixwins.singletons.RandomNumberGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for DiceUtils.
 */
public class DiceUtilsTest {

    @Test
    public void test1() {
        final Long seed = 12371827L;

        RandomNumberGenerator.getInstance().setSeed(seed);

        final Integer diceValue = DiceUtils.rollDice();

        RandomNumberGenerator.getInstance().setSeed(seed);

        final Integer newDiceValue = DiceUtils.rollDice();

        assertEquals("Dice values are not the same", diceValue, newDiceValue);
    }
}