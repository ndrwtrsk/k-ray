import com.winterbe.expekt.should
import nd.rw.kray.Canvas
import nd.rw.kray.Color
import nd.rw.kray.PpmCanvasExporter
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class PpmCanvasExporterSpec : Spek({

    describe("constructing header for canvas of width 20 and height 80") {
        val canvas = Canvas(width = 20, height = 80)
        val exporter = PpmCanvasExporter()

        it("constructs ppm header") {
            val ppm = exporter.export(canvas)
            val lines = ppm.value.split("\n").take(3)
            lines[0].should.equal("P3")
            lines[1].should.equal("20 80")
            lines[2].should.equal("255")
        }
    }

    describe("constructing the PPM pixel data") {
        val canvas = Canvas(5, 3)
        val c1 = Color(1.5, 0, 0)
        val c2 = Color(0, 0.5, 0)
        val c3 = Color(-0.5, 0, 1)

        canvas.writePixel(0, 0, c1)
        canvas.writePixel(2, 1, c2)
        canvas.writePixel(4, 2, c3)

        val exporter = PpmCanvasExporter()

        val ppm = exporter.export(canvas)

        it("lines 4-6 are") {
            val ppmLines = ppm.value.split("\n")
            ppmLines[3] == "255 0 0 0 0 0 0 0 0 0 0 0 0 0 0"
            ppmLines[4] == "0 0 0 0 0 0 128 0 0 0 0 0 0 0 0"
            ppmLines[5] == "0 0 0 0 0 0 0 0 0 0 0 0 0 0 255"
        }

    }
})