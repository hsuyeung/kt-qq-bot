package run.xuyang.ktqqbot.plugin

import com.fasterxml.jackson.databind.ObjectMapper
import net.lz1998.pbbot.alias.GroupMessageEvent
import net.lz1998.pbbot.bot.Bot
import net.lz1998.pbbot.bot.BotPlugin
import org.springframework.stereotype.Component
import run.xuyang.ktqqbot.entity.Poem
import run.xuyang.ktqqbot.util.HttpClientUtil
import javax.annotation.Resource

/**
 * 随机一句古诗词
 * https://v1.jinrishici.com/all.json
 *
 * @author XuYang
 * @date 2021/3/21 23:07
 */
@Component
class RandomPoemPlugin : BotPlugin() {
    @Resource
    private lateinit var objectMapper: ObjectMapper

    /**
     * 收到群消息时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    override fun onGroupMessage(bot: Bot, event: GroupMessageEvent): Int {
        if (event.rawMessage.startsWith("bot randomPoem")
            || event.rawMessage.contains("诗词")
            || event.rawMessage.contains("古诗")) {
            val getRes = HttpClientUtil.doGet("https://v1.jinrishici.com/all.json")
            val poem = objectMapper.readValue(getRes, Poem::class.java)
            bot.sendGroupMsg(event.groupId, "${poem.content}--${poem.author}《${poem.origin}》", false)
        }
        return super.onGroupMessage(bot, event)
    }
}
