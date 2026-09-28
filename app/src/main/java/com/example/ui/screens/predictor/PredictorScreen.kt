package com.example.ui.screens.predictor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.models.Branch
import com.example.data.models.ChanceCategory
import com.example.data.models.ExamType
import com.example.data.models.Region
import com.example.data.models.ReservationCategory
import com.example.ui.components.GeminiVoiceQuestionCard
import com.example.ui.components.PredictionCollegeCard
import com.example.ui.theme.DeepSolarOrange
import com.example.ui.theme.DreamRed
import com.example.ui.theme.DreamRedContainer
import com.example.ui.theme.ElectricOrange
import com.example.ui.theme.ElectricYellow
import com.example.ui.theme.LaserCrimson
import com.example.ui.theme.RealisticAmber
import com.example.ui.theme.RealisticAmberContainer
import com.example.ui.theme.SafeGreen
import com.example.ui.theme.SafeGreenContainer
import com.example.ui.theme.SaffronGold
import com.example.ui.theme.SolarOrange
import com.example.ui.theme.VividEmeraldGreen
import com.example.viewmodel.ChanceFilterTab
import com.example.viewmodel.PredictorViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PredictorScreen(
    viewModel: PredictorViewModel,
    modifier: Modifier = Modifier
) {
    val rankInput by viewModel.rankInput.collectAsStateWithLifecycle()
    val selectedExam by viewModel.selectedExam.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val selectedBranches by viewModel.selectedBranches.collectAsStateWithLifecycle()
    val selectedRegion by viewModel.selectedRegion.collectAsStateWithLifecycle()
    val chanceFilter by viewModel.chanceFilter.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val strategyList by viewModel.strategyList.collectAsStateWithLifecycle()
    val mockEntries by viewModel.mockRankEntries.collectAsStateWithLifecycle()
    val isMockTrackerOpen by viewModel.isMockTrackerOpen.collectAsStateWithLifecycle()
    val predictionRankMode by viewModel.predictionRankMode.collectAsStateWithLifecycle()

    val smoothedRank = viewModel.getSmoothedMockRank()
    val bestMockRank = viewModel.getBestMockRank()
    val confidence = viewModel.getPredictionConfidenceScore()

    val focusManager = LocalFocusManager.current
    var showAdvancedFilters by remember { mutableStateOf(false) }

    val predictions = viewModel.getFilteredPredictions()
    val addedKeys = remember(strategyList) {
        strategyList.map { "${it.collegeCode}_${it.branchCode}" }.toSet()
    }

    val safeCount = remember(predictions) { predictions.count { it.chance == ChanceCategory.HIGH_CHANCE } }
    val realisticCount = remember(predictions) { predictions.count { it.chance == ChanceCategory.MODERATE_CHANCE } }
    val dreamCount = remember(predictions) { predictions.count { it.chance == ChanceCategory.LOW_CHANCE } }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("predictor_screen"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Main Input Card: Rank + Exam Toggle + Category
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("rank_input_card"),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        // Eye-Beam Radiant Hero Header
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "College Predictor",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = SolarOrange
                                    ) {
                                        Text(
                                            text = "LIVE",
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color.White
                                        )
                                    }
                                }
                                Text(
                                    text = "Cutoffs for KCET & COMEDK Karnataka 2026",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            // Radiant Gradient Icon Badge
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primaryContainer,
                                modifier = Modifier.size(38.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.linearGradient(
                                                listOf(SolarOrange, SaffronGold)
                                            )
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AutoAwesome,
                                        contentDescription = "AI Predictor",
                                        modifier = Modifier.size(20.dp),
                                        tint = Color.White
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Gemini Voice Question Hub (Placed under the app icon / title header)
                        GeminiVoiceQuestionCard(viewModel = viewModel)

                        Spacer(modifier = Modifier.height(14.dp))

                        // Historical Rank Tracker & Accuracy Banner
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = MaterialTheme.colorScheme.surface,
                            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("banner_historical_rank_tracker")
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Tune,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "Rank History & Mock Accuracy",
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = VividEmeraldGreen.copy(alpha = 0.15f),
                                        border = androidx.compose.foundation.BorderStroke(1.dp, VividEmeraldGreen.copy(alpha = 0.5f))
                                    ) {
                                        Text(
                                            text = "${mockEntries.size} Mocks • $confidence% Confidence",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = VividEmeraldGreen,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .horizontalScroll(rememberScrollState()),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    // Manual Input
                                    FilterChip(
                                        selected = predictionRankMode == com.example.viewmodel.PredictionRankMode.MANUAL,
                                        onClick = { viewModel.setPredictionRankMode(com.example.viewmodel.PredictionRankMode.MANUAL) },
                                        label = { Text("Manual (#$rankInput)") }
                                    )

                                    // Smoothed Rank
                                    FilterChip(
                                        selected = predictionRankMode == com.example.viewmodel.PredictionRankMode.WEIGHTED_MOCK_SMOOTHED,
                                        onClick = { viewModel.setPredictionRankMode(com.example.viewmodel.PredictionRankMode.WEIGHTED_MOCK_SMOOTHED) },
                                        label = { Text("🎯 Smoothed AI (#$smoothedRank)") }
                                    )

                                    // Best Mock Rank
                                    FilterChip(
                                        selected = predictionRankMode == com.example.viewmodel.PredictionRankMode.BEST_MOCK_ACHIEVED,
                                        onClick = { viewModel.setPredictionRankMode(com.example.viewmodel.PredictionRankMode.BEST_MOCK_ACHIEVED) },
                                        label = { Text("🏆 Peak Mock (#$bestMockRank)") }
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Button(
                                    onClick = { viewModel.openMockTracker() },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(36.dp)
                                        .testTag("button_open_historical_rank_tracker"),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.FilterList,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Manage Historical Mock Ranks & Accuracy",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                    // 1. Exam Toggle: KCET vs COMEDK
                    Text(
                        text = "Counseling Exam",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        ExamType.values().forEach { exam ->
                            val isSelected = selectedExam == exam
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        if (isSelected) {
                                            Brush.horizontalGradient(
                                                listOf(SolarOrange, DeepSolarOrange)
                                            )
                                        } else {
                                            Brush.linearGradient(listOf(Color.Transparent, Color.Transparent))
                                        }
                                    )
                                    .clickable { viewModel.setExam(exam) }
                                    .padding(vertical = 10.dp)
                                    .testTag("exam_toggle_${exam.name}"),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = exam.displayName,
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = if (exam == ExamType.KCET) "State Quota (KEA)" else "Consortium (All-India)",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontSize = 10.sp,
                                        color = if (isSelected) Color.White.copy(alpha = 0.9f) else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 2. Rank Input with Quick Increment Chips
                    Text(
                        text = "Your Exam Rank",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = rankInput,
                        onValueChange = { viewModel.setRankInput(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("rank_text_field"),
                        placeholder = { Text("e.g. 15420 (1 to 2,50,000)") },
                        leadingIcon = {
                            Text(
                                text = "RANK #",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.ExtraBold,
                                color = SolarOrange,
                                modifier = Modifier.padding(start = 12.dp, end = 4.dp)
                            )
                        },
                        trailingIcon = {
                            if (rankInput.isNotBlank()) {
                                IconButton(onClick = { viewModel.setRankInput("") }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear",
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedBorderColor = SolarOrange
                        )
                    )

                    // Quick Rank Shortcut Chips
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf(2500, 8500, 18000, 45000, 85000, 140000, 210000).forEach { rk ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.surface,
                                border = androidx.compose.foundation.BorderStroke(1.dp, SaffronGold.copy(alpha = 0.4f)),
                                modifier = Modifier.clickable {
                                    viewModel.setRankInput(rk.toString())
                                    viewModel.triggerPredict()
                                }
                            ) {
                                Text(
                                    text = if (rk >= 1000) "${rk / 1000}k" else "$rk",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Dedicated "Predict My Colleges" Button with Eye-Beam Gradient
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .clickable {
                                focusManager.clearFocus()
                                viewModel.triggerPredict()
                            }
                            .testTag("predict_button"),
                        color = Color.Transparent,
                        shadowElevation = 4.dp,
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(LaserCrimson, SolarOrange, SaffronGold)
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = "Predict",
                                    modifier = Modifier.size(20.dp),
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Predict My Colleges (Rank #${viewModel.parsedRank})",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 3. Reservation Category (KCET specific or COMEDK)
                    Text(
                        text = if (selectedExam == ExamType.KCET) "Reservation Category (KEA Matrix)" else "COMEDK Category",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    val categoriesToShow = if (selectedExam == ExamType.KCET) {
                        ReservationCategory.values()
                    } else {
                        arrayOf(ReservationCategory.GM, ReservationCategory.KKR_371J)
                    }

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        items(categoriesToShow) { cat ->
                            val isSelected = selectedCategory == cat
                            FilterChip(
                                selected = isSelected,
                                onClick = { viewModel.setCategory(cat) },
                                label = {
                                    Text(
                                        text = "${cat.code} (${cat.displayName})",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = SolarOrange.copy(alpha = 0.15f),
                                    selectedLabelColor = SolarOrange
                                ),
                                border = if (isSelected) FilterChipDefaults.filterChipBorder(
                                    enabled = true,
                                    selected = true,
                                    borderColor = SolarOrange
                                ) else null,
                                modifier = Modifier.testTag("category_chip_${cat.code}")
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Advanced Filter Toggle Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showAdvancedFilters = !showAdvancedFilters }
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Tune,
                                contentDescription = "Filters",
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (showAdvancedFilters) "Hide Branch & Region Filters" else "Filter by Branches & Region (${if (selectedBranches.isEmpty()) "All Branches" else "${selectedBranches.size} Selected"})",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(
                            text = if (showAdvancedFilters) "▲" else "▼",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Collapsible Advanced Filters (Branch + Region)
                    AnimatedVisibility(visible = showAdvancedFilters) {
                        Column(modifier = Modifier.padding(top = 10.dp)) {
                            // Branch Selection Filter
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Preferred Branches",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                if (selectedBranches.isNotEmpty()) {
                                    Text(
                                        text = "Reset All",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.clickable { viewModel.clearBranchSelection() }
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))

                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Branch.values().forEach { branch ->
                                    val isSelected = selectedBranches.contains(branch)
                                    FilterChip(
                                        selected = isSelected,
                                        onClick = { viewModel.toggleBranch(branch) },
                                        label = {
                                            Text(
                                                text = branch.code,
                                                style = MaterialTheme.typography.labelSmall
                                            )
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = SaffronGold.copy(alpha = 0.2f),
                                            selectedLabelColor = DeepSolarOrange
                                        ),
                                        border = if (isSelected) FilterChipDefaults.filterChipBorder(
                                            enabled = true,
                                            selected = true,
                                            borderColor = SaffronGold
                                        ) else null,
                                        modifier = Modifier.testTag("branch_chip_${branch.code}")
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            // Region Filter
                            Text(
                                text = "College Region",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))

                            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                items(Region.values()) { region ->
                                    val isSelected = selectedRegion == region
                                    FilterChip(
                                        selected = isSelected,
                                        onClick = { viewModel.setRegion(region) },
                                        label = {
                                            Text(
                                                text = region.displayName,
                                                style = MaterialTheme.typography.labelSmall
                                            )
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = SolarOrange.copy(alpha = 0.15f),
                                            selectedLabelColor = SolarOrange
                                        ),
                                        modifier = Modifier.testTag("region_chip_${region.name}")
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Search Bar for College Name / Code
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.setSearchQuery(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("college_search_bar"),
                placeholder = { Text("Search 200+ colleges by name, code (e.g. RVCE, BMSCE, E001)...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotBlank()) {
                        IconButton(onClick = { viewModel.setSearchQuery("") }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedBorderColor = SolarOrange
                )
            )
        }

        // Specific Green / Yellow / Red Tabs
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    ScrollableTabRow(
                        selectedTabIndex = chanceFilter.ordinal,
                        edgePadding = 4.dp,
                        containerColor = Color.Transparent,
                        divider = {},
                        indicator = { tabPositions ->
                            TabRowDefaults.SecondaryIndicator(
                                Modifier.tabIndicatorOffset(tabPositions[chanceFilter.ordinal]),
                                color = when (chanceFilter) {
                                    ChanceFilterTab.ALL -> SolarOrange
                                    ChanceFilterTab.SAFE -> SafeGreen
                                    ChanceFilterTab.REALISTIC -> RealisticAmber
                                    ChanceFilterTab.DREAM -> DreamRed
                                }
                            )
                        }
                    ) {
                        ChanceFilterTab.values().forEach { tab ->
                            val count = when (tab) {
                                ChanceFilterTab.ALL -> predictions.size
                                ChanceFilterTab.SAFE -> safeCount
                                ChanceFilterTab.REALISTIC -> realisticCount
                                ChanceFilterTab.DREAM -> dreamCount
                            }

                            val tabColor = when (tab) {
                                ChanceFilterTab.ALL -> MaterialTheme.colorScheme.primary
                                ChanceFilterTab.SAFE -> SafeGreen
                                ChanceFilterTab.REALISTIC -> RealisticAmber
                                ChanceFilterTab.DREAM -> DreamRed
                            }

                            Tab(
                                selected = chanceFilter == tab,
                                onClick = { viewModel.setChanceFilter(tab) },
                                text = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = when (tab) {
                                                ChanceFilterTab.ALL -> "All"
                                                ChanceFilterTab.SAFE -> "Safe (High)"
                                                ChanceFilterTab.REALISTIC -> "Realistic (±15%)"
                                                ChanceFilterTab.DREAM -> "Dream (Low)"
                                            },
                                            fontWeight = if (chanceFilter == tab) FontWeight.Bold else FontWeight.Normal,
                                            color = if (chanceFilter == tab) tabColor else MaterialTheme.colorScheme.onSurfaceVariant,
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Surface(
                                            shape = CircleShape,
                                            color = if (chanceFilter == tab) tabColor.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surface
                                        ) {
                                            Text(
                                                text = "$count",
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                                style = MaterialTheme.typography.labelSmall,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = if (chanceFilter == tab) tabColor else MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                },
                                modifier = Modifier.testTag("chance_tab_${tab.name}")
                            )
                        }
                    }
                }
            }
        }

        // Student Kindness & Rank Insight Reassurance Card
        item {
            val insight = viewModel.getRankInsight()
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("student_kindness_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                ),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    SaffronGold.copy(alpha = 0.5f)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = insight.title,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = insight.encouragingMessage,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "💡 Option Entry Strategy Advice:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = SolarOrange
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = insight.recommendation,
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "🎁 SNQ & Scholarships: ${insight.snqEligibleTip}",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 11.sp,
                                color = SaffronGold,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }

        // Summary Bar
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Showing ${predictions.size} Options for Rank #${viewModel.parsedRank}",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (selectedBranches.isNotEmpty() || selectedRegion != Region.ALL || searchQuery.isNotBlank() || chanceFilter != ChanceFilterTab.ALL) {
                    Text(
                        text = "Reset Filters",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = SolarOrange,
                        modifier = Modifier
                            .clickable { viewModel.clearFilters() }
                            .padding(4.dp)
                    )
                }
            }
        }

        // Predictions List
        if (predictions.isEmpty()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No colleges with current filter combo",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Don't worry! We have great engineering colleges in Karnataka for Rank #${viewModel.parsedRank}. Click below to show all available colleges.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.clearFilters() },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SolarOrange
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Reset",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Show All Colleges for Rank #${viewModel.parsedRank}")
                        }
                    }
                }
            }
        } else {
            items(predictions, key = { "${it.cutoff.collegeCode}_${it.cutoff.branch.code}" }) { item ->
                val isAdded = addedKeys.contains("${item.cutoff.collegeCode}_${item.cutoff.branch.code}")
                PredictionCollegeCard(
                    item = item,
                    isAlreadyAdded = isAdded,
                    onAddToStrategy = { viewModel.addToStrategy(item) }
                )
            }
        }
    }

    if (isMockTrackerOpen) {
        HistoricalRankTrackerSheet(
            viewModel = viewModel,
            onDismiss = { viewModel.closeMockTracker() }
        )
    }
}
}

