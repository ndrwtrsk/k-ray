package nd.rw.kray

import nd.rw.kray.Intersections.Companion.emptyIntersections
import nd.rw.kray.Tuple.Companion.point
import kotlin.math.sqrt

class Ray(val origin: Tuple, val direction: Tuple) {

    fun position(t: Number): Tuple {
        return origin + direction * t
    }

}

fun intersect(sphere: Sphere, ray: Ray): Intersections {
    val sphereToRayDistance = ray.origin - point(0, 0, 0)
    val a = ray.direction.dot(ray.direction)
    val b = ray.direction.dot(sphereToRayDistance) * 2
    val c = sphereToRayDistance.dot(sphereToRayDistance) - 1

    val discriminant = (b * b) - (4 * a * c)

    if (discriminant < 0) {
        return emptyIntersections()
    }

    val intersectionValue1 = (-b - sqrt(discriminant)) / (2 * a)
    val intersectionValue2 = (-b + sqrt(discriminant)) / (2 * a)

    return Intersections(
        Intersection(intersectionValue1, sphere),
        Intersection(intersectionValue2, sphere)
    )
}