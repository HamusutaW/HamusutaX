package hamusutax.ktor.clientapp.alist.model.payload


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdminMetaCreatePayload(
    val id: Int,
    val path: String,
    val password: String,
    @SerialName("p_sub") val pSub: Boolean,
    val write: Boolean,
    @SerialName("w_sub") val wSub: Boolean,
    val hide: String,
    @SerialName("h_sub") val hSub: Boolean,
    val readme: String,
    @SerialName("r_sub") val rSub: Boolean
)
