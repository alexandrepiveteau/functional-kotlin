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
 * Returns a new instance of [Either], after running the associated block of code. If the code
 * executes and runs, the [Either] will be in its [Either.Value] state. If some exception or
 * throwable is raised, the [Either] will be in its [Either.Error] state and wrap the raised error.
 *
 * @param f The block of code that will be executed.
 *
 * @author Alexandre Piveteau
 */
fun <V> either(f: () -> V): Either<Throwable, V> =
        try {
            Either.Value(f())
        } catch (t: Throwable) {
            Either.Error(t)
        }

/**
 * Returns a new [Either] in its [Either.Error] form, that will contain the provided [element].
 *
 * @author Alexandre Piveteau
 */
fun <E, V> eitherError(element: E): Either<E, V> = Either.Error(element)

/**
 * Returns a new [Either] in its [Either.Value] form, that will contain the provided [element].
 *
 * @author Alexandre Piveteau
 */
fun <E, V> eitherValue(element: V): Either<E, V> = Either.Value(element)


/**
 * Folds an [Either] instance into an instance of [R]. Depending on the form of the [Either], the
 * appropriate [ifError] of [ifValue] function will be applied to map the content to an instance
 * of [R].
 *
 * @author Alexandre Piveteau
 */
fun <E, V, R> Either<E, V>.fold(ifError: (E) -> R, ifValue: (V) -> R): R =
        when (this) {
            is Either.Error -> ifError(error)
            is Either.Value -> ifValue(value)
        }

fun <E1, E2, V> Either.Projection.Error<E1, V>.flatMap(f: (E1) -> Either<E2, V>): Either.Projection.Error<E2, V> =
        when (either) {
            is Either.Error -> Either.Projection.Error(f(either.error))
            is Either.Value -> Either.Projection.Error(Either.Value(either.value))
        }

fun <E, V1, V2> Either.Projection.Value<E, V1>.flatMap(f: (V1) -> Either<E, V2>): Either.Projection.Value<E, V2> =
        when (either) {
            is Either.Error -> Either.Projection.Value(Either.Error(either.error))
            is Either.Value -> Either.Projection.Value(f(either.value))
        }

fun <E, V, R> Either.Projection<E, V>.fold(ifError: (E) -> R, ifValue: (V) -> R): R =
        either.fold(ifError, ifValue)

fun <E1, E2, V> Either.Projection.Error<E1, V>.map(f: (E1) -> E2): Either.Projection.Error<E2, V> =
        when (either) {
            is Either.Error -> Either.Projection.Error(Either.Error(f(either.error)))
            is Either.Value -> Either.Projection.Error(Either.Value(either.value))
        }

fun <E, V1, V2> Either.Projection.Value<E, V1>.map(f: (V1) -> V2): Either.Projection.Value<E, V2> =
        when (either) {
            is Either.Error -> Either.Projection.Value(Either.Error(either.error))
            is Either.Value -> Either.Projection.Value(Either.Value(f(either.value)))
        }

fun <E, V> Either.Projection.Error<E, V>.toIterable(): Iterable<E> =
        when (either) {
            is Either.Error -> setOf(either.error)
            is Either.Value -> emptySet()
        }

fun <E, V> Either.Projection.Value<E, V>.toIterable(): Iterable<V> =
        when (either) {
            is Either.Error -> emptySet()
            is Either.Value -> setOf(either.value)
        }

fun <E, V> Either.Projection.Error<E, V>.toMaybe(): Maybe<E> =
        when (either) {
            is Either.Error -> maybeOf(either.error)
            is Either.Value -> emptyMaybe()
        }

fun <E, V> Either.Projection.Value<E, V>.toMaybe(): Maybe<V> =
        when (either) {
            is Either.Error -> emptyMaybe()
            is Either.Value -> maybeOf(either.value)
        }

fun <E, V> Either.Projection.Error<E, V>.swap(): Either.Projection.Error<V, E> =
        when (either) {
            is Either.Error -> Either.Projection.Error(Either.Value(either.error))
            is Either.Value -> Either.Projection.Error(Either.Error(either.value))
        }

fun <E, V> Either.Projection.Value<E, V>.swap(): Either.Projection.Value<V, E> =
        when (either) {
            is Either.Error -> Either.Projection.Value(Either.Value(either.error))
            is Either.Value -> Either.Projection.Value(Either.Error(either.value))
        }

fun <E1, E2, V> Either.Projection.Error<E1, V>.zip2(a: Either.Projection.Error<E2, V>): Either.Projection.Error<Pair<E1, E2>, V> =
        flatMap { e1 -> a.map { e2 -> e1 to e2 }.either }

fun <E, V1, V2> Either.Projection.Value<E, V1>.zip2(a: Either.Projection.Value<E, V2>): Either.Projection.Value<E, Pair<V1, V2>> =
        flatMap { v1 -> a.map { v2 -> v1 to v2 }.either }

fun <E1, E2, E3, V> Either.Projection.Error<E1, V>.zip3(a: Either.Projection.Error<E2, V>, b: Either.Projection.Error<E3, V>): Either.Projection.Error<Triple<E1, E2, E3>, V> =
        flatMap { e1 -> a.flatMap { e2 -> b.map { e3 -> Triple(e1, e2, e3) }.either }.either }

fun <E, V1, V2, V3> Either.Projection.Value<E, V1>.zip3(a: Either.Projection.Value<E, V2>, b: Either.Projection.Value<E, V3>): Either.Projection.Value<E, Triple<V1, V2, V3>> =
        flatMap { v1 -> a.flatMap { v2 -> b.map { v3 -> Triple(v1, v2, v3) }.either }.either }