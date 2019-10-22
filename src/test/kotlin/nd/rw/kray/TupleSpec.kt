package nd.rw.kray

import com.winterbe.expekt.should
import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.math.sqrt

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

        describe("from a list of values") {
            val tuple = Tuple(listOf(1,2,3,4))
            it("is a correct tuple") {
                tuple.should.equal(Tuple(1,2,3,4))
            }
        }
    }

    describe("indexing operator") {
        val tuple = Tuple(1,2,3,4)
        it("values are correctly indexed") {
            tuple[0].should.equal(1.0)
            tuple[1].should.equal(2.0)
            tuple[2].should.equal(3.0)
            tuple[3].should.equal(4.0)
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

        describe("division") {
            val a = Tuple(1, -2, 3, -4)
            it("should result in all values divided") {
                (a / 2).should.equal(Tuple(0.5, -1, 1.5, -2))
            }
        }

        describe("dot product") {
            it("should compute values") {
                (vector(1, 2, 3).dot(vector(2, 3, 4))).should.equal(20.0)
            }
        }

        describe("cross product") {
            val a = vector(1, 2, 3)
            val b = vector(2, 3, 4)

            it("$a cross $b == ${vector(-1, 2, -1)}") {
                a.cross(b).should.equal(vector(-1, 2, -1))
            }

            it("$b cross $a == ${vector(1, -2, 1)}") {
                b.cross(a).should.equal(vector(1, -2, 1))
            }
        }
    }

    describe("magnitude") {
        listOf(
            listOf(vector(1, 0, 0), 1.0),
            listOf(vector(0, 1, 0), 1.0),
            listOf(vector(0, 0, 1), 1.0),
            listOf(vector(1, 2, 3), sqrt(14.0)),
            listOf(vector(-1, -2, -3), sqrt(14.0))
        ).forEach { test ->
            it("${test[0]} magnitude should be ${test[1]}") {
                val v = test[0] as Tuple
                v.magnitude.should.equal(test[1] as Double)
            }
        }
    }

    describe("normalization") {
        describe("normalizing vector(4,0,0)") {
            it("should result in vector(1,0,0)") {
                vector(4, 0, 0).normalize.should.equal(vector(1, 0, 0))
            }
        }

        describe("normalizing vector(1,2,3)") {
            it("should result in vector(1/sqrt(14), 2/sqrt(14), 3/sqrt(14)") {
                vector(1, 2, 3).normalize.should.equal(vector(1 / sqrt(14.0), 2 / sqrt(14.0), 3 / sqrt(14.0)))
            }
        }

        describe("magnitude of a normalized vector") {
            it("should be 1") {
                vector(1, 2, 3).normalize.magnitude.should.equal(1.0)
            }
        }
    }
})