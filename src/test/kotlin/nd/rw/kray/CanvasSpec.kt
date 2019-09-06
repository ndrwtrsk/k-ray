package nd.rw.kray

import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class CanvasSpec : Spek({

    describe("creating a canvas") {
        val c = Canvas(10, 20)

        it("width should be 10, height should be 20") {
            c.width.should.equal(10)
            c.height.should.equal(20)
        }

        it("every pixel is black Color(0,0,0)") {
            c.pixelGrid.forEach { row ->
                row.forEach { pixel ->
                    pixel.should.equal(Color(0,0,0))
                }
            }
        }
    }

})