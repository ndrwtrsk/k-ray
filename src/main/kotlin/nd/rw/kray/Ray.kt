package nd.rw.kray

import nd.rw.kray.Intersections.Companion.emptyIntersections
import nd.rw.kray.Tuple.Companion.point
import kotlin.math.sqrt

class Ray(val origin: Tuple, val direction: Tuple) {

    fun position(t: Number): Tuple {
        return origin + direction * t
    }

    fun transform(transformation: Matrix) : Ray {
        return Ray(transformation * origin, transformation * direction)
    }

}

fun intersect(sphere: Sphere, ray: Ray): Intersections {
    val transformedRay = ray.transform(sphere.transformation.inverted)
    val origin = transformedRay.origin
    val direction = transformedRay.direction
    val sphereToRayDistance = origin - point(0, 0, 0)
    val a = direction.dot(direction)
    val b = direction.dot(sphereToRayDistance) * 2
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