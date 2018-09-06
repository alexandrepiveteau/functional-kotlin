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

import com.github.alexandrepiveteau.functional.monads.Maybe
import com.github.alexandrepiveteau.functional.monads.toMaybe

/**
 * Returns the first element of a [List] in a [Maybe], or, if the [List] is empty, returns an empty
 * instance of [Maybe].
 */
fun <T> List<T>.head(): Maybe<T> = firstOrNull().toMaybe()

/**
 * Returns the [List], without the first element. If the [List] is empty, it will still return an
 * empty [List].
 */
fun <T> List<T>.tail(): List<T> = drop(1)

/**
 * Decomposes the [List] into its [head] component.
 */
operator fun <T> List<T>.component1(): Maybe<T> = head()

/**
 * Decomposes the [List] into its [tail] component.
 */
operator fun <T> List<T>.component2(): List<T> = tail()