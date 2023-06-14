package com.trashcare.user.utils

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.PriorityQueue

class ImageClassifier(context: Context, private val imageSize: Int) {
    private val model: Interpreter
    private val labels: List<String>

    init {
        val options = Interpreter.Options()
        options.setNumThreads(4)
        model = Interpreter(loadModelFile(context), options)
        labels = loadLabelList(context)
    }

    fun classify(bitmap: Bitmap): List<String> {
        val byteBuffer = convertBitmapToByteBuffer(bitmap)
        val output = Array(1) { FloatArray(labels.size) }
        model.run(byteBuffer, output)
        return getSortedResult(output)
    }

    private fun loadModelFile(context: Context): MappedByteBuffer {
        val assetFileDescriptor = context.assets.openFd("trash_model.tflite")
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun loadLabelList(context: Context): List<String> {
        return context.assets.open("trash_label.txt").bufferedReader().useLines { it.toList() }
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
        byteBuffer.order(ByteOrder.nativeOrder())
        val intValues = IntArray(imageSize * imageSize)
        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        var pixel = 0
        for (i in 0 until imageSize) {
            for (j in 0 until imageSize) {
                val value = intValues[pixel++]
                byteBuffer.putFloat((((value shr 16 and 0xFF) - 127.5f) / 127.5f))
                byteBuffer.putFloat((((value shr 8 and 0xFF) - 127.5f) / 127.5f))
                byteBuffer.putFloat((((value and 0xFF) - 127.5f) / 127.5f))
            }
        }
        return byteBuffer
    }

    private fun getSortedResult(labelProbArray: Array<FloatArray>): List<String> {
        val pq = PriorityQueue(
            labels.size,
            Comparator<LabelScore> { (_, v1), (_, v2) -> v1.compareTo(v2) }
        )

        for (i in labels.indices) {
            pq.add(
                LabelScore(labels[i], labelProbArray[0][i])
            )
            if (pq.size > labels.size) {
                pq.poll()
            }
        }

        val result = ArrayList<String>()
        while (!pq.isEmpty()) {
            val labelScore = pq.poll()
            result.add(labelScore.label + ":" + labelScore.score)
        }
        result.reverse()

        return result
    }
}

data class LabelScore(val label: String, val score: Float)
