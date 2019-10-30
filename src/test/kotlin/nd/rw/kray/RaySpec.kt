import com.winterbe.expekt.should
import nd.rw.kray.Ray
import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class RaySpec : Spek({

    describe("creating a ray") {

        val origin = point(1,2,3)
        val direction = vector(4,5,6)

        val ray = Ray(origin, direction)

        it("querying ray") {
            ray.origin.should.equal(point(1,2,3))
            ray.direction.should.equal(vector(4,5,6))
        }
    }

})