package nd.rw.kray

import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector
import java.io.File
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

    val canvas = Canvas(2000, 550)

    while (projectile.position.y > 0) {
        println("Current position: ${projectile.position}")
        canvas.writePixel(
            x = projectile.position.x.roundToInt(),
            y = (canvas.height - projectile.position.y).roundToInt(),
            pixel = Color(255, 255, 255)
        )
        projectile = tick(projectile, environment)
    }

    val ppm = canvas.export(PpmCanvasExporter())
    File("projectile.ppm").writeText(ppm.value)
}
