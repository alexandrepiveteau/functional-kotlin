/*
 * MIT License
 *
 * Copyright (c) 2018 Alexandre Piveteau
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.alexandrepiveteau.functional.monads

/**
 * A class representing an [Either] monad, that can either be in its [Error] or in its [Value] form.
 * An [Either] monad represents the result of a computation, that will be in exactly one of two
 * possible states. By convention, the first state will be the [Error] form, and the second state
 * will be the [Value] form.
 *
 * In order to perform some monadic operations, the [Either] has to be transformed to one of its
 * [Projection] instances. The monadic operators are only available on the [Projection]s.
 *
 * @param E The type of the content that will be present in the [Error] form of the monad.
 * @param V The type of the content that will be present in the [Value] form of the monad.
 *
 * @author Alexandre Piveteau
 */
@Suppress(names = ["Unused"])
sealed class Either<E, V> {

    /**
     * A class representing a [Projection] of the [Either] monad. Each [Projection] will offer its
     * own specific set and implementation of the monadic operators.
     *
     * Using a [Projection], it is possible to get back the (modified) [Either] by calling the
     * [either] method of the [Projection].
     *
     * @param E The type of the content that will be present in the [Error] form of the monad.
     * @param V The type of the content that will be present in the [Value] form of the monad.
     *
     * @author Alexandre Piveteau
     */
    sealed class Projection<E, V>(val either: Either<E, V>) {

        /**
         * Transforms this [Either.Projection] into an instance of [Projection.Error].
         *
         * @author Alexandre Piveteau
         */
        fun toError() = Error(either)

        /**
         * Transforms this [Either.Projection] into an instance of [Projection.Value].
         *
         * @author Alexandre Piveteau
         */
        fun toValue() = Value(either)

        /**
         * An instance of the [Either.Projection] representing the [Either.Projection.Error] form.
         * @param either The value of the [Either] of this [Either.Projection].
         *
         * @author Alexandre Piveteau
         */
        class Error<E, V>(either: Either<E, V>): Projection<E, V>(either)

        /**
         * An instance of the [Either.Projection] representing the [Either.Projection.Value] form.
         * @param either The value of the [Either] of this [Either.Projection].
         *
         * @author Alexandre Piveteau
         */
        class Value<E, V>(either: Either<E, V>): Projection<E, V>(either)
    }

    /**
     * Transforms this [Either] into an instance of [Projection.Error].
     *
     * @author Alexandre Piveteau
     */
    fun toError() = Projection.Error(this)

    /**
     * Transforms this [Either] into an instance of [Projection.Value].
     *
     * @author Alexandre Piveteau
     */
    fun toValue() = Projection.Value(this)

    /**
     * An instance of the [Either] that represents the [Error] form.
     * @param error The value of the error of this [Either] monad.
     *
     * @author Alexandre Piveteau
     */
    class Error<E, V>(val error: E): Either<E, V>()

    /**
     * An instance of the [Either] that represents the [Value] form.
     * @param value The value of the value of this [Either] monad.
     *
     * @author Alexandre Piveteau
     */
    class Value<E, V>(val value: V): Either<E, V>()
}