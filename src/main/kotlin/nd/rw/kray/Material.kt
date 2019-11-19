package nd.rw.kray

class Material(
    val ambient: Double = 0.1,
    val diffuse: Double = 0.9,
    val specular: Double = 0.9,
    val shininess: Double = 200.0,
    val color: Color = Color(1, 1, 1)
)