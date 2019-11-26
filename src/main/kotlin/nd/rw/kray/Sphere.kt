package nd.rw.kray

import nd.rw.kray.Matrix.Companion.identityMatrix
import nd.rw.kray.Tuple.Companion.point
import nd.rw.kray.Tuple.Companion.vector

data class Sphere(
    val origin: Tuple = point(0, 0, 0),
    val transformation: Matrix = identityMatrix(),
    val material: Material = Material()
) {

    fun normalAt(worldPoint: Tuple): Tuple {
        val invertedTransformation = transformation.inverted
        val objectPoint = invertedTransformation * worldPoint
        val objectNormal = objectPoint - point(0, 0, 0)
        val worldNormal = invertedTransformation.transpose * objectNormal
        return vector(worldNormal.x, worldNormal.y, worldNormal.z).normalize
    }
}