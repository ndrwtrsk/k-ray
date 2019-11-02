package nd.rw.kray

class Intersection(val t: Double, val o: Any) {
    constructor(t: Number, o: Any) : this(t.toDouble(), o)


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other === null) return false
        if (other !is Intersection) return false
        if (this.t != other.t) return false
        if (this.o != other.o) return false

        return true
    }

    override fun hashCode(): Int {
        var result = t.hashCode()
        result = 31 * result + o.hashCode()
        return result
    }
}

class Intersections(val intersections: List<Intersection>) {

    constructor(vararg intersections: Intersection) : this(intersections.toList())

    companion object {

        fun emptyIntersections(): Intersections {
            return Intersections(emptyList())
        }
    }

    val size: Int = intersections.size

    fun hit(): Intersection? {
        return intersections.filter { it.t > 0.0 }.minBy { it.t }
    }

    operator fun get(i: Int): Intersection {
        return intersections[i]
    }
}