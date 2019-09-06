package nd.rw.kray

class Canvas(val width: Int, val height: Int) {

    val pixelGrid = MutableList(height) { MutableList(width) { Color(0, 0, 0) } }

    fun writePixel(x: Int, y: Int, pixel: Color) {
        pixelGrid[y][x] = pixel
    }

    fun pixelAt(x: Int, y: Int): Color {
        return pixelGrid[y][x]
    }

}