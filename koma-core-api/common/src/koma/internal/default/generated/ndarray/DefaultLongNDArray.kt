/**
 * THIS FILE IS AUTOGENERATED, DO NOT MODIFY. EDIT THE FILES IN templates/
 * AND RUN ./gradlew :codegen INSTEAD!
 */

package koma.internal.default.generated.ndarray

import koma.ndarray.*
import koma.internal.KomaJsName
import koma.internal.default.utils.*


/**
 * An (unoptimized) implementation of [NDArray] in pure Kotlin, for portability between the
 * different platforms koma supports.
 *
 * @param shape A vararg specifying the size of each dimension, e.g. a 3D array with size 4x6x8 would pass in 4,6,8)
 * @param init A function that takes a location in the new array and returns its initial value.
 */
open class DefaultLongNDArray(@KomaJsName("shape_private") vararg protected val shape: Int,
                             init: ((IntArray)->Long)? = null): NDArray<Long> {

    /**
     * Underlying storage. PureKt backend uses a simple array.
     */
    private val storage: LongArray

    init {
        @Suppress("UNCHECKED_CAST")
        storage = if (init!=null) 
            LongArray(shape.reduce{ a, b-> a * b}, {init.invoke(linearToNIdx(it))}) 
        else
            LongArray(shape.reduce{ a, b-> a * b})
    }

    override fun getGeneric(vararg indices: Int): Long {
        checkIndices(indices)
        return storage[nIdxToLinear(indices)]
    }
    override fun getGeneric(i: Int): Long = storage[i]
    override fun setGeneric(i: Int, value: Long) { storage[i] = value }

    override fun setGeneric(vararg indices: Int, value: Long) {
        checkIndices(indices)
        storage[nIdxToLinear(indices)] = value
    }
    // TODO: cache this
    override val size get() = storage.size
    override fun shape(): List<Int> = shape.toList()
    override fun copy(): NDArray<Long> = DefaultLongNDArray(*shape, init = { this.getGeneric(*it) })
    override fun getBaseArray(): Any = storage

    private val wrongType = "Double methods not implemented for generic NDArray"
    override fun getDouble(i: Int): Double {
        val ele = storage[checkLinearIndex(i)]
        return ele.toDouble()
    }
    override fun setDouble(i: Int, v: Double) {
        storage[checkLinearIndex(i)] = v.toLong()
    }

    override fun getByte(i: Int): Byte {
        val ele = storage[checkLinearIndex(i)]
        return ele.toByte()
    }
    override fun setByte(i: Int, v: Byte) {
        storage[checkLinearIndex(i)] = v.toLong()
    }

    override fun getInt(i: Int): Int {
        val ele = storage[checkLinearIndex(i)]
        return ele.toInt()
    }
    override fun setInt(i: Int, v: Int) {
        storage[checkLinearIndex(i)] = v.toLong()
    }

    override fun getFloat(i: Int): Float {
        val ele = storage[checkLinearIndex(i)]
        return ele.toFloat()
    }
    override fun setFloat(i: Int, v: Float) {
        storage[checkLinearIndex(i)] = v.toLong()
    }

    override fun getLong(i: Int): Long {
        val ele = storage[checkLinearIndex(i)]
        return ele.toLong()
    }
    override fun setLong(i: Int, v: Long) {
        storage[checkLinearIndex(i)] = v.toLong()
    }

    override fun getShort(i: Int): Short {
        val ele = storage[checkLinearIndex(i)]
        return ele.toShort()
    }
    override fun setShort(i: Int, v: Short) {
        storage[checkLinearIndex(i)] = v.toLong()
    }



}

