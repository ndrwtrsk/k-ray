package nd.rw.kray

import nd.rw.kray.Tuple.Companion.point
import java.io.File

fun main() {
    val origin = point(0, 0, -5)
    val wallZ = 5.0
    val wallSize = 7.0
    val canvasPixels = 100
    val pixelSize = wallSize / canvasPixels;
    val half = wallSize / 2;

    val canvas = Canvas(canvasPixels, canvasPixels)
    val color = Color(1, 0, 0)
    val shape = Sphere()

    for (y in 0 until canvasPixels) {
        val worldY = half - pixelSize * y
        for (x in 0 until canvasPixels) {
            val worldX = half - pixelSize * x
            val position = point(worldX, worldY, wallZ)
            val ray = Ray(origin, (position - origin).normalize)
            val xs = intersect(shape, ray)
            xs.hit()?.let {
                canvas.writePixel(x, y, color)
            }
        }


    }

    val ppm = canvas.export(PpmCanvasExporter())
    File("ppms/FirstSphere.ppm").writeText(ppm.value)

}