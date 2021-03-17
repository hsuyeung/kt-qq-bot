package run.xuyang.ktqqbot.interceptor

import lombok.SneakyThrows
import net.lz1998.pbbot.handler.BotSessionInterceptor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketSession

/**
 * websocket 连接的拦截器，如果需要禁止某些机器人（QQ 号）连接，则在这里处理
 *
 * @author XuYang
 * @date 2021/3/15 17:01
 */
@Primary
@Component
class WebSocketConnectionInterceptor : BotSessionInterceptor() {
    val logger: Logger = LoggerFactory.getLogger(WebSocketConnectionInterceptor::class.java)

    @SneakyThrows
    @Override
    override fun checkSession(session: WebSocketSession): Boolean {
        val httpHeaders = session.handshakeHeaders
        val botId = httpHeaders.getFirst("x-self-id")
        logger.info("headers: $httpHeaders")
        logger.info("new connection: $botId")
//        if ("123" == botId) {
//            logger.info("QQ: 123 禁止连接！")
//            session.close()
//            return false
//        }
        return true
    }
}
