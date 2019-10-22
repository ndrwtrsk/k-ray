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
                resultTuple[i] += this[i,j] * tuple[j]
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