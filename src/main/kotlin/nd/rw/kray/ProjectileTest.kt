package nd.rw.kray

import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector

data class Projectile(val position: Tuple, val velocity: Tuple)
data class Environment(val gravity: Tuple, val wind: Tuple)

fun tick(projectile: Projectile, environment: Environment): Projectile{
    val newPosition = projectile.position + projectile.velocity
    val newVelocity = projectile.velocity + environment.gravity + environment.wind
    return Projectile(newPosition, newVelocity)
}

fun main() {
    var projectile = Projectile(
        position = point(0, 1, 0),
        velocity = vector(1, 1, 0).normalize
    )

    val environment = Environment(
        gravity = vector(0, -0.1, 0),
        wind = vector(0.01, 0, 0)
    )

    while(projectile.position.y > 0) {
        println("Current position: ${projectile.position}")
        projectile = tick(projectile, environment)
    }
}
