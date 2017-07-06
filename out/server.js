(function (_, Kotlin, $module$node_telegram_bot_api) {
  'use strict';
  var Error_0 = Kotlin.kotlin.Error;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var json = Kotlin.kotlin.js.json_pyyo18$;
  function main$lambda(closure$bot) {
    return function (msg, matches) {
      var chatId = msg.chat.id;
      var resp = matches[1];
      closure$bot.sendMessage(chatId, resp);
    };
  }
  function main$lambda_0(closure$bot) {
    return function (msg) {
      var chatId = msg.chat.id;
      closure$bot.sendMessage(chatId, 'Received your message: ' + msg);
    };
  }
  function main(args) {
    var tmp$, tmp$_0, tmp$_1;
    tmp$_0 = (tmp$ = process.env['API_KEY']) != null ? tmp$ : process.argv[2];
    if (tmp$_0 == null) {
      throw new Error_0('Token is not passed');
    }
    var token = tmp$_0;
    var bot = new $module$node_telegram_bot_api(typeof (tmp$_1 = token) === 'string' ? tmp$_1 : Kotlin.throwCCE(), json([to('polling', true)]));
    bot.onText(new RegExp('/echo (.+)'), main$lambda(bot));
    bot.on('message', main$lambda_0(bot));
  }
  _.main_kand9s$ = main;
  main([]);
  Kotlin.defineModule('server', _);
  return _;
}(module.exports, require('kotlin'), require('node-telegram-bot-api')));
