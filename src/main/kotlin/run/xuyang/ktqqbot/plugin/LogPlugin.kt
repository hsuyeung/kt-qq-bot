package run.xuyang.ktqqbot.plugin

import net.lz1998.pbbot.alias.*
import net.lz1998.pbbot.bot.Bot
import net.lz1998.pbbot.bot.BotPlugin
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * TODO:后期可以讲这些所有日志在数据库里存起来
 * 日志记录插件
 *
 * @author XuYang
 * @date 2021/3/15 16:54
 */
@Component
class LogPlugin : BotPlugin() {
    val logger: Logger = LoggerFactory.getLogger(LogPlugin::class.java);

    /**
     * 好友添加时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onFriendAddNotice(bot: Bot, event: FriendAddNoticeEvent): Int {
        logger.info("收到 ${event.userId} 的添加好友请求！")
        return super.onFriendAddNotice(bot, event)
    }

    /**
     * 好友撤回消息时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onFriendRecallNotice(bot: Bot, event: FriendRecallNoticeEvent): Int {
        logger.info("好友 ${event.userId} 撤回消息，内容为: ${bot.getMsg(event.messageId)}")
        return super.onFriendRecallNotice(bot, event)
    }

    /**
     * 加好友请求时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onFriendRequest(bot: Bot, event: FriendRequestEvent): Int {
        logger.info("收到添加QQ: ${event.userId} 为好友的请求！")
        return super.onFriendRequest(bot, event)
    }

    /**
     * 群管理员变动时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onGroupAdminNotice(bot: Bot, event: GroupAdminNoticeEvent): Int {
        logger.info("")
        return super.onGroupAdminNotice(bot, event)
    }

    /**
     * 群禁言时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onGroupBanNotice(bot: Bot, event: GroupBanNoticeEvent): Int {
        logger.info("管理员开启了群禁言！")
        return super.onGroupBanNotice(bot, event)
    }

    /**
     * 群成员减少时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onGroupDecreaseNotice(bot: Bot, event: GroupDecreaseNoticeEvent): Int {
        logger.info("成员 ${event.userId} 退出群聊 ${event.groupId}")
        return super.onGroupDecreaseNotice(bot, event)
    }

    /**
     * 群成员增加时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onGroupIncreaseNotice(bot: Bot, event: GroupIncreaseNoticeEvent): Int {
        logger.info("QQ ${event.userId} 加入群聊 ${event.groupId}")
        return super.onGroupIncreaseNotice(bot, event)
    }

    /**
     * 收到群消息时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onGroupMessage(bot: Bot, event: GroupMessageEvent): Int {
        logger.info("收到 ${event.groupId} 群消息，内容为：${event.rawMessage}")
        return super.onGroupMessage(bot, event)
    }

    /**
     * 群撤回消息时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onGroupRecallNotice(bot: Bot, event: GroupRecallNoticeEvent): Int {
        logger.info("${event.groupId} 群撤回消息，内容为：${bot.getMsg(event.messageId)}")
        return super.onGroupRecallNotice(bot, event)
    }

    /**
     * 加群请求/邀请时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onGroupRequest(bot: Bot, event: GroupRequestEvent): Int {
        logger.info("收到加群/邀请加群请求！")
        return super.onGroupRequest(bot, event)
    }

    /**
     * 群内有文件上传时调用此方法
     * 仅群文件上传表现为事件，好友发送文件在 酷Q 中没有独立的事件，而是直接表现为好友消息，请注意在编写业务逻辑时进行判断。
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onGroupUploadNotice(bot: Bot, event: GroupUploadNoticeEvent): Int {
        logger.info("${event.groupId} 群上传了群文件！")
        return super.onGroupUploadNotice(bot, event)
    }

    /**
     * 收到私聊消息时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onPrivateMessage(bot: Bot, event: PrivateMessageEvent): Int {
        logger.info("收到来自QQ ${event.userId} 的私聊消息，内容为：${event.rawMessage}")
        return super.onPrivateMessage(bot, event)
    }
}
