package nd.rw.kray

import com.winterbe.expekt.should
import nd.rw.kray.Matrix.Companion.scaling
import nd.rw.kray.Matrix.Companion.translation
import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class TransformationsSpec : Spek({

    describe("translation") {
        describe("multiplying by translation matrix") {
            val translation = translation(5, -3, 2)
            val p = point(-3, 4, 5)

            it("point is translated") {
                (translation * p).should.equal(point(2, 1, 7))
            }

            val invertedTranslation = translation.inverted

            it("point is translated in reverse if multiplied by translation inversion") {
                (invertedTranslation * p).should.equal(point(-8, 7, 3))
            }
        }

        /**
         * This is because of the w component in vector which is 0 (whereas point has 1 in there).
         */
        describe("translation does not affect vectors") {
            val translation = translation(5, -3, 2)
            val v = vector(-3, 4, 5)

            it("vector is untouched") {
                (translation * v).should.equal(v)
            }
        }
    }

    describe("scaling") {
        describe("scaling matrix applied to a point") {
            val scaling = scaling(2, 3, 4)
            val p = point(-4, 6, 8)

            it("point is scaled") {
                (scaling * p).should.equal(point(-8, 18, 32))
            }
        }

        describe("scaling matrix applied to a vector") {
            val scaling = scaling(2, 3, 4)
            val p = vector(-4, 6, 8)
            it("vector is scaled") {
                (scaling * p).should.equal(vector(-8, 18, 32))
            }
        }

        describe("shrinking by applying inverse matrix to scaling matrix") {
            val shrinking = scaling(2,3,4).inverted
            val v = vector(-4, 6, 8)

            it("vector is shrinked") {
                (shrinking * v).should.equal(vector(-2, 2, 2))
            }
        }

        describe("reflection is scaling by a negative value") {
            val reflection = scaling(-1, 1, 1)
            val p = point(2,3,4)

            it("point is on the other side of x axis") {
                (reflection * p).should.equal(point(-2,3,4))
            }
        }
    }


})