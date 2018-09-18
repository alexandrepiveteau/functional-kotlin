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

import kotlin.math.abs


class Distribution<out O>(private val sample: () -> O) {

    fun sample(): O = sample.invoke()

    companion object {

        fun bernoulli(positive: Double = 0.5, negative: Double = 0.5): Distribution<Boolean> =
                Distribution {
                    val absPositive = abs(positive)
                    val absNegative = abs(negative)
                    return@Distribution uniform().map { it * (absPositive + absNegative) }.sample() < absPositive
                }

        /**
         * Check Wikipedia for information on the Box-Muller transform.
         */
        fun gaussian(): Distribution<Double> =
                Distribution {
                    Math.sqrt(-2.0 * Math.log(uniform().sample())) * Math.sin(2.0 * Math.PI * uniform().sample())
                }

        fun geometric(positive: Double = 0.5, negative: Double = 0.5): Distribution<Int> = bernoulli(positive, negative)
                .flatMap { b ->
                    return@flatMap when (b) {
                        true -> always(1)
                        false -> lazy { geometric().map { it + 1 } }
                    }
                }

        fun uniform(): Distribution<Double> =
                Distribution { Math.random() }

        fun <O> always(element: O): Distribution<O> =
                Distribution { element }

        fun <O> lazy(f: () -> Distribution<O>): Distribution<O> =
                Distribution { f().sample() }
    }
}