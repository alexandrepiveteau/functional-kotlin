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

fun <R> reader(f: () -> R): Reader<Unit, R> = Reader { f() }
fun <C, R> reader(f: (C) -> R): Reader<C, R> = Reader(f)
fun <R> identityReader(): Reader<R, R> = Reader { it }

fun <C, R1, R2> Reader<C, R1>.flatMap(f: (R1) -> Reader<C, R2>): Reader<C, R2> =
        Reader { c: C -> f(this@flatMap.read(c)).read(c) }

fun <C1, C2, R> Reader<C1, R>.local(f: (C2) -> C1): Reader<C2, R> =
        Reader { c2: C2 -> read(f(c2)) }

fun <C, R1, R2> Reader<C, R1>.map(f: (R1) -> R2): Reader<C, R2> =
        Reader { c: C -> f(this@map.read(c)) }

fun <C, R1, R2> Reader<C, R1>.zip2(a: Reader<C, R2>): Reader<C, Pair<R1, R2>> =
        flatMap { r1 : R1 -> a.map { r2: R2 -> r1 to r2 } }

fun <C, R1, R2, R3> Reader<C, R1>.zip3(a: Reader<C, R2>, b: Reader<C, R3>): Reader<C, Triple<R1, R2, R3>> =
        flatMap { r1 -> a.flatMap { r2 -> b.map { r3 -> Triple(r1, r2, r3) } } }