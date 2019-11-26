import com.winterbe.expekt.should
import nd.rw.kray.*
import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector

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
        val xs = Intersections(Intersection(1, s), Intersection(2, s))

        it("aggregates individual intersections") {
            xs.intersections.size.should.equal(2)
            xs.intersections[0].t.should.equal(1.0)
            xs.intersections[1].t.should.equal(2.0)
        }
    }

    describe("hit") {
        val s = Sphere()
        val xs = Intersections(
            Intersection(5, s),
            Intersection(7, s),
            Intersection(-3, s),
            Intersection(2, s)
        )

        it("is always the lowest non negative intersection") {
            xs.hit().should.equal(Intersection(2, s))
        }
    }

    describe("precomputing the state of an intersection") {
        val ray = Ray(point(0,0,-5), vector(0,0,1))
        val shape = Sphere()
        val i = Intersection(4, shape)

        val comps = Computations.prepareComputations(i, ray)

        it("calculates correctly") {
            comps.t.should.equal(i.t)
            comps.o.should.equal(i.o)
            comps.point.should.equal(point(0,0,-1))
            comps.eyeVector.should.equal(vector(0,0,-1))
            comps.normalVector.should.equal(vector(0,0,-1))
        }
    }

})