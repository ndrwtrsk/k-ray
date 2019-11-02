package nd.rw.kray

import com.winterbe.expekt.should
import nd.rw.kray.Matrix.Companion.identityMatrix
import nd.rw.kray.Matrix.Companion.rotationMatrixAroundX
import nd.rw.kray.Matrix.Companion.rotationMatrixAroundY
import nd.rw.kray.Matrix.Companion.rotationMatrixAroundZ
import nd.rw.kray.Matrix.Companion.scalingMatrix
import nd.rw.kray.Matrix.Companion.shearingMatrix
import nd.rw.kray.Matrix.Companion.translationMatrix
import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.math.PI
import kotlin.math.sqrt

class TransformationsSpec : Spek({

    describe("translation") {
        describe("multiplying by translation matrix") {
            val translation = translationMatrix(5, -3, 2)
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
            val translation = translationMatrix(5, -3, 2)
            val v = vector(-3, 4, 5)

            it("vector is untouched") {
                (translation * v).should.equal(v)
            }
        }
    }

    describe("scaling") {
        describe("scaling matrix applied to a point") {
            val scaling = scalingMatrix(2, 3, 4)
            val p = point(-4, 6, 8)

            it("point is scaled") {
                (scaling * p).should.equal(point(-8, 18, 32))
            }
        }

        describe("scaling matrix applied to a vector") {
            val scaling = scalingMatrix(2, 3, 4)
            val p = vector(-4, 6, 8)
            it("vector is scaled") {
                (scaling * p).should.equal(vector(-8, 18, 32))
            }
        }

        describe("shrinking by applying inverse matrix to scaling matrix") {
            val shrinking = scalingMatrix(2, 3, 4).inverted
            val v = vector(-4, 6, 8)

            it("vector is shrinked") {
                (shrinking * v).should.equal(vector(-2, 2, 2))
            }
        }

        describe("reflection is scaling by a negative value") {
            val reflection = scalingMatrix(-1, 1, 1)
            val p = point(2, 3, 4)

            it("point is on the other side of x axis") {
                (reflection * p).should.equal(point(-2, 3, 4))
            }
        }
    }

    describe("rotation") {
        describe("around x axis") {
            val p = point(0, 1, 0)

            val halfQuarter = rotationMatrixAroundX(PI / 4)
            it("rotating point by half quarter should place it at 45 degrees") {
                (halfQuarter * p).should.equal(point(0, sqrt(2.0) / 2.0, sqrt(2.0) / 2.0))
            }

            val fullQuarter = rotationMatrixAroundX(PI / 2)
            it("rotating point by full quarter should place on z axis") {
                (fullQuarter * p).should.equal(point(0, 0, 1))
            }
        }

        describe("around y axis") {
            val p = point(0, 0, 1)

            val halfQuarter = rotationMatrixAroundY(PI / 4)
            it("rotating point by half quarter should place it at 45 degrees") {
                (halfQuarter * p).should.equal(point(sqrt(2.0) / 2.0, 0, sqrt(2.0) / 2.0))
            }

            val fullQuarter = rotationMatrixAroundY(PI / 2)
            it("rotating point by full quarter should place on x axis") {
                (fullQuarter * p).should.equal(point(1, 0, 0))
            }
        }

        describe("around z axis") {
            val p = point(0, 1, 0)

            val halfQuarter = rotationMatrixAroundZ(PI / 4)
            it("rotating point by half quarter should place it at 45 degrees") {
                (halfQuarter * p).should.equal(point(-sqrt(2.0) / 2.0, sqrt(2.0) / 2.0, 0))
            }

            val fullQuarter = rotationMatrixAroundZ(PI / 2)
            it("rotating point by full quarter should place on x axis") {
                (fullQuarter * p).should.equal(point(-1, 0, 0))
            }
        }
    }

    describe("shearing") {
        describe("a shearing transformation moves x in proportion to y") {
            val shearing = shearingMatrix(1, 0, 0, 0, 0, 0)
            val p = point(2, 3, 4)

            it("x should be moved in proportion 1 times y (3) to 5") {
                (shearing * p).should.equal(point(5, 3, 4))
            }
        }

        describe("a shearing transformation moves x in proportion to z") {
            val shearing = shearingMatrix(0, 1, 0, 0, 0, 0)
            val p = point(2, 3, 4)

            it("x should be moved in proportion 1 times z (4) to 6") {
                (shearing * p).should.equal(point(6, 3, 4))
            }
        }

        describe("a shearing transformation moves y in proportion to x") {
            val shearing = shearingMatrix(0, 0, 1, 0, 0, 0)
            val p = point(2, 3, 4)

            it("y should be moved in proportion 1 times x (2) to 5") {
                (shearing * p).should.equal(point(2, 5, 4))
            }
        }

        describe("a shearing transformation moves y in proportion to z") {
            val shearing = shearingMatrix(0, 0, 0, 1, 0, 0)
            val p = point(2, 3, 4)

            it("y should be moved in proportion 1 times z (4) to 7") {
                (shearing * p).should.equal(point(2, 7, 4))
            }
        }

        describe("a shearing transformation moves z in proportion to x") {
            val shearing = shearingMatrix(0, 0, 0, 0, 1, 0)
            val p = point(2, 3, 4)

            it("z should be moved in proportion 1 times x (2) to 6") {
                (shearing * p).should.equal(point(2, 3, 6))
            }
        }

        describe("a shearing transformation moves z in proportion to y") {
            val shearing = shearingMatrix(0, 0, 0, 0, 0, 1)
            val p = point(2, 3, 4)

            it("z should be moved in proportion 1 times y (3) to 7") {
                (shearing * p).should.equal(point(2, 3, 7))
            }
        }
    }

    describe("chaining transformations") {
        describe("individual transformations are applied in sequence") {
            val p = point(1, 0, 1)
            val rotate = rotationMatrixAroundX(PI / 2)
            val scale = scalingMatrix(5, 5, 5)
            val translate = translationMatrix(10, 5, 7)

            val p2 = rotate * p

            it("apply rotation first") {
                p2.should.equal(point(1, -1, 0))
            }

            val p3 = scale * p2

            it("then apply scaling") {
                p3.should.equal(point(5, -5, 0))
            }

            val p4 = translate * p3

            it("then apply translation") {
                p4.should.equal(point(15, 0, 7))
            }
        }

        describe("chained transformations must be applied in reverse order due to associative nature of matrix multiplication") {
            val p = point(1, 0, 1)
            val rotate = rotationMatrixAroundX(PI / 2)
            val scale = scalingMatrix(5, 5, 5)
            val translate = translationMatrix(10, 5, 7)

            val transformedPoint = translate * scale * rotate * p

            it("point should be transformed") {
                transformedPoint.should.equal(point(15, 0, 7))
            }
        }

        describe("transformations can be applied fluently") {
            val p = point(1, 0, 1)
            val transform = identityMatrix()
                .rotateAroundX(PI / 2)
                .scale(5, 5, 5)
                .translate(10, 5, 7)

            val transformedPoint = transform * p

            it("point should be transformed") {
                transformedPoint.should.equal(point(15, 0, 7))
            }

        }
    }
})