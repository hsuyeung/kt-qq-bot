package run.xuyang.ktqqbot.plugin

import net.lz1998.pbbot.alias.GroupMessageEvent
import net.lz1998.pbbot.bot.Bot
import net.lz1998.pbbot.bot.BotPlugin
import org.springframework.stereotype.Component

/**
 * 复读插件
 *
 * @author XuYang
 * @date 2021/3/21 21:52
 */
@Component
class ReReadPlugin : BotPlugin() {
    /**
     * 收到群消息时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onGroupMessage(bot: Bot, event: GroupMessageEvent): Int {
        bot.sendGroupMsg(event.groupId, event.messageList, false)
        return super.onGroupMessage(bot, event)
    }
}
