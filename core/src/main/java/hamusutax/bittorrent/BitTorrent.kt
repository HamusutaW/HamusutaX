@file:Suppress("unused")
package hamusutax.bittorrent

import androidx.annotation.RequiresApi
import hamusutax.bytearray.emptyByteArray
import hamusutax.bytearray.toByteArray
import hamusutax.hash.sha1
import hamusutax.hash.sha256
import hamusutax.number.isPowerOf
import hamusutax.number.nextPowerOf
import java.io.ByteArrayOutputStream
import java.io.InputStream

class BitTorrent {
    companion object {
        internal const val BLOCK_SIZE = 16 * 1024

        @RequiresApi(33)
        fun getPieces(bytes: ByteArray, pieceLength: Int = 256 * 1024, isPadding: Boolean = false) =
            getPieces(bytes.inputStream(), pieceLength, isPadding)

        /**
         * 获取 BitTorrent V1 的 Pieces
         * @param pieceLength 分块大小，单位 Byte
         */
        @RequiresApi(33)
        fun getPieces(inputStream: InputStream, pieceLength: Int = 256 * 1024, isPadding: Boolean = false): ByteArray {
            val pieces = ByteArrayOutputStream(20 * 500) // 每个 PIECE 占 20 字节，预留 500 块空间
            val buffer = ByteArrayOutputStream(pieceLength)
            while (true) {
                val block = inputStream.readNBytes(pieceLength - buffer.size())
                if (block.isEmpty())
                    break
                buffer.writeBytes(block)

                if (isPadding)
                    buffer.writeBytes(ByteArray(pieceLength - buffer.size()))
                if (buffer.size() == pieceLength) {
                    pieces.writeBytes(buffer.toByteArray().sha1())
                    buffer.reset()
                }
            }
            return pieces.toByteArray()
        }

        @RequiresApi(33)
        fun getPiecesV2(bytes: ByteArray, pieceLength: Int = 256 * 1024) =
            getPiecesV2(bytes.inputStream(), pieceLength)

        /**
         * 获取 BitTorrent V2 的 Pieces Root 与 Pieces
         * @param pieceLength 分块大小，单位 Byte
         * @return Pieces Root 与 Pieces。
         *
         * 文件为空时 Pieces Root 为空；文件小于分块大小时 Pieces 为空
         */
        @RequiresApi(33)
        fun getPiecesV2(inputStream: InputStream, pieceLength: Int = 256 * 1024): Pair<ByteArray, ByteArray> {
            val blocksPerPiece = pieceLength / BLOCK_SIZE
            val pieces = mutableListOf<ByteArray>()
            while (true) {
                val blockHashes = mutableListOf<ByteArray>()
                repeat(blocksPerPiece) {
                    val block = inputStream.readNBytes(BLOCK_SIZE)
                    if (block.isEmpty())
                        return@repeat
                    blockHashes.add(block.sha256())
                }
                if (blockHashes.isEmpty())
                    break
                if (blockHashes.size != blocksPerPiece)
                    blockHashes.paddingPieces(pieceLength)
                pieces.add(blockHashes.piecesRoot(pieceLength))
            }

            // 文件为空
            if (pieces.isEmpty())
                return emptyByteArray() to emptyByteArray()

            // 计算 ROOT HASH
            val rootHash =
                if (pieces.size > 1) pieces.piecesRoot(pieceLength)
                else pieces.first().also { pieces.clear() } // 当只有一个 PIECE 时 PIECE 列表清空

            return rootHash to pieces.toByteArray()
        }

        /**
         * 对散列列表进行 BitTorrent V2 填充
         * @param pieceLength 分块大小，单位 Byte
         */
        private fun MutableList<ByteArray>.paddingPieces(pieceLength: Int): MutableList<ByteArray> {
            // 若文件不足一个 Piece，则填充至下一个 2 的整数次幂大小；
            // 若文件超过一个 Piece，则将最后一个 Piece 填充至完整
            val targetSize = if (isEmpty()) size.nextPowerOf(2) else pieceLength
            val paddingHashes = List(targetSize - size) { ByteArray(32) }
            addAll(paddingHashes)
            return this
        }

        /**
         * 计算 BitTorrent V2 散列列表的 Pieces Root
         * @param pieceLength 分块大小，单位 Byte
         */
        fun List<ByteArray>.piecesRoot(pieceLength: Int): ByteArray {
            var list = if (size.isPowerOf(2)) this
            else this + List(size.nextPowerOf(2) - size) { pieceLength.paddingUnit }

            while (list.size > 1) {
                list = list.chunked(2).map { (it[0] + it[1]).sha256() }
            }
            return list.first()
        }
    }
}
