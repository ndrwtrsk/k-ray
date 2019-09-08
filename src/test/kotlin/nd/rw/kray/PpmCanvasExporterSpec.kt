import com.winterbe.expekt.should
import nd.rw.kray.Canvas
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
})