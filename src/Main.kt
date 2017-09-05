import stub.*
import zeckson.telegram.Action
import zeckson.telegram.BotController
import kotlin.js.Promise
import kotlin.js.RegExp
import kotlin.js.json

val ZECKSON_ID = 76694824;

private val MAX_MESSAGE_LENGTH = 4096

fun main(args: Array<String>) {
    // replace the value below with the Telegram token you receive from @BotFather
    val token = process.env.get("API_KEY") ?: process.argv[2] ?: throw Error("Token is not passed")

    // Create a bot that uses `polling` to fetch new updates
    val bot = TelegramBot(token as String, json("polling" to true))

    val controller = BotController(bot, emptyArray<Action>())
    controller.init()

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

    bot.onText(RegExp("/exec (.+)")) { msg, matches ->
        // `msg` is the received Message from Telegram
        // `match` is the result of executing the regexp above on the text content
        // of the message

        val userId = msg.from?.id
        val chatId = msg.chat.id
        if (userId != ZECKSON_ID) {
            bot.sendMessage(chatId, "You are not authorized to exec commands")
            return@onText;
        }
        val command = matches[1]
        console.log("Got command: $command")

        fun shCodeBlock(content: Any) = "```sh\n$content```"

        fun sendText(text: String) {
            if (text.isNotEmpty()) {
                if (text.length > MAX_MESSAGE_LENGTH) {
                    // TODO: Buffer and split by \n if possible
                    for (chunk in IntProgression.fromClosedRange(0, text.length, MAX_MESSAGE_LENGTH)) {
                        sendText(text.substring(chunk, chunk + MAX_MESSAGE_LENGTH))
                    }
                } else {
                    val asMarkdown = json("parse_mode" to "Markdown")
                    bot.sendMessage(chatId, shCodeBlock(text), asMarkdown)
                }
            }
        }

        if (!command.isEmpty()) {
            exec(command, json()) { error: Error?, stdin: String, stderr: String ->
                if (error != null) {
                    bot.sendMessage(chatId, "Error: ${error.message}")
                } else {
                    console.log("Got response", stdin, stderr)
                    sendText(stdin)
                    sendText(stderr)
                }
            }
        } else {
            // TODO: send usage
        }
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
        // send a message to the chat acknowledging receipt of their message
        console.log(Util.inspect(msg))
    }

    console.log("All set up! Ready to receive messages...")
}