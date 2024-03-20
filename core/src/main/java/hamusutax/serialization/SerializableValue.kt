package hamusutax.serialization

import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.serializer

inline fun <reified T> Encoder.encodeSerializableValue(value: T) =
    encodeSerializableValue(serializer(), value)

inline fun <reified T> Decoder.decodeSerializableValue() =
    decodeSerializableValue<T>(serializer())
