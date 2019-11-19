package nd.rw.kray

import com.winterbe.expekt.should
import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.math.sqrt

class LightingSpec : Spek({

    var material = Material()
    var position = point(0,0,0)

    beforeEachTest {
        material = Material()
        position = point(0, 0, 0)
    }

    describe("lighting") {
        describe("with the eye between the light and the surface") {
            val eyeVector = vector(0, 0, -1)
            val normalVector = vector(0, 0, -1)
            val light = PointLight(point(0, 0, -10), Color(1, 1, 1))

            val result = lighting(material, light, position, eyeVector, normalVector)

            it("produces ambient, diffuse and specular at full strength") {
                result.should.equal(Color(1.9, 1.9, 1.9))
            }
        }

        describe("with the eye between the light and the surface, eye offset 45 deg") {
            val eyeVector = vector(0, sqrt(2.0)/2, -sqrt(2.0)/2)
            val normalVector = vector(0, 0, -1)
            val light = PointLight(point(0, 0, -10), Color(1, 1, 1))

            val result = lighting(material, light, position, eyeVector, normalVector)

            it("produces color with specular fallen to 0") {
                result.should.equal(Color(1.0, 1.0, 1.0))
            }
        }

        describe("with eye opposite surface, light offset 45 deg") {
            val eyeVector = vector(0, 0, -1)
            val normalVector = vector(0, 0, -1)
            val light = PointLight(point(0, 10, -10), Color(1, 1, 1))

            val result = lighting(material, light, position, eyeVector, normalVector)

            it("color is less intense") {
                result.should.equal(Color(0.7364, 0.7364, 0.7364))
            }
        }

        describe("with eye in the path of the reflection vector") {
            val eyeVector = vector(0, -sqrt(2.0)/2, -sqrt(2.0)/2)
            val normalVector = vector(0, 0, -1)
            val light = PointLight(point(0, 10, -10), Color(1, 1, 1))

            val result = lighting(material, light, position, eyeVector, normalVector)

            it("color is less intense") {
                result.should.equal(Color(1.6364, 1.6364, 1.6364))
            }
        }

        describe("with the light behind the surface") {
            val eyeVector = vector(0,0,-1)
            val normalVector = vector(0, 0, -1)
            val light = PointLight(point(0, 0, 10), Color(1, 1, 1))

            val result = lighting(material, light, position, eyeVector, normalVector)

            it("color is less intense") {
                result.should.equal(Color(0.1, 0.1, 0.1))
            }
        }


    }

})