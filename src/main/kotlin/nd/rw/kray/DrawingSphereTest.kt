package nd.rw.kray

import nd.rw.kray.Tuple.Companion.point
import java.io.File
import kotlin.system.measureTimeMillis

fun main() {
    val time = measureTimeMillis { execute() }
    println("It took $time ms to compute this.")
}

fun execute() {
    val origin = point(0, 0, -5)
    val wallZ = 5.0
    val wallSize = 7.0
    val canvasPixels = 2000
    val pixelSize = wallSize / canvasPixels;
    val half = wallSize / 2;

    val canvas = Canvas(canvasPixels, canvasPixels)
    val shape = Sphere(material = Material(color = Color(1, 0.2, 1)))

    val light = PointLight(point(-10, 10, -10), Color.white)

    val canvasPixelsRange = 0 until canvasPixels
    canvasPixelsRange.forEach { y ->
        val worldY = half - pixelSize * y
        canvasPixelsRange.forEach { x ->
            val worldX = half - pixelSize * x
            val position = point(worldX, worldY, wallZ)
            val ray = Ray(origin, (position - origin).normalize)
            val xs = intersect(shape, ray)
            xs.hit()?.let {
                val point = ray.positionAt(it.t)
                val hitObject = it.o as Sphere
                val normal = hitObject.normalAt(point)
                val eye = -ray.direction
                val color = lighting(hitObject.material, light, point, eye, normal)
                canvas.writePixel(x, y, color)
            }
        }
    }


    val ppm = canvas.export(PpmCanvasExporter())
    File("ppms/FirstSphere.ppm").writeText(ppm.value)
}