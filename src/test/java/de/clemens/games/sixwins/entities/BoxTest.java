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

package de.clemens.games.sixwins.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for box.
 * @author Clemens Bartz
 * @since 2.0
 */
@DisplayName("Test class Box.")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BoxTest {

    /** The box to run the tests on. */
    private Box box;

    /**
     * Before all method execution, create a new box.
     * <br />
     * Note that this method does not need to be static, since
     * the lifecycle is set to "per-class".
     */
    @BeforeAll
    void beforeAll() {
        box = new Box();
    }

    /**
     * Add a stick to the box.
     */
    @DisplayName("Add a regular stick to the box.")
    @Test
    void test0001() {
        final Stick stick = new Stick();

        assertDoesNotThrow(() -> box.addStick(stick), "did throw an exception");
    }

    /**
     * Get Exception when adding null.
     */
    @DisplayName("Check for exception when adding null.")
    @Test
    void test0002() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> box.addStick(null), "did not throw IllegalArgumentException");

        assertNotEquals(0, exception.getMessage().trim().length(), "exception had no message");
    }

}