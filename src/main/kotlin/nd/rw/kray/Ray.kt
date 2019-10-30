package nd.rw.kray

class Ray (val origin: Tuple, val direction: Tuple){

    fun position(t: Number): Tuple {
        return origin + direction * t
    }


}