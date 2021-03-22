package run.xuyang.ktqqbot.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

/**
 * @author XuYang
 * @date 2021/3/22 23:07
 */
@Configuration
class JacksonConfig {

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper::class)
    fun customObjectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper? {
        val om = builder.build<ObjectMapper>()
        // 解决反序列化 LocalDateTime 的错误
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        om.registerModule(JavaTimeModule())
        return om
    }
}
