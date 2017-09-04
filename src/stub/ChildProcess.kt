@file:JsModule("child_process")
package stub

import kotlin.js.Json

external fun exec(command: String, options: Json, callback: ((error: Error?, stdout: String, stderr: String) -> Unit)? = definedExternally)
