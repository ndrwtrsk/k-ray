import com.winterbe.expekt.should
import nd.rw.kray.*
import nd.rw.kray.Matrix.Companion.scalingMatrix
import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector
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

    describe("intersections") {
        describe("ray within a world") {
            val w = defaultWorld
            val ray = Ray(point(0,0,-5), vector(0,0,1))

            val intersections = w.intersect(ray)

            it("computes") {
                intersections.size.should.equal(4)
                intersections[0].t.should.equal(4.0)
                intersections[1].t.should.equal(4.5)
                intersections[2].t.should.equal(5.5)
                intersections[3].t.should.equal(6.0)
            }
        }

    }

})