package koma.ndarray.default

import koma.ndarray.*

class DefaultDoubleNDArray(vararg shape: Int, init: (IntArray)->Double)
    : DefaultNDArray<Double>(*shape, init=init), NumericalNDArray<Double> {
    override fun copy(): NDArray<Double> = DefaultDoubleNDArray(*shape, init={this.get(*it)})
    
    override fun div(other: Double): NumericalNDArray<Double>
            = this.map { it/other }.toNumerical()
    override fun times(other: NDArray<Double>): NumericalNDArray<Double>
            = this.mapIndexed{ idx, ele -> other[idx]*ele}.toNumerical()
    override fun times(other: Double): NumericalNDArray<Double>
            = this.map { other*it }.toNumerical()
    override fun unaryMinus(): NumericalNDArray<Double>
            = this.map { -1*it }.toNumerical()
    override fun minus(other: Double): NumericalNDArray<Double>
            = this.map { it - other }.toNumerical()
    override fun minus(other: NDArray<Double>): NumericalNDArray<Double>
            = this.mapIndexedN { idx, ele -> ele - other.get(*idx)}.toNumerical()
    override fun plus(other: Double): NumericalNDArray<Double>
            = this.map { it + other }.toNumerical()
    override fun plus(other: NDArray<Double>): NumericalNDArray<Double>
            = this.mapIndexedN { idx, ele -> ele+other.get(*idx) }.toNumerical()
    override fun pow(exponent: Int): NumericalNDArray<Double>
            = this.map {koma.pow(it.toDouble(), exponent.toDouble()).toDouble()}.toNumerical()
}

/**
 * Converts a regular [NDArray] with the same primitive type to
 * a NumericalNDArray, attempting to avoid a copy when possible
 */
fun NDArray<Double>.toNumerical(): NumericalNDArray<Double> 
        = when(this) {
    is NumericalNDArray<Double> -> { this }
    else -> { DefaultDoubleNDArray(*shape().toIntArray(), init={this.get(*it)}) }
}