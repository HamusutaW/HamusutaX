@file:Suppress("unused")
package hamusutax.hash

import org.bouncycastle.jcajce.provider.digest.Blake3.Blake3_256

fun ByteArray.blake3_256(): ByteArray =
    Blake3_256().digest(this)
