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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Session}.
 * @author Clemens Bartz
 */
@DisplayName("Test class Session.")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SessionTest {

    /**
     * Test empty builder.
     */
    @DisplayName("Test empty builder")
    @Test
    @Order(1)
    void test0001() {
        final Session.Builder builder = new Session.Builder();

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, builder::build, "did not throw an exception");
        assertFalse(exception.getMessage().isEmpty());
    }

    /**
     * Test empty risk attitudes.
     */
    @DisplayName("Test empty risk attitudes")
    @Test
    @Order(2)
    void test0002() {
        final Session.Builder builder = new Session.Builder().withNumberOfSticksPerPlayer(50).withSeed(50);

        assertThrows(IllegalArgumentException.class, builder::build, "did not throw an exception");
    }

    /**
     * Test empty number of sticks per player.
     */
    @DisplayName("Test empty number of sticks per player")
    @Test
    @Order(3)
    void test0003() {
        final Session.Builder builder = new Session.Builder().withSeed(50).withRiskAttitudes(ERiskAttitudes.values());

        assertDoesNotThrow(builder::build, "did throw an exception");
    }

    /**
     * Test empty seed.
     */
    @DisplayName("Test empty seed")
    @Test
    @Order(4)
    void test0004() {
        final Session.Builder builder = new Session.Builder().withNumberOfSticksPerPlayer(50).withRiskAttitudes(ERiskAttitudes.values());

        assertDoesNotThrow(builder::build, "did throw an exception");
    }

    /**
     * Test invalid seed.
     */
    @DisplayName("Test invalid seed")
    @Test
    @Order(5)
    void test0005() {
        final Session.Builder builder = new Session.Builder().withSeed(-1);

        assertThrows(IllegalArgumentException.class, builder::build, "did not throw an exception");
    }

    /**
     * Test invalid number of sticks per player.
     */
    @DisplayName("Test invalid number of sticks per player")
    @Test
    @Order(6)
    void test0006() {
        final Session.Builder builder = new Session.Builder().withNumberOfSticksPerPlayer(0);

        assertThrows(IllegalArgumentException.class, builder::build, "did not throw an exception");
    }

}