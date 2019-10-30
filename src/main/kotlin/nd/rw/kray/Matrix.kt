package nd.rw.kray

import kotlin.math.cos
import kotlin.math.sin

data class Row(val values: List<Number>) {
    constructor(vararg values: Number) : this(values.toList())
}

open class Matrix(protected val matrix: MutableList<MutableList<Double>> = arrayListOf()) {

    operator fun Row.unaryPlus() {
        matrix.add(this.values.map { it.toDouble() }.toMutableList())
    }

    companion object {
        @JvmStatic
        fun matrix(init: Matrix.() -> Unit): Matrix {
            val matrix = Matrix()
            matrix.init()
            return matrix;
        }

        fun identityMatrix(): Matrix {
            return matrix {
                +Row(1, 0, 0, 0)
                +Row(0, 1, 0, 0)
                +Row(0, 0, 1, 0)
                +Row(0, 0, 0, 1)
            }
        }

        fun translation(x: Int, y: Int, z: Int): Matrix {
            return matrix {
                +Row(1, 0, 0, x)
                +Row(0, 1, 0, y)
                +Row(0, 0, 1, z)
                +Row(0, 0, 0, 1)
            }
        }

        fun scaling(x: Int, y: Int, z: Int): Matrix {
            return matrix {
                +Row(x, 0, 0, 0)
                +Row(0, y, 0, 0)
                +Row(0, 0, z, 0)
                +Row(0, 0, 0, 1)
            }
        }

        fun rotationAroundX(radiansAsNumber: Number): Matrix {
            val radians = radiansAsNumber.toDouble()
            return matrix {
                +Row(1, 0, 0, 0)
                +Row(0, cos(radians), -sin(radians), 0)
                +Row(0, sin(radians), cos(radians), 0)
                +Row(0, 0, 0, 1)
            }
        }

        fun rotationAroundY(radiansAsNumber: Number): Matrix {
            val radians = radiansAsNumber.toDouble()
            return matrix {
                +Row(cos(radians), 0, sin(radians), 0)
                +Row(0, 1, 0, 0)
                +Row(-sin(radians), 0, cos(radians), 0)
                +Row(0, 0, 0, 1)
            }
        }

        fun rotationAroundZ(radiansAsNumber: Number): Matrix {
            val radians = radiansAsNumber.toDouble()
            return matrix {
                +Row(cos(radians), -sin(radians), 0, 0)
                +Row(sin(radians), cos(radians), 0, 0)
                +Row(0, 0, 1, 0)
                +Row(0, 0, 0, 1)
            }
        }

        fun shearing(
            xToY: Number, xToZ: Number,
            yToX: Number, yToZ: Number,
            zToX: Number, zToY: Number
        ): Matrix {
            return matrix {
                +Row(1, xToY, xToZ, 0)
                +Row(yToX, 1, yToZ, 0)
                +Row(zToX, zToY, 1, 0)
                +Row(0, 0, 0, 1)
            }
        }
    }

    private class MutableMatrix(rows: Int, columns: Int) : Matrix(MutableList(rows) { MutableList(columns) { 0.0 } }) {

        operator fun set(row: Int, column: Int, value: Number) {
            this.matrix[row][column] = value.toDouble()
        }
    }

    val inverted: Matrix
        get() {
            if (!isInvertible) throw Exception("matrix is not invertible!")

            val inverted = MutableMatrix(this.numberOfRows, this.numberOfColumns)
            for (row in 0 until size) {
                for (col in 0 until size) {
                    inverted[col, row] = cofactor(row, col) / determinant
                }
            }
            return inverted
        }

    val isInvertible: Boolean
        get() {
            return !determinant.equalsWithMargin(0.0)
        }

    val numberOfRows: Int
        get() = matrix.size

    val numberOfColumns: Int
        get() = matrix[0].size

    val size: Int
        get() = matrix.size

    val transpose: Matrix
        get() {
            val result = MutableMatrix(4, 4)
            for (i in 0 until 4) {
                for (j in 0 until 4) {
                    result[j, i] = this[i, j]
                }
            }
            return result
        }

    val determinant: Double
        get() {
            if (size == 2) {
                return this[0, 0] * this[1, 1] - this[0, 1] * this[1, 0]
            }
            var determinant = 0.0
            for (column in 0 until size) {
                determinant += this[0, column] * cofactor(0, column)
            }
            return determinant
        }

    fun submatrix(row: Int, column: Int): Matrix {
        val copiedMatrix = matrix.map { it.toMutableList() }.toMutableList()
        copiedMatrix.removeAt(row)
        copiedMatrix.forEach { it.removeAt(column) }
        return Matrix(copiedMatrix)
    }

    fun minor(row: Int, column: Int): Double {
        return submatrix(row, column).determinant
    }

    fun cofactor(row: Int, column: Int): Double {
        val isEven = (row + column) % 2 == 0
        return minor(row, column) * if (isEven) 1.0 else -1.0
    }

    operator fun times(other: Matrix): Matrix {
        val result = MutableMatrix(4, 4)
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                for (k in 0 until 4) {
                    result[i, j] += this[i, k] * other[k, j]
                }
            }
        }
        return result
    }

    operator fun times(tuple: Tuple): Tuple {
        val resultTuple = MutableList(4) { 0.0 }
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                resultTuple[i] += this[i, j] * tuple[j]
            }
        }
        return Tuple(resultTuple)
    }

    operator fun get(row: Int, column: Int): Double {
        return matrix[row][column]
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other === null) return false
        if (other !is Matrix) return false

        if (this.numberOfRows != other.numberOfRows) return false
        if (this.numberOfColumns != other.numberOfColumns) return false

        var equal = true

        this.matrix.forEachIndexed { i, columns ->
            columns.forEachIndexed { j, value ->
                equal = value.equalsWithMargin(other[i, j])
            }
        }

        return equal
    }

    override fun hashCode(): Int {
        return matrix.hashCode()
    }

    override fun toString(): String {
        return matrix.joinToString(separator = "\n") { it.toString() }
    }
}