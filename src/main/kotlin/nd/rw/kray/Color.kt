package nd.rw.kray

class Color (val red: Double, val green: Double, val blue: Double) {

    constructor(red: Number, green: Number, blue: Number)
            : this(red.toDouble(), green.toDouble(), blue.toDouble())
}