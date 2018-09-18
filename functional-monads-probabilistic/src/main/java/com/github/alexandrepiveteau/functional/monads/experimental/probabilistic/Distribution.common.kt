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

package com.github.alexandrepiveteau.functional.monads.experimental.probabilistic

fun <O1, O2> Distribution<O1>.map(f: (O1) -> O2): Distribution<O2> =
        Distribution { f(this.sample()) }

fun <O1, O2> Distribution<O1>.flatMap(f: (O1) -> Distribution<O2>): Distribution<O2> =
        f(this.sample())

fun <O1, O2> Distribution<O1>.zip2(a: Distribution<O2>): Distribution<Pair<O1, O2>> =
        flatMap { o1 -> a.map { o2 -> o1 to o2 } }

fun <O1, O2, O3> Distribution<O1>.zip3(a: Distribution<O2>, b: Distribution<O3>): Distribution<Triple<O1, O2, O3>> =
        flatMap { o1 -> a.flatMap { o2 -> b.map { o3 -> Triple(o1, o2, o3) } } }

fun <O: Any> Distribution<O>.toSequence(): Sequence<O> =
        generateSequence { sample() }