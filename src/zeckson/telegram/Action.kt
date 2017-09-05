package zeckson.telegram

import stub.Message
import kotlin.js.Promise

abstract class Action(val alias: String) {
    fun canHandle(command: String) = alias.equals(command, true)
    abstract fun handle(controller: BotController, msg: Message):Promise<Error>
}