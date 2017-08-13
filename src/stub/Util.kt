package stub

/**
 * Created by zeckson on 13/08/2017.
 */
@JsModule("util")
external object Util {
    fun inspect(obj: Any): String
    fun inspect(obj: Any, options: JSON): String
}