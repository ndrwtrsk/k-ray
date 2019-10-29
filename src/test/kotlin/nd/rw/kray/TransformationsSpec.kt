package nd.rw.kray

import com.winterbe.expekt.should
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
            val translation = translation(5,-3,2)
            val v = vector(-3, 4, 5)

            it("vector is untouched") {
                (translation * v).should.equal(v)
            }
        }
    }


})