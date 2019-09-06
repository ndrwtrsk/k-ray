package nd.rw.kray

import nd.rw.kray.Tuple.Companion.point
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import com.winterbe.expekt.should
import nd.rw.kray.Tuple.Companion.vector

object TupleSpec : Spek({

    describe("creating tuples") {
        describe("as a point") {
            val point = point(4.3, 4.2, 3.1)
            it("is a point and not a vector") {
                point.isPoint.should.be.`true`
                point.isVector.should.be.`false`
            }
        }

        describe("as a vector") {
            val point = vector(4.3, 4.2, 3.1)
            it("is a vector and not a point") {
                point.isVector.should.be.`true`
                point.isPoint.should.be.`false`
            }
        }
    }

    describe("equality") {
        val vector1 = vector(1.1, 2.0, 3.0)
        val vector2 = vector(4.0, 5.0, 6.0)
        val vector3 = vector(1.1, 2.0, 3.0)

        it("vector1 != vector2") {
            vector1.should.not.be.equal(vector2)
        }

        it("vector1 == vector3") {
            vector1.should.be.equal(vector3)
        }
    }

    describe("operations") {
        describe("addition") {
            val tuple1 = Tuple(3.0, -2.0, 5.0, 1.0)
            val tuple2 = Tuple(-2.0, 3.0, 1.0, 0.0)

            it("should equal to sum of each value") {
                (tuple1 + tuple2).should.equal(Tuple(1.0, 1.0, 6.0, 1.0))
            }
        }

        describe("subtraction") {
            describe("subtracting two points") {
                val p1 = point(3.0, 2.0, 1.0)
                val p2 = point(5.0, 6.0, 7.0)

                it("should result in vector") {
                    (p1 - p2).should.be.equal(vector(-2.0, -4.0, -6.0))
                }
            }

            describe("subtracting a vector from a point") {
                val p = point(3.0, 2.0, 1.0)
                val v = vector(5.0, 6.0, 7.0)

                it("should result in a point") {
                    (p - v).should.equal(point(-2.0, -4.0, -6.0))
                }
            }

            describe("subtracting two vectors") {
                val v1 = vector(3.0, 2.0, 1.0)
                val v2 = vector(5.0, 6.0, 7.0)

                it("should result in a vector") {
                    (v1 - v2).should.be.equal(vector(-2.0, -4.0, -6.0))
                }
            }
        }

        describe("negation") {
            val a = Tuple(1.0, -2.0, 3.0, -4.0)

            it("should result in all values negated") {
                (-a).should.equal(Tuple(-1.0, 2.0, -3.0, 4.0))
            }
        }

        describe("scalar multiplication") {
            val a = Tuple(1, -2, 3, -4)

            describe("multiplying a tuple by a scalar") {
                it("should result in all values multiplied") {
                    (a * 3.5).should.equal(Tuple(3.5, -7.0, 10.5, -14.0))
                }

            }

            describe("multiplying a tuple by a fraction") {
                it("should result in all values multiplied") {
                    (a * 0.5).should.equal(Tuple(0.5, -1, 1.5, -2))
                }
            }
        }

    }
})