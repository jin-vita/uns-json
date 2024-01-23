package org.techtown.unsjson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import org.techtown.unsjson.databinding.ActivityMainBinding
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val tag: String = javaClass.simpleName

    private val fileList by lazy { listOf("option-1.json", "option-2.json", "option-3.json") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        option1Button.setOnClickListener { getContent(0) }
        option2Button.setOnClickListener { getContent(1) }
        option3Button.setOnClickListener { getContent(2) }
    }

    private fun ActivityMainBinding.getContent(index: Int) {
        val method = Thread.currentThread().stackTrace[2].methodName
        AppData.debug(tag, "$method called.")
        val file = fileList[index]
        statusText.text = file

        val options = readJsonFromAsset(file)
        if (options == null) {
            resultText.text = "결과 없음"
            return
        }
        resultText.text = StringBuilder()
            .append("optionA : ").appendLine(options.optionA)
            .append("optionB : ").appendLine(options.optionB)
            .append("optionC : ").appendLine(options.optionC)
            .append("optionD : ").appendLine(options.optionD)
            .append("optionE : ").append(options.optionE)
    }

    private fun readJsonFromAsset(fileName: String): Options? = try {
        val method = Thread.currentThread().stackTrace[2].methodName
        AppData.debug(tag, "$method called.")

        val inputStream: InputStream = assets.open(fileName)
        val reader = InputStreamReader(inputStream)
        Gson().fromJson(reader, Options::class.java)
    } catch (e: Exception) {
        AppData.error("AppData", "JSON 파싱 오류: ${e.message}")
        null
    }
}