package koma.ndarray.default

import koma.ndarray.*

class DefaultLongNDArray(vararg shape: Int, init: (IntArray)->Long)
    : DefaultNDArray<Long>(*shape, init=init), NumericalNDArray<Long> {
    override fun copy(): NDArray<Long> = DefaultLongNDArray(*shape, init={this.get(*it)})
    
    override fun div(other: Long): NumericalNDArray<Long>
            = this.map { it/other }.toNumerical()
    override fun times(other: NDArray<Long>): NumericalNDArray<Long>
            = this.mapIndexed{ idx, ele -> other[idx]*ele}.toNumerical()
    override fun times(other: Long): NumericalNDArray<Long>
            = this.map { other*it }.toNumerical()
    override fun unaryMinus(): NumericalNDArray<Long>
            = this.map { -1*it }.toNumerical()
    override fun minus(other: Long): NumericalNDArray<Long>
            = this.map { it - other }.toNumerical()
    override fun minus(other: NDArray<Long>): NumericalNDArray<Long>
            = this.mapIndexedN { idx, ele -> ele - other.get(*idx)}.toNumerical()
    override fun plus(other: Long): NumericalNDArray<Long>
            = this.map { it + other }.toNumerical()
    override fun plus(other: NDArray<Long>): NumericalNDArray<Long>
            = this.mapIndexedN { idx, ele -> ele+other.get(*idx) }.toNumerical()
    override fun pow(exponent: Int): NumericalNDArray<Long>
            = this.map {koma.pow(it.toDouble(), exponent.toDouble()).toLong()}.toNumerical()
}

/**
 * Converts a regular [NDArray] with the same primitive type to
 * a NumericalNDArray, attempting to avoid a copy when possible
 */
fun NDArray<Long>.toNumerical(): NumericalNDArray<Long> 
        = when(this) {
    is NumericalNDArray<Long> -> { this }
    else -> { DefaultLongNDArray(*shape().toIntArray(), init={this.get(*it)}) }
}