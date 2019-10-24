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
        describe("determinant of 2x2 matrix") {
            val a = matrix {
                +Row(1, 5)
                +Row(-3, 2)
            }
            it("computes determinant correctly") {
                a.determinant.should.equal(17.0)
            }
        }

        describe("determinant of 3x3 matrix") {
            val a = matrix {
                +Row(1, 2, 6)
                +Row(-5, 8, -4)
                +Row(2, 6, 4)
            }

            it("cofactor of a at (0,0) is 56") {
                a.cofactor(0,0).should.equal(56.0)
            }

            it("cofactor of a at (0,1) is 12") {
                a.cofactor(0,1).should.equal(12.0)
            }

            it("cofactor of a at (0,2) is -46") {
                a.cofactor(0,2).should.equal(-46.0)
            }

            it("determinant of a is -196") {
                a.determinant.should.equal(-196.0)
            }
        }

        describe("determinant of 4x4 matrix") {
            val a = matrix {
                +Row(-2, -8, 3, 5)
                +Row(-3, 1, 7, 3)
                +Row(1, 2, -9, 6)
                +Row(-6, 7, 7, -9)
            }

            it("cofactor of a at (0,0) is 690") {
                a.cofactor(0,0).should.equal(690.0)
            }

            it("cofactor of a at (0,1) is 447") {
                a.cofactor(0,1).should.equal(447.0)
            }

            it("cofactor of a at (0,2) is 210") {
                a.cofactor(0,2).should.equal(210.0)
            }

            it("cofactor of a at (0,3) is 51") {
                a.cofactor(0,3).should.equal(51.0)
            }

            it("determinant of a is -4071") {
                a.determinant.should.equal(-4071.0)
            }
        }

    }

    describe("submatrix") {
        describe("A submatrix of 3x3 matrix is a 2x2 matrix") {
            val a = matrix {
                +Row(1, 5, 0)
                +Row(-3, 2, 7)
                +Row(0, 6, -3)
            }

            val result = a.submatrix(0, 2)

            it("submatrix at (0,2) is the following 2x2 matrix") {
                result.should.equal(
                    matrix {
                        +Row(-3, 2)
                        +Row(0, 6)
                    }
                )
            }

            it("original matrix preserved it's state") {
                a.numberOfColumns.should.equal(3)
                a.numberOfRows.should.equal(3)
            }
        }

        describe("A submatrix of 4x4 matrix is a 3x3 matrix") {
            val a = matrix {
                +Row(-6, 1, 1, 6)
                +Row(-8, 5, 8, 6)
                +Row(-1, 0, 8, 2)
                +Row(-7, -1, -1, 1)
            }

            val result = a.submatrix(2, 1)

            it("submatrix at (2,1) is the following 3x3 matrix") {
                result.should.equal(
                    matrix {
                        +Row(-6, 1, 6)
                        +Row(-8, 8, 6)
                        +Row(-7, -1, 1)
                    }
                )
            }

            it("original matrix preserved it's state") {
                a.numberOfColumns.should.equal(4)
                a.numberOfRows.should.equal(4)
            }
        }
    }

    describe("minor") {
        val a = matrix {
            +Row(3, 5, 0)
            +Row(2, -1, -7)
            +Row(6, -1, 5)
        }

        val b = a.submatrix(1, 0)

        it("determinant of b is 25") {
            b.determinant.should.equal(25.0)
        }

        it("minor of a at (1,0) is 25 too") {
            a.minor(1, 0).should.equal(25.0)
        }
    }

    describe("cofactor") {
        val a = matrix {
            +Row(3, 5, 0)
            +Row(2, -1, -7)
            +Row(6, -1, 5)
        }

        it("minor of a at (0,0) is -12") {
            a.minor(0, 0).should.equal(-12.0)
        }

        it("cofactor of a at (0,0) is -12 too") {
            a.cofactor(0, 0).should.equal(-12.0)
        }

        it("minor of a at (1,0) is 25") {
            a.minor(1, 0).should.equal(25.0)
        }

        it("cofactor of a at (1,0) is same as minor at same point, but sign reversed") {
            a.cofactor(1, 0).should.equal(-25.0)
        }
    }

})