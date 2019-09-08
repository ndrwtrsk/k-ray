package nd.rw.kray

interface CanvasExporter<EXPORT_TYPE> {

    fun export(canvas: Canvas): EXPORT_TYPE

}

class PpmCanvasExporter : CanvasExporter<PpmString> {
    override fun export(canvas: Canvas): PpmString {
        val builder = StringBuilder()
        builder.appendln("P3")
            .appendln("${canvas.width} ${canvas.height}")
            .appendln(255)

        return PpmString(builder.toString())
    }
}

data class PpmString(val value: String)