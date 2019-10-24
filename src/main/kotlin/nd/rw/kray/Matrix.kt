package nd.rw.kray

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
    }

    private class MutableMatrix(rows: Int, columns: Int) : Matrix(MutableList(rows) { MutableList(columns) { 0.0 } }) {

        operator fun set(row: Int, column: Int, value: Number) {
            this.matrix[row][column] = value.toDouble()
        }
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