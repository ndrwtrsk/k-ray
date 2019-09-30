import com.winterbe.expekt.should
import nd.rw.kray.Matrix.Companion.of
import nd.rw.kray.Matrix.Companion.row
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


class MatrixSpec : Spek({

    describe("constructing matricies") {

        describe("of") {
            val matrix = of(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9))
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

})