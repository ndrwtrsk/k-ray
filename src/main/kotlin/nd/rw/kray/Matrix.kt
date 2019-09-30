package nd.rw.kray

data class Row(val values: List<Number>)

class Matrix(private val values: List<List<Double>>) {

    companion object {

        @JvmStatic
        fun of(vararg rows: Row): Matrix {
            val map = rows.map { it.values.map { value -> value.toDouble() } }
            return Matrix(map)
        }

        @JvmStatic
        fun row(vararg values: Number): Row {
            return Row(values.toList())
        }
    }

    val numberOfRows: Int
        get() = values.size

    val numberOfColumns: Int
        get() = values[0].size

    operator fun get(row: Int, column: Int): Double {
        return values[row][column]
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        if (this.numberOfRows != other.numberOfRows) return false
        if (this.numberOfColumns != other.numberOfColumns) return false

        var equal = true

        this.values.forEachIndexed { i, columns ->
            columns.forEachIndexed { j, value ->
                equal = value.equalsWithMargin(other[i, j])
            }
        }

        return equal
    }

    override fun hashCode(): Int {
        return values.hashCode()
    }


}