package nd.rw.kray

import nd.rw.kray.Matrix.Companion.identity
import nd.rw.kray.Tuple.Companion.point

class Sphere(val origin: Tuple = point(0,0,0)) {

    var transformation = identity()
}