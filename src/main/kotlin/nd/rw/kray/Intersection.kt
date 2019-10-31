package nd.rw.kray

class Intersection(val t: Double, val o: Any) {
    constructor(t: Number, o: Any) : this(t.toDouble(), o)
}

class Intersections(val intersections: List<Intersection>) {

    constructor(vararg intersections: Intersection) : this(intersections.toList())

    companion object {

        fun emptyIntersections(): Intersections {
            return Intersections(emptyList())
        }
    }

    val size: Int = intersections.size

    operator fun get(i: Int): Intersection {
        return intersections[i]
    }
}