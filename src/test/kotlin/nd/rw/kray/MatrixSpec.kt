import com.winterbe.expekt.should
import nd.rw.kray.Matrix.Companion.identityMatrix
import nd.rw.kray.Matrix.Companion.matrix
import nd.rw.kray.Row
import nd.rw.kray.Tuple
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


class MatrixSpec : Spek({

    describe("constructing matrices") {

        describe("matrix construction") {
            val matrix = matrix {
                +Row(1, 2, 3)
                +Row(4, 5, 6)
                +Row(7, 8, 9)
            }

            it("values should be set") {
                matrix[0, 0].should.equal(1.0)
                matrix[0, 1].should.equal(2.0)
                matrix[0, 2].should.equal(3.0)
                matrix[1, 0].should.equal(4.0)
                matrix[1, 1].should.equal(5.0)
                matrix[1, 2].should.equal(6.0)
                matrix[2, 0].should.equal(7.0)
                matrix[2, 1].should.equal(8.0)
                matrix[2, 2].should.equal(9.0)
            }
        }
    }

    describe("operators") {

        describe("equality with identical matrices") {
            val a = matrix {
                +Row(1, 2, 3, 4)
                +Row(5, 6, 7, 8)
                +Row(9, 8, 7, 6)
                +Row(5, 4, 3, 2)
            }

            val b = matrix {
                +Row(1, 2, 3, 4)
                +Row(5, 6, 7, 8)
                +Row(9, 8, 7, 6)
                +Row(5, 4, 3, 2)
            }

            it("matrices are equal") {
                (a == b).should.equal(true)
            }

        }

        describe("equality with different matrices") {
            val a = matrix {
                +Row(1, 2, 3, 4)
                +Row(5, 6, 7, 8)
                +Row(9, 8, 7, 6)
                +Row(5, 4, 3, 2)
            }
            val b = matrix {
                +Row(5, 6, 7, 8)
                +Row(9, 8, 7, 6)
                +Row(5, 4, 3, 2)
                +Row(1, 2, 3, 4)
            }

            it("matrices are not equal") {
                (a != b).should.equal(true)
            }

        }

        describe("multiplication") {
            describe("matrices") {
                val a = matrix {
                    +Row(1, 2, 3, 4)
                    +Row(5, 6, 7, 8)
                    +Row(9, 8, 7, 6)
                    +Row(5, 4, 3, 2)
                }

                val b = matrix {
                    +Row(-2, 1, 2, 3)
                    +Row(3, 2, 1, -1)
                    +Row(4, 3, 6, 5)
                    +Row(1, 2, 7, 8)
                }

                it("a * b is the following matrix") {
                    val result = a * b
                    result.should.equal(
                        matrix {
                            +Row(20, 22, 50, 48)
                            +Row(44, 54, 114, 108)
                            +Row(40, 58, 110, 102)
                            +Row(16, 26, 46, 42)
                        }
                    )
                }
            }

            describe("matrix by tuple") {
                val a = matrix {
                    +Row(1, 2, 3, 4)
                    +Row(2, 4, 4, 2)
                    +Row(8, 6, 4, 1)
                    +Row(0, 0, 0, 1)
                }

                val b = Tuple(1, 2, 3, 1)

                it("a * b is a following tuple") {
                    val result = a * b
                    result.should.equal(Tuple(18, 24, 33, 1))
                }
            }

            describe("matrix by identity matrix") {
                val a = matrix {
                    +Row(1, 2, 3, 4)
                    +Row(5, 6, 7, 8)
                    +Row(9, 8, 7, 6)
                    +Row(5, 4, 3, 2)
                }

                it("yields the same matrix") {
                    val result = a * identityMatrix()
                    result.should.equal(a)
                }
            }

            describe("tuple by identity matrix") {
                val a = Tuple(1, 2, 3, 4)

                it("yields the same tuple") {
                    val result = identityMatrix() * a
                    result.should.equal(a)
                }
            }
        }

    }

    describe("transposing") {
        val a = matrix {
            +Row(0, 9, 3, 0)
            +Row(9, 8, 0, 0)
            +Row(1, 8, 5, 3)
            +Row(0, 0, 5, 8)
        }

        it("yields transposed matrix") {
            val transposed = a.transpose
            transposed.should.equal(
                matrix {
                    +Row(0, 9, 1, 0)
                    +Row(9, 8, 8, 0)
                    +Row(3, 0, 5, 5)
                    +Row(0, 0, 3, 8)
                }
            )
        }

    }

    describe("determinant") {
        val a = matrix {
            +Row(1, 5)
            +Row(-3, 2)
        }
        it("computes determinant correctly") {
            a.determinant.should.equal(17.0)
        }
    }

})