@file:koma.internal.JvmName("Matrix")
@file:koma.internal.JvmMultifileClass

/**
 * THIS FILE IS AUTOGENERATED, DO NOT MODIFY. EDIT THE FILES IN templates/
 * AND RUN ./gradlew :codegen INSTEAD!
 */

package koma.extensions

import koma.matrix.Matrix
import koma.internal.KomaJsName
import koma.internal.KomaJvmName

/**
 * Checks to see if any element in the matrix causes f to return true.
 *
 * @param f A function which takes in an element from the matrix and returns a Boolean.
 *
 * @return Whether or not any element, when passed into f, causes f to return true.
 */
@KomaJsName("anyFloat")
@KomaJvmName("anyFloat")
inline fun  Matrix<Float>.any(f: (Float) -> Boolean): Boolean {
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            if (f(this[row, col]))
                return true
    return false
}

/**
 * Checks to see if all elements cause f to return true.
 *
 * @param f A function which takes in an element from the matrix and returns a Boolean.
 *
 * @return Returns true only if f is true for all elements of the input matrix
 */
@KomaJsName("allFloat")
@KomaJvmName("allFloat")
inline fun  Matrix<Float>.all(f: (Float) -> Boolean): Boolean {
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            if (!f(this[row, col]))
                return false
    return true
}

/**
 * Fills the matrix with the values returned by the input function.
 *
 * @param f A function which takes row,col and returns the value to fill. Note that
 * the return type must be the matrix primitive type (e.g. Double).
 */
@KomaJsName("fillFloat")
@KomaJvmName("fillFloat")
inline fun  Matrix<Float>.fill(f: (row: Int, col: Int) -> Float): Matrix<Float> {
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            this[row, col] = f(row, col)
    return this
}

/**
 * Passes each element in row major order into a function.
 *
 * @param f A function that takes in an element
 *
 */
@KomaJsName("forEachFloat")
@KomaJvmName("forEachFloat")
inline fun  Matrix<Float>.forEach(f: (Float) -> Unit) {
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            f(this[row, col])
}

/**
 * Passes each element in row major order into a function along with its index location.
 *
 * @param f A function that takes in a row,col position and an element value
 */
@KomaJsName("forEachIndexedFloat")
@KomaJvmName("forEachIndexedFloat")
inline fun  Matrix<Float>.forEachIndexed(f: (row: Int, col: Int, ele: Float) -> Unit) {
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            f(row, col, this[row, col])
}


/**
 * Takes each element in a matrix, passes them through f, and puts the output of f into an
 * output matrix. This process is done in row-major order.
 *
 * @param f A function that takes in an element and returns an element
 *
 * @return the new matrix after each element is mapped through f
 */
@KomaJsName("mapFloat")
@KomaJvmName("mapFloat")
inline fun  Matrix<Float>.map(f: (Float) -> Float): Matrix<Float> {
    val out = this.getFactory().zeros(this.numRows(), this.numCols())
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            out[row, col] = f(this[row, col])
    return out
}

/**
 * Takes each element in a matrix, passes them through f, and puts the output of f into an
 * output matrix. This process is done in row-major order.
 *
 * @param f A function that takes in an element and returns an element. Function also takes
 *      in the row, col index of the element's location.
 *
 * @return the new matrix after each element is mapped through f
 */
@KomaJsName("mapIndexedFloat")
@KomaJvmName("mapIndexedFloat")
inline fun  Matrix<Float>.mapIndexed(f: (row: Int, col: Int, ele: Float) -> Float): Matrix<Float> {
    val out = this.getFactory().zeros(this.numRows(), this.numCols())
    for (row in 0 until this.numRows())
        for (col in 0 until this.numCols())
            out[row, col] = f(row, col, this[row, col])
    return out
}


@KomaJsName("getFloat")
operator fun  Matrix<Float>.get(i: Int, j: Int): Float = getFloat(i, j)
/**
 * Gets the ith element in the matrix. If 2D, selects elements in row-major order.
 */
@KomaJsName("get1DFloat")
operator fun  Matrix<Float>.get(i: Int): Float = getFloat(i)

/**
 * Allow slicing, e.g. ```matrix[1..2, 3..4]```. Note that the range 1..2 is inclusive, so
 * it will retrieve row 1 and 2. Use 1.until(2) for a non-inclusive range.
 *
 * @param rows the set of rows to select
 * @param cols the set of columns to select
 *
 * @return a new matrix containing the submatrix.
 */
@KomaJvmName("getRangesFloat")
@KomaJsName("getRangesFloat")
operator fun  Matrix<Float>.get(rows: IntRange, cols: IntRange): Matrix<Float>
{
    val wrows = wrapRange(rows, numRows())
    val wcols = wrapRange(cols, numCols())

    val out = this.getFactory().zeros(wrows.endInclusive - wrows.start + 1,
            wcols.endInclusive - wcols.start + 1)
    for (row in wrows)
        for (col in wcols)
            out[row - wrows.start, col - wcols.start] = this[row, col]
    return out
}
/**
 * Allows for slicing of the rows and selection of a single column
 */
