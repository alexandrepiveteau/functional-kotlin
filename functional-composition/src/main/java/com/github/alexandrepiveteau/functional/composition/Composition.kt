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

package com.github.alexandrepiveteau.functional.composition

/**
 * Composes a new function, based on two different function calls that should be applied. The operator [rangeTo] will be
 * used in order to express the composition.
 *
 * @param f The function that should be used for composition.
 *
 * @author Alexandre Piveteau
 */
operator fun <P1, R> ((P1) -> R).rangeTo(f: () -> P1): () -> R = { this(f()) }

/**
 * Composes a new function, based on two different function calls that should be applied. The operator [rangeTo] will be
 * used in order to express the composition.
 *
 * @param f The function that should be used for composition.
 *
 * @author Alexandre Piveteau
 */
operator fun <P1, P2, R> ((P2) -> R).rangeTo(f: (P1) -> P2): (P1) -> R = { this(f(it)) }

/**
 * Composes a new function, based on two different function calls that should be applied. The infix keyword determines
 * which of the functions will be executed first.
 *
 * @param f The function that should be used for composition.
 *
 * @author Alexandre Piveteau
 */
infix fun <P1, R> (() -> P1).andThen(f: (P1) -> R) = f..this

/**
 * Composes a new function, based on two different function calls that should be applied. The infix keyword determines
 * which of the functions will be executed first.
 *
 * @param f The function that should be used for composition.
 *
 * @author Alexandre Piveteau
 */
infix fun <P1, P2, R> ((P1) -> P2).andThen(f: (P2) -> R) = f..this

/**
 * Composes a new function, based on two different function calls that should be applied. The infix keyword determines
 * which of the functions will be executed first.
 *
 * @param f The function that should be used for composition.
 *
 * @author Alexandre Piveteau
 */
infix fun <P1, R> ((P1) -> R).compose(f: () -> P1) = this..f

/**
 * Composes a new function, based on two different function calls that should be applied. The infix keyword determines
 * which of the functions will be executed first.
 *
 * @param f The function that should be used for composition.
 *
 * @author Alexandre Piveteau
 */
infix fun <P1, P2, R> ((P2) -> R).compose(f: (P1) -> P2) = this..f

/**
 * Composes a new function, based on two different function calls that should be applied. The infix keyword determines
 * which of the functions will be executed first.
 *
 * @param f The function that should be used for composition.
 *
 * @author Alexandre Piveteau
 */
infix fun <P1, R> (() -> P1).forwardCompose(f: (P1) -> R) = f..this

/**
 * Composes a new function, based on two different function calls that should be applied. The infix keyword determines
 * which of the functions will be executed first.
 *
 * @param f The function that should be used for composition.
 *
 * @author Alexandre Piveteau
 */
infix fun <P1, P2, R> ((P1) -> P2).forwardCompose(f: (P2) -> R) = f..this