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
 * A class representing the [Maybe] monad, that can either be in its empty or just form. This monad
 * represents some element that can either be present or not, and offers the different monadic
 * operations on top of it.
 *
 * @param T The type of the [Maybe] monad if it is in its just state.
 *
 * @author Alexandre Piveteau
 */
sealed class Maybe<out T> {

    /**
     * An instance of the [Maybe] that will contain a [just] element. This will be the one
     * projection where the element is actually contained.
     *
     * @param just The element that is wrapped in this [Maybe].
     *
     * @author Alexandre Piveteau
     */
    class Some<out T>(val just: T): Maybe<T>()

    /**
     * An instance of the [Maybe] that will not contain anything. This projection will usually not
     * have any alterations when some methods are applied to it.
     *
     * @author Alexandre Piveteau
     */
    class None<out T>: Maybe<T>()
}