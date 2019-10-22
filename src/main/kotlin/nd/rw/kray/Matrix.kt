package nd.rw.kray

data class Row(val values: List<Number>) {
    constructor(vararg values: Number) : this(values.toList())
}

class Matrix(private val matrix: MutableList<MutableList<Double>> = arrayListOf()) {

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

    val numberOfRows: Int
        get() = matrix.size

    val numberOfColumns: Int
        get() = matrix[0].size

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