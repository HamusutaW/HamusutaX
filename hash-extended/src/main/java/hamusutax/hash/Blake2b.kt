@file:Suppress("unused")
package hamusutax.hash

import org.bouncycastle.jcajce.provider.digest.Blake2b
import java.nio.charset.Charset

fun ByteArray.blake2b160(): ByteArray =
    Blake2b.Blake2b160().digest(this)

fun String.blake2b160(charset: Charset = Charsets.UTF_8) =
    toByteArray(charset).blake2b160()

fun ByteArray.blake2b256(): ByteArray =
    Blake2b.Blake2b256().digest(this)

fun String.blake2b256(charset: Charset = Charsets.UTF_8) =
    toByteArray(charset).blake2b256()

fun ByteArray.blake2b384(): ByteArray =
    Blake2b.Blake2b384().digest(this)

fun String.blake2b384(charset: Charset = Charsets.UTF_8) =
    toByteArray(charset).blake2b384()

fun ByteArray.blake2b512(): ByteArray =
    Blake2b.Blake2b512().digest(this)

fun String.blake2b512(charset: Charset = Charsets.UTF_8) =
    toByteArray(charset).blake2b512()
