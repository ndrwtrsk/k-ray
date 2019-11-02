import com.winterbe.expekt.should
import nd.rw.kray.Matrix.Companion.scalingMatrix
import nd.rw.kray.Matrix.Companion.translationMatrix
import nd.rw.kray.Ray
import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class RaySpec : Spek({

    describe("creating a ray") {

        val origin = point(1, 2, 3)
        val direction = vector(4, 5, 6)

        val ray = Ray(origin, direction)

        it("querying ray") {
            ray.origin.should.equal(point(1, 2, 3))
            ray.direction.should.equal(vector(4, 5, 6))
        }
    }

    describe("computing a point from a distance") {
        val ray = Ray(point(2, 3, 4), vector(1, 0, 0))

        it("position at 0") {
            ray.positionAt(0).should.equal(point(2, 3, 4))
        }

        it("position at 1") {
            ray.positionAt(1).should.equal(point(3, 3, 4))
        }

        it("position at -1") {
            ray.positionAt(-1).should.equal(point(1, 3, 4))
        }

        it("position at 2.5") {
            ray.positionAt(2.5).should.equal(point(4.5, 3, 4))
        }
    }

    describe("translating a ray") {
        val ray = Ray(point(1, 2, 3), vector(0, 1, 0))
        val translate = translationMatrix(3, 4, 5)

        val translatedRay = ray.transformBy(translate)

        it("ray's origin and direction have been translated") {
            translatedRay.origin.should.equal(point(4, 6, 8))
            translatedRay.direction.should.equal(vector(0, 1, 0))
        }
    }

    describe("scaling a ray") {
        val ray = Ray(point(1, 2, 3), vector(0, 1, 0))
        val scale = scalingMatrix(2, 3, 4)

        val scaledRay = ray.transformBy(scale)

        it("ray's origin and direction have been scaled") {
            scaledRay.origin.should.equal(point(2, 6, 12))
            scaledRay.direction.should.equal(vector(0, 3, 0))
        }
    }

})