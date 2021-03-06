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
 * A class representing the [Reader] monad, that will contain a computation. The contained
 * computation will require a certain context instance of type [C] to be provided to the [read]
 * method to be computed.
 *
 * @param f The computation that will be run in this [Reader].
 *
 * @param C The type of the context for this [Reader] monad.
 * @param R The type of the return value of the computation of this [Reader] monad.
 *
 * @author Alexandre Piveteau
 */
class Reader<in C, out R>(private val f: (C) -> R) {

    /**
     * Reads the content of the computation, when a [context] of type [C] will have been provided.
     * Returns an instance of [R].
     */
    fun read(context: C): R = f(context)
}