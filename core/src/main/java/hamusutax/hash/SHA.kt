@file:Suppress("unused")
package hamusutax.hash

import java.nio.charset.Charset
import java.security.MessageDigest

fun ByteArray.sha1(): ByteArray =
    MessageDigest.getInstance("SHA-1").digest(this)

fun ByteArray.sha256(): ByteArray =
    MessageDigest.getInstance("SHA-256").digest(this)

fun ByteArray.sha384(): ByteArray =
    MessageDigest.getInstance("SHA-384").digest(this)

fun ByteArray.sha512(): ByteArray =
    MessageDigest.getInstance("SHA-512").digest(this)

fun ByteArray.sha512_256(): ByteArray =
    MessageDigest.getInstance("SHA-512/256").digest(this)

fun ByteArray.sha3_384(): ByteArray =
    MessageDigest.getInstance("SHA3-384").digest(this)

fun ByteArray.sha3_512(): ByteArray =
    MessageDigest.getInstance("SHA3-512").digest(this)

fun String.sha1(charset: Charset = Charsets.UTF_8) =
    toByteArray(charset).sha1()

fun String.sha256(charset: Charset = Charsets.UTF_8) =
    toByteArray(charset).sha256()

fun String.sha384(charset: Charset = Charsets.UTF_8) =
    toByteArray(charset).sha384()

fun String.sha512(charset: Charset = Charsets.UTF_8) =
    toByteArray(charset).sha512()

fun String.sha512_256(charset: Charset = Charsets.UTF_8) =
    toByteArray(charset).sha512_256()

fun String.sha3_384(charset: Charset = Charsets.UTF_8) =
    toByteArray(charset).sha3_384()

fun String.sha3_512(charset: Charset = Charsets.UTF_8) =
    toByteArray(charset).sha3_512()
