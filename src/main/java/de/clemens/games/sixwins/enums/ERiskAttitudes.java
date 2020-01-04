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

package de.clemens.games.sixwins.enums;

/**
 * Describes different risk attributes.
 * @author Clemens Bartz
 */
public enum ERiskAttitudes {

    /** Will pass at 0.5. */
    RISK_AVERSE(0.5),
    /** Will pass at 0.5 + 1e-8. */
    RISK_LOVING(0.5 + 1e-8);

    /** The risk percentage. */
    private final Double passPercentage;

    /**
     * Create a new risk attitude with a certain pass percentage.
     * @param passPercentage the percentage to pass
     */
    ERiskAttitudes(final Double passPercentage) {
        this.passPercentage = passPercentage;
    }

    /**
     *
     * @return the risk percentage
     */
    public Double getPassPercentage() {
        return passPercentage;
    }
}
