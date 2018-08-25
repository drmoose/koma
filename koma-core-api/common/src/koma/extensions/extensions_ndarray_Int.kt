@file:koma.internal.JvmName("NDArray")
@file:koma.internal.JvmMultifileClass

/**
 * THIS FILE IS AUTOGENERATED, DO NOT MODIFY. EDIT THE FILES IN templates/
 * AND RUN ./gradlew :codegen INSTEAD!
 */

package koma.extensions

import koma.internal.default.generated.ndarray.DefaultGenericNDArray
import koma.internal.default.utils.checkIndices
import koma.internal.default.utils.linearToNIdx
import koma.matrix.doubleFactory
import koma.ndarray.NDArray
import koma.ndarray.NumericalNDArrayFactory
import koma.internal.default.utils.nIdxToLinear
import koma.pow
import koma.matrix.Matrix

@koma.internal.JvmName("toMatrixInt")
fun NDArray<Int>.toMatrix(): Matrix<Int> {
    if (this is Matrix)
        return this
    val dims = this.shape()
    return when (dims.size) {
        1 -> { Matrix.intFactory.zeros(dims[0], 1).fill { row, _ -> this[row] } }
        2 -> { Matrix.intFactory.zeros(dims[0], dims[1]).fill { row, col -> this[row, col] } }
        else -> error("Cannot convert NDArray with ${dims.size} dimensions to matrix (must be 1 or 2)")
    }
}

@koma.internal.JvmName("fillInt")
inline fun  NDArray<Int>.fill(f: (idx: IntArray) -> Int) = apply {
    for ((nd, linear) in this.iterateIndices())
        this.setInt(linear, f(nd))
}

@koma.internal.JvmName("fillIntBoth")
inline fun  NDArray<Int>.fillBoth(f: (nd: IntArray, linear: Int) -> Int) = apply {
    for ((nd, linear) in this.iterateIndices())
        this.setInt(linear, f(nd, linear))
}

@koma.internal.JvmName("fillIntLinear")
inline fun  NDArray<Int>.fillLinear(f: (idx: Int) -> Int) = apply {
    for (idx in 0 until size)
        this.setInt(idx, f(idx))
}

@koma.internal.JvmName("createInt")
inline fun  NumericalNDArrayFactory<Int>.create(vararg lengths: Int, filler: (idx: IntArray) -> Int)
    = NDArray.intFactory.zeros(*lengths).fill(filler)


/**
 * Returns a new NDArray with the given shape, populated with the data in this array.
 *
 * @param dims Desired dimensions of the output array.
 *
 * @returns A copy of the elements in this array, shaped to the given number of rows and columns,
 *          such that `this.toList() == this.reshape(*dims).toList()`
 *
 * @throws IllegalArgumentException when the product of all of the given `dims` does not equal [size]
 */
@koma.internal.JvmName("reshapeInt")
fun  NDArray<Int>.reshape(vararg dims: Int): NDArray<Int> {
    if (dims.reduce { a, b -> a * b } != size)
        throw IllegalArgumentException("$size items cannot be reshaped to ${dims.toList()}")
    var idx = 0
    return NDArray.intFactory.zeros(*dims).fill { _ -> getInt(idx++) }
}


/**
 * Takes each element in a NDArray, passes them through f, and puts the output of f into an
 * output NDArray.
 *
 * @param f A function that takes in an element and returns an element
 *
 * @return the new NDArray after each element is mapped through f
 */
@koma.internal.JvmName("mapInt")
inline fun  NDArray<Int>.map(f: (Int) -> Int)
    = NDArray.intFactory.zeros(*shape().toIntArray()).fillLinear { f(this.getInt(it)) }


/**
 * Takes each element in a NDArray, passes them through f, and puts the output of f into an
 * output NDArray. Index given to f is a linear index, depending on the underlying storage
 * major dimension.
 *
 * @param f A function that takes in an element and returns an element. Function also takes
 *      in the linear index of the element's location.
 *
 * @return the new NDArray after each element is mapped through f
 */
@koma.internal.JvmName("mapIndexedInt")
inline fun  NDArray<Int>.mapIndexed(f: (idx: Int, ele: Int) -> Int)
    = NDArray.intFactory.zeros(*shape().toIntArray()).fillLinear { f(it, this.getInt(it)) }


/**
 * Takes each element in a NDArray and passes them through f.
 *
 * @param f A function that takes in an element
 *
 */
@koma.internal.JvmName("forEachInt")
inline fun  NDArray<Int>.forEach(f: (ele: Int) -> Unit) {
    // TODO: Change this back to iteration once there are non-boxing iterators
    for (idx in 0 until size)
        f(getInt(idx))
}
/**
 * Takes each element in a NDArray and passes them through f. Index given to f is a linear
 * index, depending on the underlying storage major dimension.
 *
 * @param f A function that takes in an element. Function also takes
 *      in the linear index of the element's location.
 *
 */
