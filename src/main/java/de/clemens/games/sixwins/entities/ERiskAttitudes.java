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

/**
 * Describes different risk attributes.
 * @author Clemens Bartz
 */
public enum ERiskAttitudes {

    /** A risk averse player will pass if the risk is 0.5 or higher. */
    AVERSE,
    /** A risk neutral player will pass if the risk is above 0.5. */
    NEUTRAL,
    /** A risk seeking player will do one step above the 0.5. */
    SEEKING;
}
