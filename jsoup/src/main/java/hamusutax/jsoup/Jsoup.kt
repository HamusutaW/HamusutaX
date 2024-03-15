@file:Suppress("unused")
package hamusutax.jsoup

import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.nodes.TextNode

/**
 * 使 XPath 支持直接获取节点文本
 */
fun Element.xpathText(xpath: String): String {
    // 获取属性值
    Regex("^(.+)/+@([^/@]+)$").find(xpath)?.let { matchResult ->
        val (elementXPath, attributeKey) = matchResult.destructured
        return selectXpath(elementXPath).attr(attributeKey)
    }
    return selectXpath(xpath, TextNode::class.java).first().text()
}

fun Response.asJsoup(): Document =
    Jsoup.parse(body.string(), request.url.toString())
