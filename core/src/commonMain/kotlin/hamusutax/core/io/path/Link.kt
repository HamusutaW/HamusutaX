@file:Suppress("UNUSED")
package hamusutax.core.io.path

import kotlinx.io.files.Path

expect fun Path.createLinkPointingTo(target: Path)
