package nd.rw.kray

import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

data class Projectile(val position: Tuple, val velocity: Tuple)
data class Environment(val gravity: Tuple, val wind: Tuple)

fun tick(projectile: Projectile, environment: Environment): Projectile {
    val newPosition = projectile.position + projectile.velocity
    val newVelocity = projectile.velocity + environment.gravity + environment.wind
    return Projectile(newPosition, newVelocity)
}

fun main() {
    var projectile = Projectile(
        position = point(0, 1, 0),
        velocity = vector(1, 1, 0).normalize * 13
    )

    val environment = Environment(
        gravity = vector(0, -0.1, 0),
        wind = vector(-0.05, 0, 0)
    )

    var i = 0
    val fileNames = mutableListOf<String>()
    while (projectile.position.y > 0) {
//        println("Current position: ${projectile.position}")
        val canvas = Canvas(900, 550)
        writeInCanvasPixelGrid(
            canvas = canvas,
            x = projectile.position.x.roundToInt(),
            y = projectile.position.y.roundToInt(),
            pixel = Color(1, 1, 1)
        )
        projectile = tick(projectile, environment)
        val ppm = canvas.export(PpmCanvasExporter())
        val fileName = "ppms/projectile$i.ppm"
        File(fileName).writeText(ppm.value)
        fileNames.add(fileName)
        i++
    }
    "convert -delay 10 ${fileNames.joinToString(separator = " ", transform = { it })} animated.gif".trimIndent().runCommand(File("."))
    fileNames.forEach { file -> File(file).delete() }
    println("Finished rendering.")
}

fun writeInCanvasPixelGrid(canvas: Canvas, x: Int, y: Int, pixel: Color) {
    for (ix in -4..4) {
        for (iy in -4..4) {
            canvas.writePixel(
                x = x + ix,
                y = (canvas.height - y + iy),
                pixel = Color(255, 255, 255)
            )
        }
    }

}

fun String.runCommand(workingDir: File) {
    ProcessBuilder(*split(" ").toTypedArray())
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start()
        .waitFor(60, TimeUnit.MINUTES)
}
