package com.xuyang9978.ktqqbot.util

import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.entity.ContentType
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.URI

/**
 * @author XuYang
 * @date 2021/3/22 22:17
 */
object HttpClientUtil {
    private val logger: Logger = LoggerFactory.getLogger(HttpClientUtil::class.java)

    /**
     * 发送带参数的 get 请求
     * @param url       请求地址
     * @param params    参数
     */
    fun doGet(url: String, params: Map<String, Any>?): String {
        val httpClient: CloseableHttpClient = HttpClients.createDefault()
        var resultStr = ""
        var response: CloseableHttpResponse? = null

        try {
            val builder = URIBuilder(url)
            params?.forEach { builder.addParameter(it.key, it as String?) }
            val uri: URI = builder.build()
            val httpGet = HttpGet(uri)
            response = httpClient.execute(httpGet)
            if (response.statusLine.statusCode == 200) {
                resultStr = EntityUtils.toString(response.entity, "UTF-8")
            }
        } catch (e: Exception) {
            logger.error("发送 get 请求：$url 失败，错误信息：${e.message}")
        } finally {
            try {
                response?.close()
            } catch (e: IOException) {
                logger.error("关闭 response 失败，错误信息：${e.message}")
            }
        }
        return resultStr
    }

    /**
     * 发送不带参数的 get 请求
     * @param url   请求地址
     */
    fun doGet(url: String): String {
        return doGet(url, null)
    }

    /**
     * 发送 map 参数的 post 请求（表单请求）
     * @param url       请求地址
     * @param params    请求参数
     */
    fun doPost(url: String, params: Map<String, Any>?): String {
        val httpClient = HttpClients.createDefault()
        var resultStr = ""
        var response: CloseableHttpResponse? = null

        try {
            val httpPost = HttpPost(url)
            if (params != null) {
                val paramList: ArrayList<NameValuePair> = ArrayList(params.size)
                params.forEach { paramList.add(BasicNameValuePair(it.key, it as String?)) }
                // 模拟表单
                val entity = UrlEncodedFormEntity(paramList)
                httpPost.entity = entity
            }
            response = httpClient.execute(httpPost)
            EntityUtils.toString(response.entity, "UTF-8").also { resultStr = it }
        } catch (e: Exception) {
            logger.error("发送 post 请求：$url 失败，错误信息为：${e.message}")
        } finally {
            try {
                response?.close()
            } catch (e: IOException) {
                logger.error("关闭 response 失败，错误信息：${e.message}")
            }
        }
        return resultStr
    }

    /**
     * 发送 post 请求（json 格式参数）
     *
     * @param url   请求地址
     * @param json  请求参数
     */
    fun doPost(url: String?, json: String?): String? {
        val httpClient = HttpClients.createDefault()
        var response: CloseableHttpResponse? = null
        var resultString: String? = ""
        try {
            val httpPost = HttpPost(url)
            val entity = StringEntity(json, ContentType.APPLICATION_JSON)
            httpPost.entity = entity
            response = httpClient.execute(httpPost)
            EntityUtils.toString(response.entity, "utf-8").also { resultString = it }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                response?.close()
            } catch (e: IOException) {
                logger.error("关闭 response 失败，错误信息：${e.message}")
            }
        }
        return resultString
    }
}
