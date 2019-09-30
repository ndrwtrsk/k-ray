package nd.rw.kray

class Canvas(val width: Int, val height: Int) {

    val pixelGrid = MutableList(height) { MutableList(width) { Color(0, 0, 0) } }

    fun writePixel(x: Int, y: Int, pixel: Color) {
        if(y !in 0 until pixelGrid.size || x !in 0 until pixelGrid[y].size) {
            return
        }
        pixelGrid[y][x] = pixel
    }

    fun pixelAt(x: Int, y: Int): Color = pixelGrid[y][x]

    fun <EXPORT_TYPE> export(exporter: CanvasExporter<EXPORT_TYPE>) : EXPORT_TYPE {
        return exporter.export(this)
    }

}