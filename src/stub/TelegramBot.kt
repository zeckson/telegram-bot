package stub

import kotlin.js.Json
import kotlin.js.Promise
import kotlin.js.RegExp

typealias Handler = (message: Message, matches: Array<String>) -> Unit;

external interface MessageOption {
    var parse_mode: String? get() = definedExternally; set(value) = definedExternally
    var reply_markup: Json? get() = definedExternally; set(value) = definedExternally
}

@JsModule("node-telegram-bot-api")
external class TelegramBot(token: String, config: Json) {
    fun onText(regExp: RegExp, handler: Handler)
    fun onText(string: String, handler: Handler)
    fun on(action: String, handler: (message: Message) -> Unit)
    fun sendMessage(chatId: Int, resp: String): Promise<Error>
    fun sendMessage(chatId: Int, resp: String, options: Json?): Promise<Error>
}

external interface Message {
    val message_id: Int                         // Unique message identifier inside this chat
    val from: User?                             // Optional. Sender, can be empty for messages sent to channels
    val date: Int                               // Date the message was sent in Unix time
    val chat: Chat                              // Conversation the message belongs to
    val forward_from: User                      // Optional. For forwarded messages, sender of the original message
    val forward_from_chat: Chat?                // Optional. For messages forwarded from a channel, information about the original channel
    val forward_from_message_id: Int?           // Optional. For forwarded channel posts, identifier of the original message in the channel
    val forward_date: Int?                      // Optional. For forwarded messages, date the original message was sent in Unix time
    val reply_to_message: Message?              // Optional. For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
    val edit_date: Int?                         // Optional. Date the message was last edited in Unix time
    val text: String?                           // Optional. For text messages, the actual UTF-8 text of the message, 0-4096 characters.
    val entities: Array<MessageEntity>?         // of MessageEntity	Optional. For text messages, special entities like usernames, URLs, bot commands, etc. that appear in the text
    val audio: Audio?                           // Optional. Message is an audio file, information about the file
    val document: Document?                     // Optional. Message is a general file, information about the file
    val game: Game?                             // Optional. Message is a game, information about the game. More about games »
    val photo: Array<PhotoSize>                 // of PhotoSize	Optional. Message is a photo, available sizes of the photo
    val sticker: Sticker?                       // Optional. Message is a sticker, information about the sticker
    val video: Video?                           // Optional. Message is a video, information about the video
    val voice: Voice?                           // Optional. Message is a voice message, information about the file
    val video_note: VideoNote?                  // Optional. Message is a video note, information about the video message
    val new_chat_members: Array<User>?          // of User	Optional. New members that were added to the group or supergroup and information about them (the bot itself may be one of these members)
    val caption: String?                        // Optional. Caption for the document, photo or video, 0-200 characters
    val contact: Contact?                       // Optional. Message is a shared contact, information about the contact
    val location: Location?                     // Optional. Message is a shared location, information about the location
    val venue: Venue?                           // Optional. Message is a venue, information about the venue
    val new_chat_member: User?                  // Optional. A new member was added to the group, information about them (this member may be the bot itself)
    val left_chat_member: User?                 // Optional. A member was removed from the group, information about them (this member may be the bot itself)
    val new_chat_title: String?                 // Optional. A chat title was changed to this value
    val new_chat_photo: Array<PhotoSize>?       // of PhotoSize	Optional. A chat photo was change to this value
    val delete_chat_photo: Boolean?             // Optional. Service message: the chat photo was deleted
    val group_chat_created: Boolean?            // Optional. Service message: the group has been created
    val supergroup_chat_created: Boolean?       // Optional. Service message: the supergroup has been created. This field can‘t be received in a message coming through updates, because bot can’t be a member of a supergroup when it is created. It can only be found in reply_to_message if someone replies to a very first message in a directly created supergroup.
    val channel_chat_created: Boolean?          // Optional. Service message: the channel has been created. This field can‘t be received in a message coming through updates, because bot can’t be a member of a channel when it is created. It can only be found in reply_to_message if someone replies to a very first message in a channel.
    val migrate_to_chat_id: Int?                // Optional. The group has been migrated to a supergroup with the specified identifier. This number may be greater than 32 bits and some programming languages may have difficulty/silent defects in interpreting it. But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type are safe for storing this identifier.
    val migrate_from_chat_id: Int?              // Optional. The supergroup has been migrated from a group with the specified identifier. This number may be greater than 32 bits and some programming languages may have difficulty/silent defects in interpreting it. But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type are safe for storing this identifier.
    val pinned_message: Message?                // Optional. Specified message was pinned. Note that the Message object in this field will not contain further reply_to_message fields even if it is itself a reply.
    val invoice: Invoice?                       // Optional. Message is an invoice for a payment, information about the invoice. More about payments »
    val successful_payment: SuccessfulPayment?  // Optional. Message is a service message about a successful payment, information about the payment. More about payments »
}

external interface Chat {
    val id: Int                                     //Unique identifier for this chat. This number may be greater than 32 bits and some programming languages may have difficulty/silent defects in interpreting it. But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type are safe for storing this identifier.
    val type: String                                //Type of chat, can be either “private”, “group”, “supergroup” or “channel”
    val title: String?                              //Optional. Title, for supergroups, channels and group chats
    val username: String?                           //Optional. Username, for private chats, supergroups and channels if available
    val first_name: String?                         //Optional. First name of the other party in a private chat
    val last_name: String?                          //Optional. Last name of the other party in a private chat
    val all_members_are_administrators: Boolean?    //Optional. True if a group has ‘All Members Are Admins’ enabled.
    val photo: ChatPhoto?                           //Optional. Chat photo. Returned only in getChat.
    val description: String?                        //Optional. Description, for supergroups and channel chats. Returned only in getChat.
    val invite_link: String?                        //Optional. Chat invite link, for supergroups and channel chats. Returned only in getChat.
}

external interface Location {

}

external interface SuccessfulPayment {

}

external interface Invoice {

}

external interface Venue {

}

external interface Contact {

}

external interface VideoNote {

}

external interface Voice {

}

external interface Video {

}

external interface Sticker {

}

external interface PhotoSize {

}

external interface Game {

}

external interface Audio {

}

external interface Document {

}

external interface MessageEntity {

}

external interface ChatPhoto {

}

external interface User {
    val id:Int	                // Unique identifier for this user or bot
    val is_bot:Boolean	        // True, if this user is a bot
    val first_name:String	    // User‘s or bot’s first name
    val last_name:String?	    // Optional. User‘s or bot’s last name
    val username:String?	        // Optional. User‘s or bot’s username
    val language_code:String?    // Optional. IETF language tag of the user's language

}
