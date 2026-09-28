package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.FormatListNumbered
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Rule
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.SmartToy
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material.icons.filled.Settings
import com.example.ui.components.NetworkStatusBanner
import com.example.ui.screens.ai.AiMentorScreen
import com.example.ui.screens.ai.GeminiVoiceAssistantSheet
import com.example.ui.screens.auth.AuthScreen
import com.example.ui.screens.directory.CollegeDirectoryScreen
import com.example.ui.screens.guide.GuideScreen
import com.example.ui.screens.predictor.PredictorScreen
import com.example.ui.screens.settings.SettingsAndCutoffsSheet
import com.example.ui.screens.simulator.SimulatorScreen
import com.example.ui.screens.strategy.StrategyScreen
import com.example.ui.theme.ElectricOrange
import com.example.ui.theme.ElectricYellow
import com.example.ui.theme.LaserCrimson
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.SaffronGold
import com.example.ui.theme.SolarOrange
import com.example.ui.theme.VividEmeraldGreen
import com.example.viewmodel.AppTab
import com.example.viewmodel.PredictorViewModel

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import com.example.viewmodel.ThemeMode

class MainActivity : ComponentActivity() {

    private val viewModel: PredictorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()
            val isDark = when (themeMode) {
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
            }

            MyApplicationTheme(darkTheme = isDark) {
                val isAuthenticated by viewModel.isAuthenticated.collectAsStateWithLifecycle()
                if (!isAuthenticated) {
                    AuthScreen(viewModel = viewModel)
                } else {
                    MainAppScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen(viewModel: PredictorViewModel) {
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val strategyList by viewModel.strategyList.collectAsStateWithLifecycle()
    val snackbarMessage by viewModel.snackbarMessage.collectAsStateWithLifecycle()
    val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()
    val isVoiceAssistantOpen by viewModel.isVoiceAssistantOpen.collectAsStateWithLifecycle()
    val isSettingsOpen by viewModel.isSettingsOpen.collectAsStateWithLifecycle()
    val isNetworkAvailable by viewModel.isNetworkAvailable.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearSnackbar()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("main_app_scaffold"),
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primaryContainer,
                                modifier = Modifier.padding(end = 10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            Brush.horizontalGradient(
                                                listOf(SolarOrange, SaffronGold)
                                            )
                                        )
                                        .padding(horizontal = 7.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = "2027",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White
                                    )
                                }
                            }
                            Text(
                                text = when (currentTab) {
                                    AppTab.PREDICTOR -> "KCET & COMEDK Predictor"
                                    AppTab.AI_MENTOR -> "Gemini Admissions Mentor"
                                    AppTab.STRATEGY -> "Option Entry Strategy"
                                    AppTab.SIMULATOR -> "KEA Allotment Simulator"
                                    AppTab.COLLEGES -> "College Directory (200+)"
                                    AppTab.GUIDE -> "Counselling Guide & FAQs"
                                },
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    },
                    actions = {
                        // Official Cutoffs & Settings Button (2020-2026 Archive)
                        IconButton(
                            onClick = { viewModel.openSettings() },
                            modifier = Modifier.testTag("topbar_settings_button")
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Settings & Cutoff PDFs (2020-2026)",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                        // Voice Assistant Quick Button
                        IconButton(
                            onClick = {
                                viewModel.openVoiceAssistant()
                            },
                            modifier = Modifier.testTag("topbar_voice_button")
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Mic,
                                contentDescription = "Ask Gemini Voice",
                                tint = SolarOrange
                            )
                        }

                        IconButton(
                            onClick = { viewModel.toggleTheme() },
                            modifier = Modifier.testTag("theme_toggle_button")
                        ) {
                            val icon = when (themeMode) {
                                ThemeMode.SYSTEM -> Icons.Filled.BrightnessAuto
                                ThemeMode.LIGHT -> Icons.Filled.LightMode
                                ThemeMode.DARK -> Icons.Filled.DarkMode
                            }
                            Icon(
                                imageVector = icon,
                                contentDescription = "Theme: ${themeMode.displayName}",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface
                    )
                )

                // Luminous Eye-Beam Spectrum Accent Line (Vibrant Crimson -> Orange -> Saffron Yellow -> Emerald Green)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.5.dp)
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    LaserCrimson,
                                    ElectricOrange,
                                    ElectricYellow,
                                    SaffronGold,
                                    VividEmeraldGreen
                                )
                            )
                        )
                )
            }
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .testTag("bottom_navigation_bar"),
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                val navColors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // 1. Predictor
                NavigationBarItem(
                    selected = currentTab == AppTab.PREDICTOR,
                    onClick = { viewModel.selectTab(AppTab.PREDICTOR) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == AppTab.PREDICTOR) Icons.Filled.AutoAwesome else Icons.Outlined.AutoAwesome,
                            contentDescription = "Predictor",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = { Text("Predictor", fontSize = 10.sp, fontWeight = if (currentTab == AppTab.PREDICTOR) FontWeight.Bold else FontWeight.Normal) },
                    colors = navColors,
                    modifier = Modifier.testTag("nav_item_predictor")
                )

                // 2. AI Mentor
                NavigationBarItem(
                    selected = currentTab == AppTab.AI_MENTOR,
                    onClick = { viewModel.selectTab(AppTab.AI_MENTOR) },
                    icon = {
                        BadgedBox(
                            badge = {
                                Badge(
                                    containerColor = VividEmeraldGreen,
                                    contentColor = Color.White
                                ) { Text("AI") }
                            }
                        ) {
                            Icon(
                                imageVector = if (currentTab == AppTab.AI_MENTOR) Icons.Filled.SmartToy else Icons.Outlined.SmartToy,
                                contentDescription = "AI Mentor",
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    label = { Text("AI Mentor", fontSize = 10.sp, fontWeight = if (currentTab == AppTab.AI_MENTOR) FontWeight.Bold else FontWeight.Normal) },
                    colors = navColors,
                    modifier = Modifier.testTag("nav_item_ai_mentor")
                )

                // 3. Option Strategy
                NavigationBarItem(
                    selected = currentTab == AppTab.STRATEGY,
                    onClick = { viewModel.selectTab(AppTab.STRATEGY) },
                    icon = {
                        if (strategyList.isNotEmpty()) {
                            BadgedBox(
                                badge = {
                                    Badge(
                                        containerColor = LaserCrimson,
                                        contentColor = Color.White
                                    ) { Text("${strategyList.size}") }
                                }
                            ) {
                                Icon(
                                    imageVector = if (currentTab == AppTab.STRATEGY) Icons.Filled.FormatListNumbered else Icons.Outlined.FormatListNumbered,
                                    contentDescription = "Strategy",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        } else {
                            Icon(
                                imageVector = if (currentTab == AppTab.STRATEGY) Icons.Filled.FormatListNumbered else Icons.Outlined.FormatListNumbered,
                                contentDescription = "Strategy",
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    label = { Text("Strategy", fontSize = 10.sp, fontWeight = if (currentTab == AppTab.STRATEGY) FontWeight.Bold else FontWeight.Normal) },
                    colors = navColors,
                    modifier = Modifier.testTag("nav_item_strategy")
                )

                // 4. Simulator
                NavigationBarItem(
                    selected = currentTab == AppTab.SIMULATOR,
                    onClick = { viewModel.selectTab(AppTab.SIMULATOR) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == AppTab.SIMULATOR) Icons.Filled.Rule else Icons.Outlined.Rule,
                            contentDescription = "Simulator",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = { Text("Simulator", fontSize = 10.sp, fontWeight = if (currentTab == AppTab.SIMULATOR) FontWeight.Bold else FontWeight.Normal) },
                    colors = navColors,
                    modifier = Modifier.testTag("nav_item_simulator")
                )

                // 5. Colleges
                NavigationBarItem(
                    selected = currentTab == AppTab.COLLEGES,
                    onClick = { viewModel.selectTab(AppTab.COLLEGES) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == AppTab.COLLEGES) Icons.Filled.School else Icons.Outlined.School,
                            contentDescription = "Colleges",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = { Text("Colleges", fontSize = 10.sp, fontWeight = if (currentTab == AppTab.COLLEGES) FontWeight.Bold else FontWeight.Normal) },
                    colors = navColors,
                    modifier = Modifier.testTag("nav_item_colleges")
                )

                // 6. Guide
                NavigationBarItem(
                    selected = currentTab == AppTab.GUIDE,
                    onClick = { viewModel.selectTab(AppTab.GUIDE) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == AppTab.GUIDE) Icons.Filled.MenuBook else Icons.Outlined.MenuBook,
                            contentDescription = "Guide",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = { Text("Guide", fontSize = 10.sp, fontWeight = if (currentTab == AppTab.GUIDE) FontWeight.Bold else FontWeight.Normal) },
                    colors = navColors,
                    modifier = Modifier.testTag("nav_item_guide")
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                AnimatedContent(
                    targetState = currentTab,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    label = "TabContent"
                ) { tab ->
                    when (tab) {
                        AppTab.PREDICTOR -> PredictorScreen(viewModel = viewModel)
                        AppTab.AI_MENTOR -> AiMentorScreen(viewModel = viewModel)
                        AppTab.STRATEGY -> StrategyScreen(viewModel = viewModel)
                        AppTab.SIMULATOR -> SimulatorScreen(viewModel = viewModel)
                        AppTab.COLLEGES -> CollegeDirectoryScreen(viewModel = viewModel)
                        AppTab.GUIDE -> GuideScreen(viewModel = viewModel)
                    }
                }

                if (isVoiceAssistantOpen) {
                    GeminiVoiceAssistantSheet(
                        viewModel = viewModel,
                        onDismiss = { viewModel.closeVoiceAssistant() }
                    )
                }

                if (isSettingsOpen) {
                    SettingsAndCutoffsSheet(
                        viewModel = viewModel,
                        onDismiss = { viewModel.closeSettings() }
                    )
                }
            }
        }
    }
}
