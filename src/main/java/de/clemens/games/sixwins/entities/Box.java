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

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the box in which the sticks are put when a 6 is diced.
 * @author Clemens Bartz
 * @since 2.0
 */
final class Box {

    /** Contains all boxed sticks. */
    private final List<Stick> sticks = new ArrayList<>();

    /**
     * Add a stick to the sticks list.
     * @param stick the new stick
     */
    public void addStick(final Stick stick) {
        if (stick == null) {
            throw new IllegalArgumentException("stick cannot be null");
        }

        sticks.add(stick);
    }
}
