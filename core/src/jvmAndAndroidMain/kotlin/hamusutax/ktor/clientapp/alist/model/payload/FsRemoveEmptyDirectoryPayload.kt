package hamusutax.ktor.clientapp.alist.model.payload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FsRemoveEmptyDirectoryPayload(
    @SerialName("src_dir") val srcDir: String
)
