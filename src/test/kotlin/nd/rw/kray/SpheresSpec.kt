package nd.rw.kray

import com.winterbe.expekt.should
import nd.rw.kray.Matrix.Companion.identityMatrix
import nd.rw.kray.Matrix.Companion.rotationMatrixAroundZ
import nd.rw.kray.Matrix.Companion.scalingMatrix
import nd.rw.kray.Matrix.Companion.translationMatrix
import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.math.PI
import kotlin.math.sqrt

class SpheresSpec : Spek({

    describe("intersections") {
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

        describe("intersecting a scaled sphere with a ray") {
            val ray = Ray(point(0, 0, -5), vector(0, 0, 1))
            val sphere = Sphere(transformation = scalingMatrix(2, 2, 2))

            val xs = intersect(sphere, ray)

            it("should apply inverse sphere transformation to ray") {
                xs[0].t.should.equal(3.0)
                xs[1].t.should.equal(7.0)
            }
        }
    }

    describe("sphere's default transformation") {
        val sphere = Sphere()

        it("is identity matrix") {
            sphere.transformation.should.equal(identityMatrix())
        }
    }

    describe("changing sphere's transformation") {
        val sphere = Sphere(transformation = translationMatrix(1, 2, 3))

        it("should set it as new one") {
            sphere.transformation.should.equal(translationMatrix(1, 2, 3))
        }
    }

    describe("normal") {
        val s = Sphere()

        it("on a sphere at a point on the x axis") {
            s.normalAt(point(1, 0, 0)).should.equal(vector(1, 0, 0))
        }

        it("on a sphere at a point on the y axis") {
            s.normalAt(point(0, 1, 0)).should.equal(vector(0, 1, 0))
        }

        it("on a sphere at a point on the z axis") {
            s.normalAt(point(0, 0, 1)).should.equal(vector(0, 0, 1))
        }

        it("on a sphere at a point at a nonaxial point") {
            s.normalAt(point(x = sqrt(3.0) / 3, y = sqrt(3.0) / 3, z = sqrt(3.0) / 3))
                .should.equal(vector(x = sqrt(3.0) / 3, y = sqrt(3.0) / 3, z = sqrt(3.0) / 3))
        }

        describe("is a normalized vector") {
            val s = Sphere()
            val normal = s.normalAt(point(x = sqrt(3.0) / 3, y = sqrt(3.0) / 3, z = sqrt(3.0) / 3))

            it("should be normalized") {
                normal.should.equal(normal.normalize)
            }
        }

        describe("on a translated sphere") {
            val s = Sphere(transformation = translationMatrix(0, 1, 0))

            val normal = s.normalAt(point(0, 1.70711, -0.70711))

            it("is computed correctly") {
                normal.should.equal(vector(0, 0.70711, -0.70711))
            }
        }

        describe("on a scaled and rotate sphere") {
            val s = Sphere(transformation = rotationMatrixAroundZ(PI / 5).scale(1, 0.5, 1))

            val normal = s.normalAt(point(0, (sqrt(2.0) / 2), (-sqrt(2.0) / 2)))

            it("is computed correctly") {
                normal.should.equal(vector(0, 0.97014, -0.24254))
            }
        }
    }
})