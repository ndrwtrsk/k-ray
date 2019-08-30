package nd.rw.kray

import spock.lang.Specification

import static nd.rw.kray.Tuple.point
import static nd.rw.kray.Tuple.vector

class TupleUT extends Specification {

    def 'should create tuple as a point'() {
        given:
            def point = point(4.3, 4.2, 3.1)

        expect:
            point.isPoint()
            !point.isVector()
    }

    def 'should create tuple as a vector'() {
        given:
            def vector = vector(1, 2, 3)

        expect:
            vector.isVector()
            !vector.isPoint()
    }

    def 'should test correctly for equality and inequality'() {
        given:
            def vector1 = vector(1.1, 2, 3)
            def vector2 = vector(4, 5, 6)
            def vector3 = vector(1.1, 2, 3)

        expect:
            vector1 != vector2
            vector1 == vector3
    }

    def 'adding two tuples'() {
        given:
            def tuple1 = new Tuple(3, -2, 5, 1)
            def tuple2 = new Tuple(-2, 3, 1, 0)


        expect:
            tuple1 + tuple2 == new Tuple(1, 1, 6, 1)
    }

    def 'subtracting two points should result in a vector'() {
        given:
            def p1 = point(3, 2, 1)
            def p2 = point(5, 6, 7)

        expect:
            p1 - p2 == vector(-2, -4, -6)
    }

    def 'subtracting a vector from a point should result in a point'() {
        given:
            def p = point(3, 2, 1)
            def v = vector(5, 6, 7)

        expect:
            p - v == point(-2, -4, -6)
    }

    def 'subtracting two vectors should result in a vector'() {
        given:
            def v1 = vector(3,2,1)
            def v2 = vector(5,6,7)

        expect:
            v1 - v2 == vector(-2, -4, -6)
    }
}
