package nd.rw.kray

class Canvas(val width: Int, val height: Int) {

    val pixelGrid: List<List<Color>> = MutableList(height) { MutableList(width) { Color(0, 0, 0) } }



}