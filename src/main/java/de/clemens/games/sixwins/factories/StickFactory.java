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

import de.clemens.games.sixwins.entities.Stick;

import java.util.Stack;

/**
 * Factory to produce stacks of sticks.
 */
public final class StickFactory {

    /**
     * Create a new factory.
     */
    public StickFactory() {

    }

    /**
     * Produce a stack of sticks.
     * @param numberOfSticks the number of sticks in the stack
     * @return a stack
     */
    public Stack<Stick> createStack(final int numberOfSticks) {
        final Stack<Stick> sticks = new Stack<>();

        for (int i=0; i<numberOfSticks; i++) {
            final Stick stick = new Stick();

            sticks.add(stick);
        }

        return sticks;
    }
}
