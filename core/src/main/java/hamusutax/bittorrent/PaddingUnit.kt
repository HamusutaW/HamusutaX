@file:Suppress("unused")
package hamusutax.bittorrent

import hamusutax.bittorrent.BitTorrent.Companion.piecesRoot
import hamusutax.number.isPowerOf

val Int.paddingUnit: ByteArray
    get() = when (this) {
        16 * 1024 -> PADDING_UNIT_16K
        32 * 1024 -> PADDING_UNIT_32K
        64 * 1024 -> PADDING_UNIT_64K
        128 * 1024 -> PADDING_UNIT_128K
        256 * 1024 -> PADDING_UNIT_256K
        512 * 1024 -> PADDING_UNIT_512K
        1024 * 1024 -> PADDING_UNIT_1M
        2 * 1024 * 1024 -> PADDING_UNIT_2M
        4 * 1024 * 1024 -> PADDING_UNIT_4M
        8 * 1024 * 1024 -> PADDING_UNIT_8M
        16 * 1024 * 1024 -> PADDING_UNIT_16M
        32 * 1024 * 1024 -> PADDING_UNIT_32M
        else -> {
            require(isPowerOf(2))
            getPaddingUnit(this)
        }
    }

private fun getPaddingUnit(pieceLength: Int) =
    List(pieceLength / BitTorrent.BLOCK_SIZE) { ByteArray(32) }
        .piecesRoot(pieceLength / BitTorrent.BLOCK_SIZE)

private val PADDING_UNIT_16K by lazy { getPaddingUnit(16 * 1024) }
private val PADDING_UNIT_32K by lazy { getPaddingUnit(32 * 1024) }
private val PADDING_UNIT_64K by lazy { getPaddingUnit(64 * 1024) }
private val PADDING_UNIT_128K by lazy { getPaddingUnit(128 * 1024) }
private val PADDING_UNIT_256K by lazy { getPaddingUnit(256 * 1024) }
private val PADDING_UNIT_512K by lazy { getPaddingUnit(512 * 1024) }
private val PADDING_UNIT_1M by lazy { getPaddingUnit(1024 * 1024) }
private val PADDING_UNIT_2M by lazy { getPaddingUnit(2 * 1024 * 1024) }
private val PADDING_UNIT_4M by lazy { getPaddingUnit(4 * 1024 * 1024) }
private val PADDING_UNIT_8M by lazy { getPaddingUnit(8 * 1024 * 1024) }
private val PADDING_UNIT_16M by lazy { getPaddingUnit(16 * 1024 * 1024) }
private val PADDING_UNIT_32M by lazy { getPaddingUnit(32 * 1024 * 1024) }
