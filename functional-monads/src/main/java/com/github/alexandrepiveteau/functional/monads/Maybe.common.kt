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

fun <T> maybeOf(just: T): Maybe<T> = Maybe.Some(just)
fun <T> emptyMaybe(): Maybe<T> = Maybe.None()

fun <T1, T2> Maybe<T1>.map(f: (T1) -> T2): Maybe<T2> = when (this) {
    is Maybe.Some -> Maybe.Some(f(just))
    is Maybe.None -> Maybe.None()
}

fun <T1, T2> Maybe<T1>.flatMap(f: (T1) -> Maybe<T2>) = when (this) {
    is Maybe.Some -> f(just)
    is Maybe.None -> Maybe.None()
}

fun <T1, T2> Maybe<T1>.fold(f: (T1) -> T2, ifEmpty: () -> T2): T2 = when (this) {
    is Maybe.Some -> f(just)
    is Maybe.None -> ifEmpty()
}

fun <T> Maybe<T>.toIterable(): Iterable<T> = when (this) {
    is Maybe.Some -> setOf(just)
    is Maybe.None -> emptySet()
}

fun <T> Maybe<T>.toNullable(): T? = when (this) {
    is Maybe.Some -> just
    is Maybe.None -> null
}

fun <T> T?.toMaybe(): Maybe<T> = when {
    this != null -> Maybe.Some(this)
    else -> Maybe.None()
}

fun <T> Maybe<T>.withDefault(default: T): T = fold({it}) { default }

fun <T1, T2> Maybe<T1>.zip2(a: Maybe<T2>): Maybe<Pair<T1, T2>> =
        flatMap { t1 -> a.map { t2 -> t1 to t2 } }

fun <T1, T2, T3> Maybe<T1>.zip3(a: Maybe<T2>, b: Maybe<T3>): Maybe<Triple<T1, T2, T3>> =
        flatMap { t1 -> a.flatMap { t2 -> b.map { t3 -> Triple(t1, t2, t3) } } }