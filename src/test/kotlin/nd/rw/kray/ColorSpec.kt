package nd.rw.kray

import com.winterbe.expekt.should

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class ColorSpec : Spek({

    describe("constructing") {
        val color = Color(-0.5, 0.4, 1.7)
        it("should create color") {
            color.red.should.equal(-0.5)
            color.green.should.equal(0.4)
            color.blue.should.equal(1.7)
        }
    }

    describe("operations") {
        describe("addition") {
            it("should add color components") {
                (Color(0.9, 0.6, 0.75) + Color(0.7, 0.1, 0.25))
                    .should.equal(Color(1.6, 0.7, 1.0))
            }
        }
    }

})