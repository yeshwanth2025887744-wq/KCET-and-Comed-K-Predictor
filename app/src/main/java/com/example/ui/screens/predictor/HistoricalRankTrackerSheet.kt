package com.example.ui.screens.predictor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.MockRankEntryEntity
import com.example.ui.theme.ElectricOrange
import com.example.ui.theme.LaserCrimson
import com.example.ui.theme.SafeGreen
import com.example.ui.theme.SafeGreenContainer
import com.example.ui.theme.SaffronGold
import com.example.ui.theme.SolarOrange
import com.example.ui.theme.VividEmeraldGreen
import com.example.viewmodel.PredictionRankMode
import com.example.viewmodel.PredictorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoricalRankTrackerSheet(
    viewModel: PredictorViewModel,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val mockEntries by viewModel.mockRankEntries.collectAsStateWithLifecycle()
    val activeRankMode by viewModel.predictionRankMode.collectAsStateWithLifecycle()

    val smoothedRank = viewModel.getSmoothedMockRank()
    val bestRank = viewModel.getBestMockRank()
    val avgRank = viewModel.getAverageMockRank()
    val rankDelta = viewModel.getRankImprovementDelta()
    val confidence = viewModel.getPredictionConfidenceScore()

    var showAddForm by remember { mutableStateOf(false) }

    // Form Fields
    var newTestName by remember { mutableStateOf("") }
    var newExamType by remember { mutableStateOf("KCET") }
    var newRankInput by remember { mutableStateOf("") }
    var newScoreInput by remember { mutableStateOf("") }
    var newMathScore by remember { mutableStateOf("") }
    var newPhysicsScore by remember { mutableStateOf("") }
    var newChemScore by remember { mutableStateOf("") }
    var newTargetBranch by remember { mutableStateOf("CSE / AIML") }
    var newNotes by remember { mutableStateOf("") }
    var formError by remember { mutableStateOf<String?>(null) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null,
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.testTag("historical_rank_tracker_sheet")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            // Top App Bar in Sheet
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.size(42.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Timeline,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Historical Rank Tracker",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Log mock exam scores to boost prediction accuracy",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.testTag("button_close_mock_tracker")
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(bottom = 32.dp)
            ) {
                // 1. AI Smoothed Accuracy Header Card
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("card_smoothed_accuracy_summary"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.AutoAwesome,
                                        contentDescription = null,
                                        tint = SolarOrange,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "AI-Smoothed Prediction Engine",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                                Surface(
                                    shape = RoundedCornerShape(20.dp),
                                    color = VividEmeraldGreen.copy(alpha = 0.15f),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, VividEmeraldGreen)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Speed,
                                            contentDescription = null,
                                            tint = VividEmeraldGreen,
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "$confidence% Accuracy Confidence",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = VividEmeraldGreen
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            // Key Metrics Grid
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                // Smoothed True Rank
                                Surface(
                                    modifier = Modifier.weight(1.1f),
                                    shape = RoundedCornerShape(14.dp),
                                    color = MaterialTheme.colorScheme.surface,
                                    border = androidx.compose.foundation.BorderStroke(
                                        if (activeRankMode == PredictionRankMode.WEIGHTED_MOCK_SMOOTHED) 2.dp else 1.dp,
                                        if (activeRankMode == PredictionRankMode.WEIGHTED_MOCK_SMOOTHED) VividEmeraldGreen else MaterialTheme.colorScheme.outlineVariant
                                    )
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Text(
                                            text = "Smoothed Rank",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Text(
                                            text = "#$smoothedRank",
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        Text(
                                            text = "${mockEntries.size} Mock Avg",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = VividEmeraldGreen
                                        )
                                    }
                                }

                                // Peak Best Rank
                                Surface(
                                    modifier = Modifier.weight(0.95f),
                                    shape = RoundedCornerShape(14.dp),
                                    color = MaterialTheme.colorScheme.surface,
                                    border = androidx.compose.foundation.BorderStroke(
                                        if (activeRankMode == PredictionRankMode.BEST_MOCK_ACHIEVED) 2.dp else 1.dp,
                                        if (activeRankMode == PredictionRankMode.BEST_MOCK_ACHIEVED) SolarOrange else MaterialTheme.colorScheme.outlineVariant
                                    )
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Text(
                                            text = "Peak Best",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Text(
                                            text = "#$bestRank",
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = SolarOrange
                                        )
                                        Text(
                                            text = "Top Benchmark",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                // Progress Delta
                                Surface(
                                    modifier = Modifier.weight(0.95f),
                                    shape = RoundedCornerShape(14.dp),
                                    color = MaterialTheme.colorScheme.surface,
                                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Text(
                                            text = "Rank Gain",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Text(
                                            text = if (rankDelta >= 0) "+$rankDelta" else "$rankDelta",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = if (rankDelta >= 0) VividEmeraldGreen else LaserCrimson
                                        )
                                        Text(
                                            text = "Since 1st Mock",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            // Action Buttons to apply directly to Predictor
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = {
                                        viewModel.setPredictionRankMode(PredictionRankMode.WEIGHTED_MOCK_SMOOTHED)
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("button_apply_smoothed_rank"),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (activeRankMode == PredictionRankMode.WEIGHTED_MOCK_SMOOTHED) VividEmeraldGreen else MaterialTheme.colorScheme.primary
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.FlashOn,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = if (activeRankMode == PredictionRankMode.WEIGHTED_MOCK_SMOOTHED) "Active: Smoothed #$smoothedRank" else "Apply Smoothed (#$smoothedRank)",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                OutlinedButton(
                                    onClick = {
                                        viewModel.setPredictionRankMode(PredictionRankMode.BEST_MOCK_ACHIEVED)
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("button_apply_best_mock_rank"),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.EmojiEvents,
                                        contentDescription = null,
                                        tint = SolarOrange,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Apply Best (#$bestRank)",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                // 2. Add New Mock Entry Bar
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Logged Mock Tests (${mockEntries.size})",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedButton(
                                onClick = { viewModel.resetMockEntriesToBenchmark() },
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                modifier = Modifier.testTag("button_reset_mock_benchmarks")
                            ) {
                                Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Reset Benchmarks", style = MaterialTheme.typography.labelSmall)
                            }

                            Button(
                                onClick = { showAddForm = !showAddForm },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                modifier = Modifier.testTag("button_toggle_add_mock_form")
                            ) {
                                Icon(
                                    imageVector = if (showAddForm) Icons.Default.Close else Icons.Default.Add,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(if (showAddForm) "Cancel" else "Log Mock", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                // 3. Add Mock Entry Expandable Form
                if (showAddForm) {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("card_add_mock_entry_form"),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Log New Mock Exam Result",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                OutlinedTextField(
                                    value = newTestName,
                                    onValueChange = { newTestName = it },
                                    label = { Text("Mock Test / Institute Name *") },
                                    placeholder = { Text("e.g. KEA Grand Mock 5 / Allen / BASE") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("input_mock_test_name"),
                                    singleLine = true,
                                    shape = RoundedCornerShape(10.dp)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    // Exam Toggle
                                    Surface(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clickable { newExamType = "KCET" },
                                        shape = RoundedCornerShape(10.dp),
                                        color = if (newExamType == "KCET") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                                    ) {
                                        Box(modifier = Modifier.padding(12.dp), contentAlignment = Alignment.Center) {
                                            Text(
                                                "KCET",
                                                fontWeight = FontWeight.Bold,
                                                color = if (newExamType == "KCET") Color.White else MaterialTheme.colorScheme.onSurface
                                            )
                                        }
                                    }

                                    Surface(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clickable { newExamType = "COMEDK" },
                                        shape = RoundedCornerShape(10.dp),
                                        color = if (newExamType == "COMEDK") SolarOrange else MaterialTheme.colorScheme.surface,
                                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                                    ) {
                                        Box(modifier = Modifier.padding(12.dp), contentAlignment = Alignment.Center) {
                                            Text(
                                                "COMEDK",
                                                fontWeight = FontWeight.Bold,
                                                color = if (newExamType == "COMEDK") Color.White else MaterialTheme.colorScheme.onSurface
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    OutlinedTextField(
                                        value = newRankInput,
                                        onValueChange = { newRankInput = it.filter { c -> c.isDigit() }.take(6) },
                                        label = { Text("Rank Achieved *") },
                                        placeholder = { Text("e.g. 11200") },
                                        modifier = Modifier
                                            .weight(1f)
                                            .testTag("input_mock_rank"),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        singleLine = true,
                                        shape = RoundedCornerShape(10.dp)
                                    )

                                    OutlinedTextField(
                                        value = newScoreInput,
                                        onValueChange = { newScoreInput = it.filter { c -> c.isDigit() }.take(3) },
                                        label = { Text("Total Score /180") },
                                        placeholder = { Text("e.g. 142") },
                                        modifier = Modifier
                                            .weight(1f)
                                            .testTag("input_mock_score"),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        singleLine = true,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                // Subject Scores (Math, Physics, Chem)
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    OutlinedTextField(
                                        value = newMathScore,
                                        onValueChange = { newMathScore = it.filter { c -> c.isDigit() }.take(2) },
                                        label = { Text("Math /60") },
                                        modifier = Modifier.weight(1f),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        singleLine = true,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    OutlinedTextField(
                                        value = newPhysicsScore,
                                        onValueChange = { newPhysicsScore = it.filter { c -> c.isDigit() }.take(2) },
                                        label = { Text("Phy /60") },
                                        modifier = Modifier.weight(1f),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        singleLine = true,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    OutlinedTextField(
                                        value = newChemScore,
                                        onValueChange = { newChemScore = it.filter { c -> c.isDigit() }.take(2) },
                                        label = { Text("Chem /60") },
                                        modifier = Modifier.weight(1f),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        singleLine = true,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                OutlinedTextField(
                                    value = newNotes,
                                    onValueChange = { newNotes = it },
                                    label = { Text("Notes / Observations") },
                                    placeholder = { Text("e.g. Improved speed in Calculus, missed 2 chemistry questions") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )

                                if (formError != null) {
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = formError!!,
                                        color = LaserCrimson,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Button(
                                    onClick = {
                                        val rankInt = newRankInput.toIntOrNull()
                                        if (newTestName.isBlank()) {
                                            formError = "Please enter the test name or institute."
                                            return@Button
                                        }
                                        if (rankInt == null || rankInt <= 0) {
                                            formError = "Please enter a valid rank number."
                                            return@Button
                                        }
                                        val scoreInt = newScoreInput.toIntOrNull() ?: 0
                                        viewModel.addMockRankEntry(
                                            testName = newTestName.trim(),
                                            examType = newExamType,
                                            rank = rankInt,
                                            score = scoreInt,
                                            notes = newNotes.trim(),
                                            targetBranch = newTargetBranch.trim(),
                                            mathScore = newMathScore.toIntOrNull() ?: 0,
                                            physicsScore = newPhysicsScore.toIntOrNull() ?: 0,
                                            chemistryScore = newChemScore.toIntOrNull() ?: 0
                                        )
                                        // Reset fields
                                        newTestName = ""
                                        newRankInput = ""
                                        newScoreInput = ""
                                        newMathScore = ""
                                        newPhysicsScore = ""
                                        newChemScore = ""
                                        newNotes = ""
                                        formError = null
                                        showAddForm = false
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("button_save_mock_entry"),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = VividEmeraldGreen)
                                ) {
                                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Save Mock Record & Re-Smooth Accuracy", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }

                // 4. Mock Entries List
                items(mockEntries, key = { it.id }) { entry ->
                    MockTestEntryCard(
                        entry = entry,
                        onDelete = { viewModel.deleteMockRankEntry(entry.id) },
                        onApply = {
                            viewModel.setRankInput(entry.rankAchieved.toString())
                            viewModel.triggerPredict()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MockTestEntryCard(
    entry: MockRankEntryEntity,
    onDelete: () -> Unit,
    onApply: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("card_mock_entry_${entry.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f)),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (entry.examType == "COMEDK") SolarOrange else MaterialTheme.colorScheme.primary
                    ) {
                        Text(
                            text = entry.examType,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = entry.dateFormatted,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = entry.testName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Achieved Rank",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "#${entry.rankAchieved}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                if (entry.scoreObtained > 0) {
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Score",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${entry.scoreObtained} / ${entry.totalScore}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = VividEmeraldGreen
                        )
                    }
                }
            }

            if (entry.mathScore > 0 || entry.physicsScore > 0 || entry.chemistryScore > 0) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    if (entry.mathScore > 0) {
                        SubjectPill(name = "Math", score = entry.mathScore)
                    }
                    if (entry.physicsScore > 0) {
                        SubjectPill(name = "Physics", score = entry.physicsScore)
                    }
                    if (entry.chemistryScore > 0) {
                        SubjectPill(name = "Chemistry", score = entry.chemistryScore)
                    }
                }
            }

            if (entry.notes.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                ) {
                    Text(
                        text = "📝 ${entry.notes}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onApply,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(imageVector = Icons.Default.FlashOn, contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Load Rank #${entry.rankAchieved} to Live Predictor",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SubjectPill(name: String, score: Int) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Text(
            text = "$name: $score/60",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}
