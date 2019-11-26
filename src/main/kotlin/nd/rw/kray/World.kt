package nd.rw.kray

import nd.rw.kray.Color.Companion.white
import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.intersect as computeIntersections

class World(val pointLight: PointLight, val spheres: List<Sphere>) {

    companion object {
        val defaultWorld
            get() = World(
                PointLight(point(-10, -10, -10), white),
                listOf(
                    Sphere(
                        material = Material(
                            color = Color(0.8, 1.0, 0.6),
                            diffuse = 0.7,
                            specular = 0.2
                        )
                    ),
                    Sphere(transformation = Matrix.scalingMatrix(0.5, 0.5, 0.5))
                )
            )
    }

    fun contains(sphere: Sphere): Boolean {
        return spheres.contains(sphere)
    }

    fun intersect(ray: Ray): Intersections {
        val intersections = spheres.map { sphere -> computeIntersections(sphere, ray) }
            .flatMap(Intersections::intersections)

        return Intersections(intersections)

    }

}