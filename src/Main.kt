import stub.TelegramBot
import stub.Util
import stub.process
import kotlin.js.RegExp
import kotlin.js.json

val ZECKSON_ID = "76694824";

fun main(args: Array<String>) {
    // replace the value below with the Telegram token you receive from @BotFather
    val token = process.env.get("API_KEY") ?: process.argv[2] ?: throw Error("Token is not passed")

    // Create a bot that uses `polling` to fetch new updates
    val bot = TelegramBot(token as String, json("polling" to true))

    // Matches "/echo [whatever]"
    bot.onText(RegExp("/echo (.+)")) { msg, matches ->
        // `msg` is the received Message from Telegram
        // `match` is the result of executing the regexp above on the text content
        // of the message

        val chatId = msg.chat.id
        val resp = matches[1]

        // send back the matched "whatever" to the chat
        bot.sendMessage(chatId, resp)
    }

    bot.onText(RegExp("/show (.+)")) { msg, matches ->
        // `msg` is the received Message from Telegram
        // `match` is the result of executing the regexp above on the text content
        // of the message

        val chatId = msg.chat.id
        val resp = matches[1]

        // send back the matched "whatever" to the chat
        bot.sendMessage(chatId, resp, json("reply_markup" to
                json("inline_keyboard" to
                        arrayOf(
                                arrayOf(
                                        json("text" to "one",
                                                "url" to "https://google.com")
                                )
                        )
                )))
    }

    // Listen for any kind of message. There are different kinds of
    // messages.
    bot.on("message") { msg ->
        val chatId = msg.chat.id

        // send a message to the chat acknowledging receipt of their message
        bot.sendMessage(chatId, "Received your message: $msg")
        console.log(Util.inspect(msg))
    }

}