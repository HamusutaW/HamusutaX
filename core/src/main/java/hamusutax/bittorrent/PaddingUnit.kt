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
            _getPaddingUnit(this)
        }
    }

private fun _getPaddingUnit(pieceLength: Int) =
    List(pieceLength / BitTorrent.BLOCK_SIZE) { ByteArray(32) }
        .piecesRoot(pieceLength / BitTorrent.BLOCK_SIZE)

private val PADDING_UNIT_16K by lazy { _getPaddingUnit(16 * 1024) }
private val PADDING_UNIT_32K by lazy { _getPaddingUnit(32 * 1024) }
private val PADDING_UNIT_64K by lazy { _getPaddingUnit(64 * 1024) }
private val PADDING_UNIT_128K by lazy { _getPaddingUnit(128 * 1024) }
private val PADDING_UNIT_256K by lazy { _getPaddingUnit(256 * 1024) }
private val PADDING_UNIT_512K by lazy { _getPaddingUnit(512 * 1024) }
private val PADDING_UNIT_1M by lazy { _getPaddingUnit(1024 * 1024) }
private val PADDING_UNIT_2M by lazy { _getPaddingUnit(2 * 1024 * 1024) }
private val PADDING_UNIT_4M by lazy { _getPaddingUnit(4 * 1024 * 1024) }
private val PADDING_UNIT_8M by lazy { _getPaddingUnit(8 * 1024 * 1024) }
private val PADDING_UNIT_16M by lazy { _getPaddingUnit(16 * 1024 * 1024) }
private val PADDING_UNIT_32M by lazy { _getPaddingUnit(32 * 1024 * 1024) }
