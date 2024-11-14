package com.lab1.giga_generator

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    // Массив строк, которые будут меняться
    private val texts = arrayOf("Мега", "Ултра", "Супер", "Нано", "Бредо", "Техно", "Пусто", "Гига")
    private var currentIndex = 0

    // Создаем Handler для периодического обновления текста
    private val handler = Handler(Looper.getMainLooper())

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showWelcomeMessage()


        val animatedTextView = findViewById<TextView>(R.id.animatedTextView)
        val text = findViewById<TextView>(R.id.scrollable_text)
        val numberSentences = findViewById<TextView>(R.id.number_sentences)
        val createButton = findViewById<Button>(R.id.create_button)

        createButton.setOnClickListener {
            val sentanceAmount = numberSentences.text.toString().toInt()
            if (sentanceAmount > 0) {
                text.text = ""
                text.append("         ")
                for (i in 1..sentanceAmount) {

                    text.append(stringBulder().toString())
                    text.append(" ")
                    if (i % 5 == 0) {
                        // Добавляем перенос строки после каждого пятоdjго предложения
                        text.append("\n")
                        text.append("         ")
                    }
                }
            }
        }



        // Запускаем обновление текста
        startTextSwitcher(animatedTextView)
    }

    private fun stringBulder(): Any {
        val first = arrayOf(
            "Согласно результатам исследований",
            "Наш опыт говорит о том, что",
            "Следует помнить, что",
            "Таким образом",
            "В первую очередь",
            "С другой стороны",
            "Как известно",
            "Статистика демонстрирует, что"
        )

        val second = arrayOf(
            "создание бренд-сообщества",
            "точный просчёт охвата ЦА",
            "продвижение сайта",
            "широкий охват аудитории",
            "постоянный рост интернет-пользователей",
            "идея интерактивной коммуникации",
            "нестандартное интернет-решение",
            "уникальная концепция онлайн-проекта"
        )

        val third = arrayOf(
            "позволяет оптимизировать ресурсы",
            "позволяет применять баинговую модель CPA",
            "предполагает комплекс диджитал-инструментов",
            "позволяет увеличить евренесс бренда",
            "стимулирует появление новых интернет-решений",
            "будет измеряться с прицелом на конкретные бизнес-показатели",
            "требует адаптации веб-сайта",
            "может стимулировать отклик рекламного месседжа"
        )

        val fourth = arrayOf(
            "для дальнейшего развития собственной платформы",
            "чтобы увеличить количество участников в представительствах бренда",
            "для оптимизации медиа-микса кампании",
            "а это принесет более эффективный WOM на последующих этапах",
            "для достижения различных аудиторий",
            "во имя увеличения CTR",
            "дабы более глубоко интегрироваться с социальными медиа",
            "что в свою очередь улучшит конвертацию посетителей в потребителей"
        )
        return first[Random.nextInt(first.size)] + " " + second[Random.nextInt(second.size)] + " " + third[Random.nextInt(third.size)] + " " + fourth[Random.nextInt(fourth.size)] + "."
    }


    private fun startTextSwitcher(textView: TextView) {
        val updateTextRunnable = object : Runnable {
            override fun run() {
                // Устанавливаем новый текст из массива
                textView.text = texts[currentIndex]

                // Переходим к следующему элементу массива
                currentIndex = (currentIndex + 1) % texts.size

                // Запускаем снова через 2 секунды
                handler.postDelayed(this, 2000)
            }
        }

        // Запуск обновления текста
        handler.post(updateTextRunnable)
    }

    private fun showWelcomeMessage() {
        val welcomeMessage = "«Генератор бесконечной речи»\n1. Пользователь выбирает количество предложений, которое должно содержаться в речи.\n2. Программа склеивает речь из случайных массивов 4-х категорий.".trimIndent() // trimIndent() removes extra indentation

        // You can display the message using an AlertDialog:
        AlertDialog.Builder(this)
            .setTitle("Инструкция")
            .setMessage(welcomeMessage)
            .setPositiveButton("OK", null)
            .show()

        // Alternatively, you can display it in a TextView:
        // val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        // welcomeTextView.text = welcomeMessage
    }

    override fun onDestroy() {
        super.onDestroy()
        // Удаляем все запланированные задачи, чтобы избежать утечек памяти
        handler.removeCallbacksAndMessages(null)
    }
}