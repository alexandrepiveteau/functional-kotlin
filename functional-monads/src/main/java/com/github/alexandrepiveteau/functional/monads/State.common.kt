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

fun <T> identityState(): State<T, T> = State { it ->  it to it }

fun <T> state(f: (T) -> T): State<T, Unit> = State { f(it) to Unit }

fun <T> stateOf(always: T): State<T, Unit> = State { always to Unit }

fun <S, T1, T2> State<S, T1>.map(f: (T1) -> T2): State<S, T2> =
        State { s1 -> run(s1).let { (s2, t) -> s2 to f(t) } }

fun <S, T1, T2> State<S, T1>.flatMap(f: (T1) -> State<S, T2>): State<S, T2> =
        State { s1 -> run(s1).let { (s2, t) -> f(t).run(s2) } }

fun <S, T1, T2> State<S, T1>.zip2(a: State<S, T2>): State<S, Pair<T1, T2>> =
        flatMap { t1 -> a.map { t2 -> t1 to t2 } }

fun <S, T1, T2, T3> State<S, T1>.zip3(a: State<S, T2>, b: State<S, T3>): State<S, Triple<T1, T2, T3>> =
        flatMap { t1 -> a.flatMap { t2 -> b.map { t3 -> Triple(t1, t2, t3) } } }