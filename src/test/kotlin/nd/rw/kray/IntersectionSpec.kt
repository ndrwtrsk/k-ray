import com.winterbe.expekt.should
import nd.rw.kray.Intersection
import nd.rw.kray.Intersections
import nd.rw.kray.Sphere

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class IntersectionSpec : Spek({

    describe("an intersection") {
        val s = Sphere()

        val i = Intersection(3.5, s)

        it("encapsulates t and object") {
            i.t.should.equal(3.5)
            i.o.should.equal(s)
        }
    }

    describe("intersections object") {
        val s = Sphere()
        val xs = Intersections(Intersection(1, s), Intersection(2,s))

        it("aggregates individual intersections") {
            xs.intersections.size.should.equal(2)
            xs.intersections[0].t.should.equal(1.0)
            xs.intersections[1].t.should.equal(2.0)
        }
    }

})