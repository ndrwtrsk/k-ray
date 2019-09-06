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

        describe("subtraction") {
            it("should subtract color components") {
                (Color(0.9, 0.6, 0.75) - Color(0.7, 0.1, 0.25))
                    .should.equal(Color(0.2, 0.5, 0.5))
            }
        }

        describe("multiplication") {
            it("should multiply two colors together") {
                (Color(1, 0.2, 0.4) * Color(0.9, 1, 0.1))
                    .should.equal(Color(0.9, 0.2, 0.04))
            }
        }

        describe("scalar multiplication") {
            it("should multiply color by scalar") {
                (Color(0.2, 0.3, 0.4) * 2).should.equal(Color(0.4, 0.6, 0.8))
            }
        }

    }

})