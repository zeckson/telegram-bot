package stub

external val process: Process

external interface Process {
    val argv: Array<String?>
}