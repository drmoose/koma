package koma.ndarray.default

import koma.ndarray.*

class DefaultIntNDArray(vararg shape: Int, init: (IntArray)->Int)
    : DefaultNDArray<Int>(*shape, init=init), NumericalNDArray<Int> {
    override fun copy(): NDArray<Int> = DefaultIntNDArray(*shape, init={this.get(*it)})
    
    override fun div(other: Int): NumericalNDArray<Int>
            = this.map { it/other }.toNumerical()
    override fun times(other: NDArray<Int>): NumericalNDArray<Int>
            = this.mapIndexed{ idx, ele -> other[idx]*ele}.toNumerical()
    override fun times(other: Int): NumericalNDArray<Int>
            = this.map { other*it }.toNumerical()
    override fun unaryMinus(): NumericalNDArray<Int>
            = this.map { -1*it }.toNumerical()
    override fun minus(other: Int): NumericalNDArray<Int>
            = this.map { it - other }.toNumerical()
    override fun minus(other: NDArray<Int>): NumericalNDArray<Int>
            = this.mapIndexedN { idx, ele -> ele - other.get(*idx)}.toNumerical()
    override fun plus(other: Int): NumericalNDArray<Int>
            = this.map { it + other }.toNumerical()
    override fun plus(other: NDArray<Int>): NumericalNDArray<Int>
            = this.mapIndexedN { idx, ele -> ele+other.get(*idx) }.toNumerical()
    override fun pow(exponent: Int): NumericalNDArray<Int>
            = this.map {koma.pow(it.toDouble(), exponent.toDouble()).toInt()}.toNumerical()
}

/**
 * Converts a regular [NDArray] with the same primitive type to
 * a NumericalNDArray, attempting to avoid a copy when possible
 */
fun NDArray<Int>.toNumerical(): NumericalNDArray<Int> 
        = when(this) {
    is NumericalNDArray<Int> -> { this }
    else -> { DefaultIntNDArray(*shape().toIntArray(), init={this.get(*it)}) }
}