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

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for ComparableUtils.
 */
public class ComparableUtilsTest {

    @Test
    public void test1() {
        final Integer i = 1;
        final Integer j = 2;

        assertFalse(ComparableUtils.isGreater(i, j));
        assertFalse(ComparableUtils.isGreaterOrEqual(i, j));
    }

    @Test
    public void test2() {
        final Integer i = 2;
        final Integer j = 2;

        assertFalse(ComparableUtils.isGreater(i, j));
        assertTrue(ComparableUtils.isGreaterOrEqual(i, j));
    }
}