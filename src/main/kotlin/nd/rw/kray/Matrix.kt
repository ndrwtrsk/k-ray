package nd.rw.kray

data class Row(val values: List<Number>)

class Matrix(private val values: List<List<Double>>) {

    companion object {

        @JvmStatic
        fun of(vararg rows: Row) : Matrix {
            val map = rows.map { it.values.map { value -> value.toDouble() } }
            return Matrix(map)
        }

        @JvmStatic
        fun row(vararg values: Number): Row {
            return Row(values.toList())
        }
    }

    operator fun get(row: Int, column: Int) : Double {
        return values[row][column]
    }

}