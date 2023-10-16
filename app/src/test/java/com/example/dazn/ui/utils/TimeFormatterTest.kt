package com.example.dazn.ui.utils

import android.content.Context
import com.example.dazn.R
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime

class TimeFormatterTest {

    private val context: Context = mockk(relaxed = true, relaxUnitFun = true) {
        every { getString(R.string.today) } returns "Today "
        every { getString(R.string.tomorrow) } returns "Tomorrow "
        every { getString(R.string.yesterday) } returns "Yesterday "
    }


    private val tested = TimeFormatter(context)
    private val now = ZonedDateTime.now()

    @Test
    fun `yesterday format`() {
        val date = now.minusDays(1L)

        val result = tested.format(date)

        assertThat(result).isEqualTo("Yesterday ${now.hour}:${now.minute}")
    }

    @Test
    fun `today format`() {
        val date = now

        val result = tested.format(date)

        assertThat(result).isEqualTo("Today ${now.hour}:${now.minute}")
    }

    @Test
    fun `tomorrow format`() {
        val date = now.plusDays(1L)

        val result = tested.format(date)

        assertThat(result).isEqualTo("Tomorrow ${now.hour}:${now.minute}")
    }

    @Test
    fun `further than tomorrow format`() {
        val date = now.plusDays(3L)

        val result = tested.format(date)

        assertThat(result).isEqualTo("${now.dayOfMonth + 3}/${now.monthValue}/${now.year}")
    }
}