package stub

import kotlin.js.RegExp

@JsModule("node-telegram-bot-api")
external class TelegramBot(token: String, config: dynamic) {

    fun onText(regExp: RegExp, handler: (message:dynamic, matches:Array<String>) -> Unit)
    fun on(action: dynamic, handler: (message:dynamic) -> Unit)
    fun sendMessage(chatId: dynamic, resp: String)
}
