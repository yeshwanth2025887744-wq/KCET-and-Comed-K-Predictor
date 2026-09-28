package com.example.ui.screens.guide

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.models.DictionaryTerm
import com.example.data.models.ExamType
import com.example.data.repository.CollegeDataRepository
import com.example.ui.theme.DreamRed
import com.example.ui.theme.DreamRedContainer
import com.example.ui.theme.RealisticAmber
import com.example.ui.theme.RealisticAmberContainer
import com.example.ui.theme.SafeGreen
import com.example.ui.theme.SafeGreenContainer
import com.example.viewmodel.PredictorViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GuideScreen(
    viewModel: PredictorViewModel,
    modifier: Modifier = Modifier
) {
    val selectedExam by viewModel.selectedExam.collectAsStateWithLifecycle()
    var activeSubTab by remember { mutableIntStateOf(0) } // 0: Dictionary, 1: Choice Rules, 2: Verification Checklist, 3: Strategy Tips
    var dictSearchQuery by remember { mutableStateOf("") }
    var selectedCategoryFilter by remember { mutableStateOf<String?>(null) }

    val checkedDocuments = remember { mutableStateListOf<Int>() }

    val documentsList = if (selectedExam == ExamType.KCET) {
        listOf(
            "SSLC / 10th Standard Marks Card (DOB & Name proof)",
            "2nd PUC / 12th Standard Marks Card (Physics, Math, Chem/Bio)",
            "Study Certificate (7 years minimum in Karnataka, BEO/DDPU Counter-signed)",
            "Rural Study Certificate (1st to 10th std in rural area, BEO signed - if claiming Rural Quota)",
            "Kannada Medium Certificate (1st to 10th in Kannada Medium, BEO signed - if claiming KM)",
            "Caste / Income Certificate (Form-D / Form-E / Form-F with 21-digit RD Number)",
            "Article 371(J) Kalyana Karnataka Region Certificate (Assistant Commissioner issued)",
            "KCET Online Application Form Printout & Admit Card",
            "KEA Verification Slip with Secret Key (Issued after Document Verification)",
            "Recent Passport Size Photographs (matching application)"
        )
    } else {
        listOf(
            "10th / SSLC Marks Card (DOB & Parent name verification)",
            "12th / 2nd PUC Original Marks Sheet (Minimum 45% aggregate in PCM / PCB)",
            "COMEDK UGET Online Application Printout & Test Admission Ticket (TAT)",
            "COMEDK Rank Card Printout",
            "Government Photo ID Proof (Aadhaar / Passport / PAN / Voter ID)",
            "COMEDK Online Allotment Letter (downloaded after Fee Payment)",
            "Online Fee Payment Receipt / Bank Challan copy (₹2.6L approx)",
            "Transfer Certificate (TC) & Migration Certificate issued by 12th Board",
            "Recent Passport Size Photographs (8 copies)"
        )
    }

    val dictionaryList = CollegeDataRepository.dictionaryList.filter {
        (it.examType == selectedExam || it.examType == ExamType.KCET && selectedExam == ExamType.KCET)
    }

    val filteredDictionary = remember(dictionaryList, dictSearchQuery, selectedCategoryFilter) {
        dictionaryList.filter { term ->
            val matchesSearch = dictSearchQuery.isBlank() ||
                    term.term.contains(dictSearchQuery, ignoreCase = true) ||
                    term.acronym.contains(dictSearchQuery, ignoreCase = true) ||
                    term.shortDefinition.contains(dictSearchQuery, ignoreCase = true) ||
                    term.detailedExplanation.contains(dictSearchQuery, ignoreCase = true)

            val matchesCat = selectedCategoryFilter == null || term.category.equals(selectedCategoryFilter, ignoreCase = true)

            matchesSearch && matchesCat
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("guide_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Exam Mode Toggle Header
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("exam_mode_banner"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (selectedExam == ExamType.KCET) "KCET (KEA) Handbook" else "COMEDK (UGET) Handbook",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = if (selectedExam == ExamType.KCET) "Government & Autonomous Quota Complete Guide" else "Private Engineering Colleges All-India Guide",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = "Handbook",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(22.dp),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Exam Switcher Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (selectedExam == ExamType.KCET) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (selectedExam == ExamType.KCET) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { viewModel.setExam(ExamType.KCET) }
                                .testTag("guide_tab_kcet")
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "🌟 KCET Mode",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (selectedExam == ExamType.KCET) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (selectedExam == ExamType.COMEDK) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (selectedExam == ExamType.COMEDK) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { viewModel.setExam(ExamType.COMEDK) }
                                .testTag("guide_tab_comedk")
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "⚡ COMEDK Mode",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (selectedExam == ExamType.COMEDK) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Shortcut to Official Cutoff PDFs (2020-2026) in Settings
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.openSettings() }
                            .testTag("guide_open_cutoff_pdfs_banner")
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = MaterialTheme.colorScheme.primary
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Description,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier
                                            .padding(6.dp)
                                            .size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "Official Cutoff PDFs (2020 - 2026)",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                    Text(
                                        text = "KCET R1, R2, R3 & COMEDK R1, R2, R3, R4 PDF matrices available in Settings.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                                    )
                                }
                            }
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Open in Settings",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }

        // Sub Navigation Tabs: Dictionary vs Choice Rules vs Checklist vs Strategy
        item {
            TabRow(
                selectedTabIndex = activeSubTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clip(RoundedCornerShape(12.dp))
            ) {
                Tab(
                    selected = activeSubTab == 0,
                    onClick = { activeSubTab = 0 },
                    text = { Text("📖 Dictionary", fontSize = 12.sp, fontWeight = FontWeight.SemiBold) },
                    modifier = Modifier.testTag("subtab_dictionary")
                )
                Tab(
                    selected = activeSubTab == 1,
                    onClick = { activeSubTab = 1 },
                    text = { Text("⚖️ Choice 1-4 Rules", fontSize = 12.sp, fontWeight = FontWeight.SemiBold) },
                    modifier = Modifier.testTag("subtab_choices")
                )
                Tab(
                    selected = activeSubTab == 2,
                    onClick = { activeSubTab = 2 },
                    text = { Text("📑 Verification", fontSize = 12.sp, fontWeight = FontWeight.SemiBold) },
                    modifier = Modifier.testTag("subtab_verification")
                )
                Tab(
                    selected = activeSubTab == 3,
                    onClick = { activeSubTab = 3 },
                    text = { Text("💡 Key Strategies", fontSize = 12.sp, fontWeight = FontWeight.SemiBold) },
                    modifier = Modifier.testTag("subtab_strategy")
                )
            }
        }

        // TAB 0: Searchable Dictionary / Glossary
        if (activeSubTab == 0) {
            item {
                Column {
                    OutlinedTextField(
                        value = dictSearchQuery,
                        onValueChange = { dictSearchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("dictionary_search_input"),
                        placeholder = { Text("Search ${selectedExam.displayName} terms (e.g. SNQ, Freeze, 371J, Tatkal)...", fontSize = 13.sp) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(20.dp)) },
                        trailingIcon = {
                            if (dictSearchQuery.isNotEmpty()) {
                                IconButton(onClick = { dictSearchQuery = "" }) {
                                    Icon(Icons.Default.Clear, contentDescription = "Clear", modifier = Modifier.size(18.dp))
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Category chips
                    val categories = listOf("All", "Fee & Quotas", "Reservations", "Process & Portal", "Allotment Rules", "Documents", "Fee & Refund")
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        categories.forEach { cat ->
                            val isSelected = (cat == "All" && selectedCategoryFilter == null) || (cat == selectedCategoryFilter)
                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    selectedCategoryFilter = if (cat == "All") null else cat
                                },
                                label = { Text(cat, fontSize = 11.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                        }
                    }
                }
            }

            if (filteredDictionary.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("No dictionary terms matched your search", style = MaterialTheme.typography.titleSmall)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text("Try searching for 'SNQ', 'Freeze', 'Tatkal', 'RD Number', or 'Penalty'.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            } else {
                items(filteredDictionary) { term ->
                    DictionaryTermCard(term = term)
                }
            }
        }

        // TAB 1: Choice 1 to Choice 4 Detailed Rule Book
        if (activeSubTab == 1) {
            val choiceRules = if (selectedExam == ExamType.COMEDK) CollegeDataRepository.comedkChoiceRules else CollegeDataRepository.kcetChoiceRules

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                    )
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = if (selectedExam == ExamType.COMEDK) "COMEDK Decision Decision Tree (Rounds 1, 2 & 3)" else "KEA Karnataka Choice 1, 2, 3, 4 Protocol",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (selectedExam == ExamType.COMEDK)
                                "COMEDK requires full tuition fee (~₹2.6L) to hold a seat in Choice 2 (Accept & Upgrade). If upgraded, the old seat is released automatically."
                            else
                                "KEA allows you to hold your Round 1 seat in Choice 2 by paying the government challan fee. It protects your seat while letting you try for higher dream colleges.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }

            items(choiceRules) { rule ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("choice_rule_card_${rule.choiceNumber}"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        when (rule.choiceNumber) {
                            1 -> SafeGreen.copy(alpha = 0.5f)
                            2 -> MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                            3 -> RealisticAmber.copy(alpha = 0.5f)
                            else -> DreamRed.copy(alpha = 0.5f)
                        }
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = when (rule.choiceNumber) {
                                    1 -> SafeGreenContainer
                                    2 -> MaterialTheme.colorScheme.primaryContainer
                                    3 -> RealisticAmberContainer
                                    else -> DreamRedContainer
                                }
                            ) {
                                Text(
                                    text = "CHOICE ${rule.choiceNumber}",
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = when (rule.choiceNumber) {
                                        1 -> SafeGreen
                                        2 -> MaterialTheme.colorScheme.primary
                                        3 -> RealisticAmber
                                        else -> DreamRed
                                    }
                                )
                            }

                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                if (rule.isRetainingSeat) {
                                    Surface(shape = CircleShape, color = SafeGreenContainer) {
                                        Text("Holds Seat", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 10.sp, color = SafeGreen, fontWeight = FontWeight.Bold)
                                    }
                                } else {
                                    Surface(shape = CircleShape, color = DreamRedContainer) {
                                        Text("Surrenders Seat", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 10.sp, color = DreamRed, fontWeight = FontWeight.Bold)
                                    }
                                }

                                if (rule.participatesNextRound) {
                                    Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primaryContainer) {
                                        Text("Next Round ✅", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 10.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = rule.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = rule.actionSummary,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = rule.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 19.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "📌 Pro-Tips & Cautions:",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = rule.dosAndDonts,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "⚠️ Risk/Worst Case: ${rule.worstCaseScenario}",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontSize = 11.sp,
                                    color = DreamRed,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }

        // TAB 2: Document Verification Checklist
        if (activeSubTab == 2) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("document_checklist_card"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Description,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${selectedExam.displayName} Document Checklist",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Surface(
                                shape = CircleShape,
                                color = SafeGreenContainer
                            ) {
                                Text(
                                    text = "${checkedDocuments.size}/${documentsList.size} Ready",
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = SafeGreen
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        documentsList.forEachIndexed { idx, doc ->
                            val isChecked = checkedDocuments.contains(idx)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        if (isChecked) checkedDocuments.remove(idx) else checkedDocuments.add(idx)
                                    }
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = { checked ->
                                        if (checked) checkedDocuments.add(idx) else checkedDocuments.remove(idx)
                                    }
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = doc,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (isChecked) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = if (isChecked) FontWeight.SemiBold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }
        }

        // TAB 3: Strategic Rules & Tips
        if (activeSubTab == 3) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = SafeGreen,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "10 Golden Rules for Option Entry",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        val tips = if (selectedExam == ExamType.COMEDK) {
                            listOf(
                                "1. Enter colleges in TRUE priority order regardless of your COMEDK rank.",
                                "2. COMEDK annual tuition fee is ~₹2,00,000 to ₹2,75,000. Do NOT add colleges you cannot afford.",
                                "3. In Choice 2 (Accept & Upgrade), you must pay the full tuition fee to lock your seat before entering Round 2.",
                                "4. In every Tatkal Choice Editing window, remove colleges that you are no longer interested in.",
                                "5. Pay attention to the Seat Cancellation / Surrender deadline before Round 3 to avoid the ₹2.6L penalty clause.",
                                "6. Keep your net banking / credit card transaction limits raised prior to the seat acceptance payment window.",
                                "7. If you secure a seat in KCET or JoSAA (IIT/NIT), surrender your COMEDK seat in time for a full refund."
                            )
                        } else {
                            listOf(
                                "1. Enter at least 40-60 options — KEA does NOT charge any extra fee for entering more choices.",
                                "2. Always order by genuine preference: Dream colleges first, Realistic in middle, Safe at the bottom.",
                                "3. Never place a safe college higher than a dream college (otherwise the dream college is blocked forever).",
                                "4. Always verify college codes (e.g. E001 for RVCE, E003 for BMSCE, E005 for MSRIT).",
                                "5. If you get a seat in Round 1, always choose Choice 2 (Hold & Upgrade) instead of rejecting it if you want to try Round 2.",
                                "6. Pay fees before the KEA bank challan deadline to avoid automatic cancellation.",
                                "7. Download and preserve the final Option Entry Acknowledgement slip."
                            )
                        }

                        tips.forEach { tip ->
                            Text(
                                text = tip,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = 20.sp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DictionaryTermCard(
    term: DictionaryTerm,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = term.term,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (term.acronym.isNotBlank()) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = MaterialTheme.colorScheme.primaryContainer
                            ) {
                                Text(
                                    text = term.acronym,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = term.shortDefinition,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 2
                    )
                }

                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Text(
                        text = term.category,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    Text(
                        text = term.detailedExplanation,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 19.sp
                    )

                    if (term.studentTip.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "💡 Student Tip: ${term.studentTip}",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