@koma.internal.JvmName("forEachIndexedInt")
inline fun  NDArray<Int>.forEachIndexed(f: (idx: Int, ele: Int) -> Unit) {
    // TODO: Change this back to iteration once there are non-boxing iterators
    for (idx in 0 until size)
        f(idx, getInt(idx))
}

/**
 * Takes each element in a NDArray, passes them through f, and puts the output of f into an
 * output NDArray. Index given to f is the full ND index of the element.
 *
 * @param f A function that takes in an element and returns an element. Function also takes
 *      in the ND index of the element's location.
 *
 * @return the new NDArray after each element is mapped through f
 */
@koma.internal.JvmName("mapIndexedNInt")
inline fun  NDArray<Int>.mapIndexedN(f: (idx: IntArray, ele: Int) -> Int): NDArray<Int>
    = NDArray.intFactory.zeros(*shape().toIntArray()).fillBoth { nd, linear -> f(nd, getInt(linear)) }


/**
 * Takes each element in a NDArray and passes them through f. Index given to f is the full
 * ND index of the element.
 *
 * @param f A function that takes in an element. Function also takes
 *      in the ND index of the element's location.
 *
 */
@koma.internal.JvmName("forEachIndexedNInt")
inline fun  NDArray<Int>.forEachIndexedN(f: (idx: IntArray, ele: Int) -> Unit) {
    for ((nd, linear) in iterateIndices())
        f(nd, getInt(linear))
}

/**
 * Converts this NDArray into a one-dimensional IntArray in row-major order.
 */
fun  NDArray<Int>.toIntArray() = IntArray(size) { getInt(it) }

@koma.internal.JvmName("getRangesInt")
operator fun  NDArray<Int>.get(vararg indices: IntRange): NDArray<Int> {
    checkIndices(indices.map { it.last }.toIntArray())
    return DefaultGenericNDArray<Int>(shape = *indices
            .map { it.last - it.first + 1 }
            .toIntArray()) { newIdxs ->
        val offsets = indices.map { it.first }
        val oldIdxs = newIdxs.zip(offsets).map { it.first + it.second }
        this.getGeneric(*oldIdxs.toIntArray())
    }
}

@koma.internal.JvmName("setInt")
operator fun  NDArray<Int>.set(vararg indices: Int, value: NDArray<Int>) {
    val shape = shape()
    val lastIndex = indices.mapIndexed { i, range -> range + value.shape()[i] }
    val outOfBounds = lastIndex.withIndex().any { it.value > shape()[it.index] }
    if (outOfBounds)
        throw IllegalArgumentException("NDArray with shape ${shape()} cannot be " +
                "set at ${indices.toList()} by a ${value.shape()} array " +
                "(out of bounds)")

    val offset = indices.map { it }.toIntArray()
    value.forEachIndexedN { idx, ele ->
        val newIdx = offset.zip(idx).map { it.first + it.second }.toIntArray()
        this.setGeneric(indices=*newIdx, v=ele)
    }
}


operator fun  NDArray<Int>.get(vararg indices: Int) = getInt(*indices)
operator fun  NDArray<Int>.set(vararg indices: Int, value: Int) = setInt(indices=*indices, v=value)


@koma.internal.JvmName("divInt")
operator fun NDArray<Int>.div(other: Int) = map { (it/other).toInt() }
@koma.internal.JvmName("timesArrInt")
operator fun NDArray<Int>.times(other: NDArray<Int>) = mapIndexedN { idx, ele -> (ele*other.get(*idx)).toInt() }
@koma.internal.JvmName("timesInt")
operator fun NDArray<Int>.times(other: Int) = map { (it * other).toInt() }
@koma.internal.JvmName("unaryMinusInt")
operator fun NDArray<Int>.unaryMinus() = map { (-it).toInt() }
@koma.internal.JvmName("minusInt")
operator fun NDArray<Int>.minus(other: Int) = map { (it - other).toInt() }
@koma.internal.JvmName("minusArrInt")
operator fun NDArray<Int>.minus(other: NDArray<Int>) = mapIndexedN { idx, ele -> (ele - other.get(*idx)).toInt() }
@koma.internal.JvmName("plusInt")
operator fun NDArray<Int>.plus(other: Int) = map { (it + other).toInt() }
@koma.internal.JvmName("plusArrInt")
operator fun NDArray<Int>.plus(other: NDArray<Int>) = mapIndexedN { idx, ele -> (ele + other.get(*idx)).toInt() }
@koma.internal.JvmName("powInt")
infix fun NDArray<Int>.pow(exponent: Int) = map { pow(it.toDouble(), exponent).toInt() }

