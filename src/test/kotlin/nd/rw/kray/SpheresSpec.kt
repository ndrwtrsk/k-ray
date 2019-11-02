package nd.rw.kray

import com.winterbe.expekt.should
import nd.rw.kray.Matrix.Companion.identity
import nd.rw.kray.Matrix.Companion.translation
import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class SpheresSpec : Spek({

    describe("ray intersects a sphere at two points") {
        val ray = Ray(point(0, 0, -5), vector(0, 0, 1))
        val sphere = Sphere()

        val xs = intersect(sphere, ray)

        it("ray should intersect sphere at it's position of 4.0 and 6.0") {
            xs.size.should.equal(2)
            xs[0].t.should.equal(4.0)
            xs[1].t.should.equal(6.0)
            xs[0].o.should.equal(sphere)
            xs[1].o.should.equal(sphere)
        }
    }

    describe("ray intersects a sphere at a tangent") {
        val ray = Ray(point(0, 1, -5), vector(0, 0, 1))
        val sphere = Sphere()

        val xs = intersect(sphere, ray)

        it("ray should intersect sphere at position 5.0") {
            xs.size.should.equal(2)
            xs[0].t.should.equal(5.0)
            xs[1].t.should.equal(5.0)
        }
    }

    describe("ray misses a sphere") {
        val ray = Ray(point(0, 2, -5), vector(0, 0, 1))
        val sphere = Sphere()

        val xs = intersect(sphere, ray)

        it("ray has no intersections") {
            xs.size.should.equal(0)
        }
    }

    describe("ray originates inside a sphere") {
        val ray = Ray(point(0, 0, 0), vector(0, 0, 1))
        val sphere = Sphere()

        val xs = intersect(sphere, ray)

        it("ray should intersect sphere at -1.0 and 1.0") {
            xs.size.should.equal(2)
            xs[0].t.should.equal(-1.0)
            xs[1].t.should.equal(1.0)
        }
    }

    describe("sphere is behind a ray") {
        val ray = Ray(point(0, 0, 5), vector(0, 0, 1))
        val sphere = Sphere()

        val xs = intersect(sphere, ray)

        it("ray should intersect sphere at -6.0 and -4.0") {
            xs.size.should.equal(2)
            xs[0].t.should.equal(-6.0)
            xs[1].t.should.equal(-4.0)
        }
    }

    describe("sphere's default transformation") {
        val sphere = Sphere()

        it("is identity matrix") {
            sphere.transformation.should.equal(identity())
        }
    }

    describe("changing sphere's transformation") {
        val sphere = Sphere()
        sphere.transformation = translation(1, 2, 3)

        it("should set it as new one") {
            sphere.transformation.should.equal(translation(1, 2, 3))
        }
    }
})