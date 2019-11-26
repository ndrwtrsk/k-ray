package nd.rw.kray

class Computations {

    private val intersection: Intersection
    private val ray: Ray
    val t: Double
    val o: Any
    val point: Tuple
    val eyeVector: Tuple
    val normalVector: Tuple

    private constructor(intersection: Intersection, ray: Ray) {
        this.intersection = intersection
        this.ray = ray
        this.t = intersection.t
        this.o = intersection.o
        this.point = ray.positionAt(this.t)
        this.eyeVector = -ray.direction
        this.normalVector = (this.o as Sphere).normalAt(this.point)
    }

    companion object {
        fun prepareComputations(intersection: Intersection, ray: Ray): Computations {
            return Computations(intersection, ray)
        }
    }


}