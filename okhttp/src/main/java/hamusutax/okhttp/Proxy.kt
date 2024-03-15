@file:Suppress("unused")
package hamusutax.okhttp

import okhttp3.OkHttpClient
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.URL

fun OkHttpClient.Builder.httpProxy(socketAddress: InetSocketAddress) =
    proxy(Proxy(Proxy.Type.HTTP, socketAddress))

fun OkHttpClient.Builder.httpProxy(hostname: String, port: Int) =
    proxy(Proxy(Proxy.Type.HTTP, InetSocketAddress(hostname, port)))

fun OkHttpClient.Builder.socksProxy(socketAddress: InetSocketAddress) =
    proxy(Proxy(Proxy.Type.SOCKS, socketAddress))

fun OkHttpClient.Builder.socksProxy(hostname: String, port: Int) =
    proxy(Proxy(Proxy.Type.SOCKS, InetSocketAddress(hostname, port)))

fun String.toProxy(): Proxy {
    val url = URL(this)
    return Proxy(
        when (url.protocol.uppercase()) {
            "HTTP" -> Proxy.Type.HTTP
            "SOCKS5" -> Proxy.Type.SOCKS
            else -> throw IllegalArgumentException("Unknown proxy protocol!")
        },
        InetSocketAddress(url.host, url.port)
    )
}
