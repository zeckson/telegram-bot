package stub

import kotlin.js.Json

external val process: Process

external interface Process {
    val argv: Array<String?>
    val env: Json
}