@KomaJvmName("setRowRangeFloat")
@KomaJsName("getRowRangeFloat")
operator fun  Matrix<Float>.get(rows: IntRange, cols: Int) = this[rows, cols..cols]

/**
 * Allows for slicing of the cols and selection of a single row
 */
@KomaJvmName("getColRangeFloat")
@KomaJsName("getColRangeFloat")
operator fun  Matrix<Float>.get(rows: Int, cols: IntRange) = this[rows..rows, cols]


/**
 * Set the ith element in the matrix. If 2D, selects elements in row-major order.
 */
@KomaJvmName("set1DFloat")
@KomaJsName("set1DFloat")
operator fun  Matrix<Float>.set(i: Int, v: Float) = setFloat(i, v)
@KomaJvmName("set2DFloat")
@KomaJsName("set2DFloat")
operator fun  Matrix<Float>.set(i: Int, j: Int, v: Float) = setFloat(i, j, v)
/**
 * Allow assignment to a slice, e.g. ```matrix[1..2, 3..4]```=something. Note that the range 1..2 is inclusive, so
 * it will retrieve row 1 and 2. Use 1.until(2) for a non-inclusive range.
 *
 * @param rows the set of rows to select
 * @param cols the set of columns to select
 * @param value the matrix to set the subslice to
 *
 */
@KomaJvmName("setRangesFloat")
@KomaJsName("setRangesFloat")
operator fun  Matrix<Float>.set(rows: IntRange, cols: IntRange, value: Matrix<Float>)
{
    val wrows = wrapRange(rows, numRows())
    val wcols = wrapRange(cols, numCols())

    for (i in wrows)
        for (j in wcols)
            this[i, j] = value[i - wrows.start, j - wcols.start]
}
@KomaJsName("setRangesScalarFloat")
operator fun  Matrix<Float>.set(rows: IntRange, cols: IntRange, value: Float)
{
    val wrows = wrapRange(rows, numRows())
    val wcols = wrapRange(cols, numCols())

    for (i in wrows)
        for (j in wcols)
            this[i, j] = value
}
/**
 * Allow assignment to a slice, e.g. ```matrix[2, 3..4]```=something. Note that the range 3..4 is inclusive, so
 * it will retrieve col 3 and 4. Use 1.until(2) for a non-inclusive range.
 *
 * @param rows the row to select
 * @param cols the set of columns to select
 * @param value the matrix to set the subslice to
 *
 */
@KomaJvmName("setColRangeFloat")
@KomaJsName("setColRangeFloat")
operator fun  Matrix<Float>.set(rows: Int, cols: IntRange, value: Matrix<Float>)
{
    this[rows..rows, cols] = value
}
@KomaJvmName("setColRangeScalarFloat")
@KomaJsName("setColRangeScalarFloat")
operator fun  Matrix<Float>.set(rows: Int, cols: IntRange, value: Float)
{
    this[rows..rows, cols] = value
}
/**
 * Allow assignment to a slice, e.g. ```matrix[1..2, 3]```=something. Note that the range 1..2 is inclusive, so
 * it will retrieve row 1 and 2. Use 1.until(2) for a non-inclusive range.
 *
 * @param rows the set of rows to select
 * @param cols the column to select
 * @param value the matrix to set the subslice to
 *
 */
@KomaJvmName("setRowRangeFloat")
@KomaJsName("setRowRangeFloat")
operator fun  Matrix<Float>.set(rows: IntRange, cols: Int, value: Matrix<Float>) {
    this[rows, cols..cols] = value
}
@KomaJsName("setRowRangeScalarFloat")
operator fun  Matrix<Float>.set(rows: IntRange, cols: Int, value: Float) {
    this[rows, cols..cols] = value
}


@KomaJvmName("set1DFloatFromInt")
@KomaJsName("set1DFloatFromInt")
operator fun Matrix<Float>.set(i: Int, v: Int) = this.setFloat(i, v.toFloat())
@KomaJvmName("set2DFloatFromInt")
@KomaJsName("set2DFloatFromInt")
operator fun Matrix<Float>.set(i: Int, j: Int, v: Int) = this.setFloat(i, j, v.toFloat())

@KomaJvmName("allCloseFloat")
fun Matrix<Float>.allClose(other: Matrix<Float>, rtol:Double=1e-05, atol:Double=1e-08): Boolean {
    if(other.numRows() != numRows() || other.numCols() != numCols())
        return false
    for(row in 0 until this.numRows()) {
        for (col in 0 until this.numCols()) {
            val err = kotlin.math.abs(this[row, col] - other[row, col])
            if (err > atol + rtol * kotlin.math.abs(this[row, col]))
                return false
        }
    }
    return true
}