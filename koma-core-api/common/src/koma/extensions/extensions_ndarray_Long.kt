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



@koma.internal.JvmName("fillLong")
inline fun  NDArray<Long>.fill(f: (idx: IntArray) -> Long) = apply {
    for ((nd, linear) in this.iterateIndices())
        this.setLong(linear, f(nd))
}

@koma.internal.JvmName("fillLongBoth")
inline fun  NDArray<Long>.fillBoth(f: (nd: IntArray, linear: Int) -> Long) = apply {
    for ((nd, linear) in this.iterateIndices())
        this.setLong(linear, f(nd, linear))
}

@koma.internal.JvmName("fillLongLinear")
inline fun  NDArray<Long>.fillLinear(f: (idx: Int) -> Long) = apply {
    for (idx in 0 until size)
        this.setLong(idx, f(idx))
}

@koma.internal.JvmName("createLong")
inline fun  NumericalNDArrayFactory<Long>.create(vararg lengths: Int, filler: (idx: IntArray) -> Long)
    = NDArray.longFactory.zeros(*lengths).fill(filler)


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
@koma.internal.JvmName("reshapeLong")
fun  NDArray<Long>.reshape(vararg dims: Int): NDArray<Long> {
    if (dims.reduce { a, b -> a * b } != size)
        throw IllegalArgumentException("$size items cannot be reshaped to ${dims.toList()}")
    var idx = 0
    return NDArray.longFactory.zeros(*dims).fill { _ -> getLong(idx++) }
}


/**
 * Takes each element in a NDArray, passes them through f, and puts the output of f into an
 * output NDArray.
 *
 * @param f A function that takes in an element and returns an element
 *
 * @return the new NDArray after each element is mapped through f
 */
@koma.internal.JvmName("mapLong")
inline fun  NDArray<Long>.map(f: (Long) -> Long)
    = NDArray.longFactory.zeros(*shape().toIntArray()).fillLinear { f(this.getLong(it)) }


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
@koma.internal.JvmName("mapIndexedLong")
inline fun  NDArray<Long>.mapIndexed(f: (idx: Int, ele: Long) -> Long)
    = NDArray.longFactory.zeros(*shape().toIntArray()).fillLinear { f(it, this.getLong(it)) }


/**
 * Takes each element in a NDArray and passes them through f.
 *
 * @param f A function that takes in an element
 *
 */
@koma.internal.JvmName("forEachLong")
inline fun  NDArray<Long>.forEach(f: (ele: Long) -> Unit) {
    // TODO: Change this back to iteration once there are non-boxing iterators
    for (idx in 0 until size)
        f(getLong(idx))
}
/**
 * Takes each element in a NDArray and passes them through f. Index given to f is a linear
 * index, depending on the underlying storage major dimension.
 *
 * @param f A function that takes in an element. Function also takes
 *      in the linear index of the element's location.
 *
 */
@koma.internal.JvmName("forEachIndexedLong")
inline fun  NDArray<Long>.forEachIndexed(f: (idx: Int, ele: Long) -> Unit) {
    // TODO: Change this back to iteration once there are non-boxing iterators
    for (idx in 0 until size)
        f(idx, getLong(idx))
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
@koma.internal.JvmName("mapIndexedNLong")
inline fun  NDArray<Long>.mapIndexedN(f: (idx: IntArray, ele: Long) -> Long): NDArray<Long>
    = NDArray.longFactory.zeros(*shape().toIntArray()).fillBoth { nd, linear -> f(nd, getLong(linear)) }


/**
 * Takes each element in a NDArray and passes them through f. Index given to f is the full
 * ND index of the element.
 *
 * @param f A function that takes in an element. Function also takes
 *      in the ND index of the element's location.
 *
 */
@koma.internal.JvmName("forEachIndexedNLong")
inline fun  NDArray<Long>.forEachIndexedN(f: (idx: IntArray, ele: Long) -> Unit) {
    for ((nd, linear) in iterateIndices())
        f(nd, getLong(linear))
}

/**
 * Converts this NDArray into a one-dimensional LongArray in row-major order.
 */
fun  NDArray<Long>.toLongArray() = LongArray(size) { getLong(it) }

@koma.internal.JvmName("getRangesLong")
operator fun  NDArray<Long>.get(vararg indices: IntRange): NDArray<Long> {
    checkIndices(indices.map { it.last }.toIntArray())
    return DefaultGenericNDArray<Long>(shape = *indices
            .map { it.last - it.first + 1 }
            .toIntArray()) { newIdxs ->
        val offsets = indices.map { it.first }
        val oldIdxs = newIdxs.zip(offsets).map { it.first + it.second }
        this.getGeneric(*oldIdxs.toIntArray())
    }
}

@koma.internal.JvmName("setLong")
operator fun  NDArray<Long>.set(vararg indices: Int, value: NDArray<Long>) {
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


operator fun  NDArray<Long>.get(vararg indices: Int) = getLong(*indices)
operator fun  NDArray<Long>.set(vararg indices: Int, value: Long) = setLong(indices=*indices, v=value)


@koma.internal.JvmName("divLong")
operator fun NDArray<Long>.div(other: Long) = map { (it/other).toLong() }
@koma.internal.JvmName("timesArrLong")
operator fun NDArray<Long>.times(other: NDArray<Long>) = mapIndexedN { idx, ele -> (ele*other.get(*idx)).toLong() }
@koma.internal.JvmName("timesLong")
operator fun NDArray<Long>.times(other: Long) = map { (it * other).toLong() }
@koma.internal.JvmName("unaryMinusLong")
operator fun NDArray<Long>.unaryMinus() = map { (-it).toLong() }
@koma.internal.JvmName("minusLong")
operator fun NDArray<Long>.minus(other: Long) = map { (it - other).toLong() }
@koma.internal.JvmName("minusArrLong")
operator fun NDArray<Long>.minus(other: NDArray<Long>) = mapIndexedN { idx, ele -> (ele - other.get(*idx)).toLong() }
@koma.internal.JvmName("plusLong")
operator fun NDArray<Long>.plus(other: Long) = map { (it + other).toLong() }
@koma.internal.JvmName("plusArrLong")
operator fun NDArray<Long>.plus(other: NDArray<Long>) = mapIndexedN { idx, ele -> (ele + other.get(*idx)).toLong() }
@koma.internal.JvmName("powLong")
infix fun NDArray<Long>.pow(exponent: Int) = map { pow(it.toDouble(), exponent).toLong() }

