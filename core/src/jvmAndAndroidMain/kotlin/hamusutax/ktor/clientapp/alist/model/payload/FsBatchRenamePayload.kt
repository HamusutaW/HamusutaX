package hamusutax.ktor.clientapp.alist.model.payload


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FsBatchRenamePayload(
    @SerialName("src_dir") val srcDir: String,
    @SerialName("rename_objects") val renameObjects: List<RenameObject>
) {
    @Serializable
    data class RenameObject(
        @SerialName("src_name") val srcName: String,
        @SerialName("new_name") val newName: String
    )
}
