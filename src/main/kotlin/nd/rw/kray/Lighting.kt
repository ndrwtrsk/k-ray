package nd.rw.kray

import kotlin.math.pow

fun lighting(
    material: Material,
    light: PointLight,
    position: Tuple,
    eyeVector: Tuple,
    normalVector: Tuple
): Color {
    val combinedEffectiveColor = material.color * light.intensity
    val lightDirection = (light.position - position).normalize
    val ambient = combinedEffectiveColor * material.ambient
    val lightDotNormal = lightDirection.dot(normalVector)

    var diffuse: Color
    var specular: Color

    if (lightDotNormal < 0) {
        diffuse = Color.black
        specular = Color.black
    } else {
        diffuse = combinedEffectiveColor * material.diffuse * lightDotNormal
        val reflectVector = -(lightDirection).reflect(normalVector)
        val reflectionDotEye = reflectVector.dot(eyeVector)
        specular = if (reflectionDotEye <= 0) {
            Color.black
        } else {
            val factor = reflectionDotEye.pow(material.shininess)
            light.intensity * material.specular * factor
        }
    }
    return ambient + diffuse + specular
}