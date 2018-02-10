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

/**
 * Class for utility functions for comparing.
 * @author Clemens Bartz
 */
public final class ComparableUtils {

    /**
     * Hidden constructor.
     */
    private ComparableUtils() {
    }

    /**
     * Check if the first number is greater then the second.
     * @param <S> the to be compared
     * @param <T> a class to extend {@link Comparable}
     * @param first first comparable
     * @param second second comparable
     * @return if the first is greater than the second
     */
    public static <S, T extends Comparable<S>> boolean isGreater(final T first, final S second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Only non-null values can be compared");
        }

        return first.compareTo(second) > 0;
    }

    /**
     * Check if the first number is greater or equal then the second.
     * @param <S> the to be compared
     * @param <T> a class to extend {@link Comparable}
     * @param first first comparable
     * @param second second comparable
     * @return if the first is greater or equal than the second
     */
    public static <S, T extends Comparable<S>> boolean isGreaterOrEqual(final T first, final S second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Only non-null values can be compared");
        }

        return first.compareTo(second) >= 0;
    }
}
