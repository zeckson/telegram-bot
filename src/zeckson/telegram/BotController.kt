package zeckson.telegram

import stub.TelegramBot

class BotController(private val bot: TelegramBot, private val actions: Array<Action>) {
    fun init() {
        for (action in actions) {
            bot.onText("/${action.alias} (.+)") {msg, matches ->
                val text = matches[0]
                console.log(text);
                action.handle(this, msg)
            }
        }
    }

    fun sendMessage(chatId: Int, text: String) = bot.sendMessage(chatId, text)
}