package nd.rw.kray

class Tuple(val x: Double, val y: Double, val z: Double, val w: Double) {

    companion object {

        @JvmStatic
        fun point(x: Double, y: Double, z: Double): Tuple {
            return Tuple(x, y, z, 1.0)
        }

        @JvmStatic
        fun vector(x: Double, y: Double, z: Double): Tuple {
            return Tuple(x, y, z, 0.0)
        }
    }

    val isPoint: Boolean
        get() = w == 1.0

    val isVector: Boolean
        get() = w == 0.0

}