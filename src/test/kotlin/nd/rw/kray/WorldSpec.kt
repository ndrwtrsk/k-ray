import com.winterbe.expekt.should
import nd.rw.kray.Color
import nd.rw.kray.Material
import nd.rw.kray.Matrix.Companion.scalingMatrix
import nd.rw.kray.PointLight
import nd.rw.kray.Sphere
import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.World.Companion.defaultWorld
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


class WorldSpec : Spek({

    describe("default world") {
        val light = PointLight(point(-10, -10, -10), Color.white)
        val s1 = Sphere(
            material = Material(
                color = Color(0.8, 1.0, 0.6),
                diffuse = 0.7,
                specular = 0.2
            )
        )
        val s2 = Sphere(transformation = scalingMatrix(0.5, 0.5, 0.5))

        val defaultWorld = defaultWorld

        it("is constructed") {
            defaultWorld.pointLight.should.equal(light)
            defaultWorld.contains(s1).should.equal(true)
            defaultWorld.contains(s2).should.equal(true)
        }
    }

})