package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.Branch
import com.example.data.models.ChanceCategory
import com.example.data.models.ExamType
import com.example.data.models.Region
import com.example.data.models.ReservationCategory
import com.example.data.repository.CollegeDataRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("KCET & COMEDK 2027 Predictor", appName)
  }

  @Test
  fun `test college prediction logic for high and low rank`() {
    val rvceCutoff = CollegeDataRepository.cutoffsList.first { it.collegeCode == "E001" && it.branch == Branch.CSE }
    
    // Student with top rank 200 should have Safe chance for RVCE CSE (Cutoff 310)
    val (chanceTop, _) = CollegeDataRepository.evaluateChance(200, rvceCutoff.kcetCutoffGM)
    assertEquals(ChanceCategory.HIGH_CHANCE, chanceTop)

    // Student with rank 50000 should have Dream chance for RVCE CSE
    val (chanceLow, _) = CollegeDataRepository.evaluateChance(50000, rvceCutoff.kcetCutoffGM)
    assertEquals(ChanceCategory.LOW_CHANCE, chanceLow)
  }

  @Test
  fun `test repository prediction filters`() {
    val results = CollegeDataRepository.getPredictions(
      studentRank = 15000,
      examType = ExamType.KCET,
      category = ReservationCategory.GM,
      selectedBranches = setOf(Branch.CSE),
      selectedRegion = Region.BENGALURU_URBAN
    )
    assertTrue(results.isNotEmpty())
    assertTrue(results.all { it.cutoff.branch == Branch.CSE })
  }
}
