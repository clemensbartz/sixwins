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
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EmptyStackException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class for {@link Player}.
 * @author Clemens Bartz
 */
@DisplayName("Test class Player")
@TestMethodOrder(MethodOrderer.Random.class)
class PlayerTest {

    /**
     * Check getting id.
     */
    @DisplayName("Check IDs match")
    @ParameterizedTest
    @ValueSource(ints = {-100, -10, 0, 1, 10, 73})
    void test0001(final int value) {
        final Player player = new Player(value, ERiskAttitudes.NEUTRAL);

        assertEquals(value, player.getId(), "id does not match");
    }

    /**
     * Check number of sticks after creation.
     */
    @DisplayName("Check initial number of sticks")
    @Test
    void test0002() {
        final Player player = new Player(0, ERiskAttitudes.NEUTRAL);

        assertEquals(0, player.getNumberOfSticks(), "number of sticks does not match");

        final EmptyStackException exception = assertThrows(EmptyStackException.class, player::takeStick, "exception not thrown");
    }

    /**
     * Give the player a stick, then check if it was taken.
     */
    @DisplayName("Check saving and retrieving a stick")
    @Test
    void test0003() {
        final Player player = new Player(0, ERiskAttitudes.NEUTRAL);

        final Stick originalStick = new Stick();

        player.addStick(originalStick);

        final Stick takenStick = player.takeStick();

        assertEquals(originalStick, takenStick, "Sticks do not match");
    }

    /**
     * Give the player any number of sticks, and check if all of them can be retrieved.
     */
    @DisplayName("Check varying number of sticks")
    @TestFactory
    Stream<DynamicTest> test0004() {
        return IntStream.range(0, 100).mapToObj(
                value -> DynamicTest.dynamicTest("Check " + value + " sticks", () -> {
                    final Player player = new Player(0, ERiskAttitudes.AVERSE);

                    IntStream.range(0, value).forEach(v -> player.addStick(new Stick()));
                    IntStream.range(0, value).forEach(v -> assertDoesNotThrow(player::takeStick, "no stack although there should be one"));

                    // Check for non remaining sticks
                    assertEquals(0, player.getNumberOfSticks(), "number of sticks does not match");
                    assertThrows(EmptyStackException.class, player::takeStick, "still a stick in the stack");
        }));
    }

    /**
     * Check the pass percentage at the critical levels.
     */
    @DisplayName("Check the pass percentage")
    @Test
    void test0005() {
        final double fraction = 1e-8;
        // Set up the players
        final Player aversePlayer = new Player(0, ERiskAttitudes.AVERSE);
        final Player neutralPlayer = new Player(0, ERiskAttitudes.NEUTRAL);
        final Player seekingPlayer = new Player(0, ERiskAttitudes.SEEKING);

        // Set up the percentages
        final double passingPercentage = ((double) Playfield.NUMBER_OF_FIELDS / (double) 2);
        final int highThreshold = BigDecimal.valueOf(passingPercentage + fraction).setScale(0, RoundingMode.UP).intValue();
        final int lowThreshold = BigDecimal.valueOf(passingPercentage).setScale(0, RoundingMode.DOWN).intValue();
        // If the number of fields is even, the neutral player is different. Otherwise, the neutral player is averse.
        final int neutralThreshold = (Playfield.NUMBER_OF_FIELDS % 2 == 0) ? Playfield.NUMBER_OF_FIELDS % 2 : lowThreshold;

        // Fill the playing field with low threshold
        final Playfield playfieldLow = new Playfield();
        IntStream.rangeClosed(1, lowThreshold).forEach(i -> playfieldLow.push(i, new Stick()));

        assertTrue(aversePlayer.doesPass(playfieldLow.getOccupiedFields()), "averse player did pass");
        if (lowThreshold == neutralThreshold) { // Check for differnt version
            assertTrue(neutralPlayer.doesPass(playfieldLow.getOccupiedFields()), "neutral player did pass");
        } else {
            assertFalse(neutralPlayer.doesPass(playfieldLow.getOccupiedFields()), "neutral player did not pass");
        }
        assertFalse(seekingPlayer.doesPass(playfieldLow.getOccupiedFields()), "loving player did pass");

        // Fill the playing field with the neutral threshold
        final Playfield playfieldNeutral = new Playfield();
        IntStream.rangeClosed(1, neutralThreshold).forEach(i -> playfieldNeutral.push(i, new Stick()));

        assertTrue(aversePlayer.doesPass(playfieldNeutral.getOccupiedFields()), "averse player did not pass");
        assertTrue(neutralPlayer.doesPass(playfieldLow.getOccupiedFields()), "neutral player did not pass");
        assertFalse(seekingPlayer.doesPass(playfieldNeutral.getOccupiedFields()), "loving player did pass");

        // Fill the playing field with high threshold
        final Playfield playfieldHigh = new Playfield();
        IntStream.rangeClosed(1, highThreshold).forEach(i -> playfieldHigh.push(i, new Stick()));

        assertTrue(aversePlayer.doesPass(playfieldHigh.getOccupiedFields()), "averse player did not pass");
        assertTrue(neutralPlayer.doesPass(playfieldHigh.getOccupiedFields()), "neutral player did not pass");
        assertTrue(seekingPlayer.doesPass(playfieldHigh.getOccupiedFields()), "loving player did not pass");
    }


}