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

package com.github.alexandrepiveteau.functional.collections

fun <A, B> Pair<A, B>.toFirst(): PairProjection.First<A, B> = PairProjection.First(this)
fun <A, B> Pair<A, B>.toSecond(): PairProjection.Second<A, B> = PairProjection.Second(this)

fun <A1, A2, B> PairProjection.First<A1, B>.map(f: (A1) -> A2): PairProjection.First<A2, B> =
        PairProjection.First(f(this.pair.first) to this.pair.second)

fun <A, B1, B2> PairProjection.Second<A, B1>.map(f: (B1) -> B2): PairProjection.Second<A, B2> =
        PairProjection.Second(this.pair.first to f(this.pair.second))