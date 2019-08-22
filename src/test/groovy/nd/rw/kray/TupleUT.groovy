package nd.rw.kray

import spock.lang.Specification

class TupleUT extends Specification {

    def 'should create point and tuple'() {
        given:
            def point = Tuple.point(4.3, 4.2, 3.1)

        expect:
            point.isPoint()
            !point.isVector()
    }

}
