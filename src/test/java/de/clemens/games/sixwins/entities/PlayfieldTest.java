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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for {@link Playfield}.
 * @author Clemens Bartz
 */
@DisplayName("Test class Playfield")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlayfieldTest {

    /** The playfield instance to test. */
    private Playfield playfield;

    // Technically, this makes no sense, but is good for learning:
    /** Create a new playfield for each test instance. */
    @BeforeEach
    void beforeEach() {
        playfield = new Playfield();
    }

    @TestFactory
    @DisplayName("Check for IllegalArgumentException in push() and pop() method")
    @Order(1)
    Stream<DynamicTest> test0001() {
        return Stream.of(-100, -5, 0, 6, 900).map(integer -> List.of(
                DynamicTest.dynamicTest("push()", () -> assertThrows(IllegalArgumentException.class, () -> playfield.push(integer, new Stick()), "did not throw for " + integer)),
                DynamicTest.dynamicTest("pop()", () -> assertThrows(IllegalArgumentException.class, () -> playfield.pop(integer), "did not throw for " + integer))
        )).flatMap(Collection::stream);
    }

    /**
     * Check that the push/pop methods does allow a <code>null</code> stick.
     */
    @DisplayName("Check that push() does allow for null stick")
    @Order(3)
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void test0003(final int value) {
        assertDoesNotThrow(() -> playfield.push(value, new Stick()), "did throw an exception");
    }

    /**
     * Check the occupation after creation.
     */
    @DisplayName("Check the occupation after creation.")
    @Order(4)
    @Test
    void test0004() {
        assertEquals(0d, playfield.getOccupationPercentage(), "occupation is not 0");
    }

    /**
     * Check to add all sticks and check that the percentage is full.
     */
    @DisplayName("Check for full playfield percentage")
    @Order(5)
    @Test
    void test0005() {
        IntStream.range(1, 6).forEach(value -> playfield.push(value, new Stick()));

        assertEquals(1d, playfield.getOccupationPercentage(), "occupation is not 1");
    }
}