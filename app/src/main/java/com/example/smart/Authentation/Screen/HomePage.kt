package com.example.smart.Authentation.Screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.smart.Authentation.ViewModel.AuthState
import com.example.smart.Authentation.ViewModel.AuthViewModel
import com.example.smart.MultiLingual.Language
import com.example.smart.MultiLingual.LanguageManager
import com.example.smart.MultiLingual.LocalizationHelper
import com.example.smart.MultiLingual.LocalizationHelper.getCommentsText
import com.example.smart.MultiLingual.multilingualViewModel
import com.example.smart.R
import kotlinx.coroutines.launch


data class Feature(
    val id: Int,
    val nameKey: Int,
    val icon: ImageVector,
    val gradient: List<Color>,
    val badgeKey: String? = null,
    val route: String = "",
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    languageManager: LanguageManager,
    navController: NavController,
    authViewModel: AuthViewModel,
    viewModel: multilingualViewModel = viewModel(),
) {

    data class DrawerItemss(
        val id: Int,
        val textKey: Int,
        val icon: ImageVector,
        val badgeCount: Int,
        val hasBadge: Boolean,
        val route: String,
    )

    data class DrawerItemsss(
        val icon: ImageVector,
        val text: String,
        val badgeCount: Int,
        val hasBadge: Boolean,
        val route: String,
    )

    val currentLanguage = viewModel.selectedLanguage.value.code
    val drawerItem = remember(currentLanguage) {
        listOf(
            DrawerItems(1, 1, Icons.Default.Face, 0, false, "Profile"),
            DrawerItems(2, 2, Icons.Default.Email, 0, true, "Inbox"),
            DrawerItems(3, 3, Icons.Default.Agriculture, 0, false, "MyFarm"),
            DrawerItems(4, 4, Icons.Default.BarChart, 0, false, "Analytics"),
            DrawerItems(5, 5, Icons.Default.Favorite, 20, true, "Favorite"),
            DrawerItems(6, 6, Icons.Default.Help, 0, false, "Help"),
            DrawerItems(7, 7, Icons.Default.Info, 0, false, "About"),
            DrawerItems(8, 8, Icons.Default.Settings, 0, false, "About"),
            DrawerItems(9, 9, Icons.Default.Logout, 0, false, "Logout")
        )
    }

    // val currentLanguage = viewModel.selectedLanguage.value.code //new

    var selectedItem by remember { mutableStateOf(drawerItem[0]) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {

                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color(0xFF43A047)),
                        contentAlignment = Alignment.Center //new color
                    ) {
                        Column(
                            Modifier.wrapContentSize(),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            Image(
                                painter = painterResource(R.drawable.ashu),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(130.dp)
                                    .clip(CircleShape)
                            )

                            Text(
                                text = "Ashu Gupta",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center
                            )
                        }


                        Divider(
                            Modifier.align(Alignment.BottomCenter), thickness = 1.dp,
                            Color.DarkGray
                        )
                    }

                    drawerItem.forEach {
                        NavigationDrawerItem(
                            label = { Text(text = LocalizationHelper.getDrawerItemName(currentLanguage, it.textKey)) },
                            selected = it == selectedItem,
                            onClick = {
                                selectedItem = it

                                if ("Logout" != it.route) {
                                    navController.navigate(it.route)
                                } else {
                                    authViewModel.signout()
                                }
                                scope.launch {
                                    drawerState.close()
                                }


                            },
                            modifier = Modifier.padding(horizontal = 20.dp),
                            icon = {
                                Icon(imageVector = it.icon, contentDescription = null)
                            },
                            badge = {
                                if (it.hasBadge) {
                                    BadgedBox(badge = {
                                        Badge {
                                            Text(text = it.badgeCount.toString(), fontSize = 17.sp)
                                        }
                                    }) {

                                    }
                                }
                            }
                        )
                    }

                }

            }
        }, drawerState = drawerState
    ) {

        // main code

        val features = remember(currentLanguage) { // Recompose when language changes
            listOf(
                Feature(
                    1, 1, Icons.Default.Cloud,
                    listOf(Color(0xFF42A5F5), Color(0xFF1E88E5)), "live", "/weather"
                ),
                Feature(
                    2, 2, Icons.Default.Grass,
                    listOf(Color(0xFF66BB6A), Color(0xFF43A047)), null, "/soil-test"
                ),
                Feature(
                    4, 4, Icons.Default.AutoAwesome,
                    listOf(Color(0xFFAB47BC), Color(0xFF8E24AA)), "smart", "/ai-advisor"
                ),
                Feature(
                    3, 3, Icons.Default.Description,
                    listOf(Color(0xFFFFA726), Color(0xFFFB8C00)), "new_3", "/soil-report"
                ),
                Feature(
                    6, 6, Icons.Default.TrendingUp,
                    listOf(Color(0xFF26A69A), Color(0xFF00897B)), "updated", "/market"
                ),
                Feature(
                    7, 7, Icons.Default.CameraAlt,
                    listOf(Color(0xFF5C6BC0), Color(0xFF3949AB)), null, "/camera"
                ),
                Feature(
                    8, 8, Icons.Default.Article,
                    listOf(Color(0xFFEF5350), Color(0xFFE53935)), "new_5", "/news"
                ),
                Feature(
                    5, 5, Icons.Default.SupportAgent,
                    listOf(Color(0xFFEC407A), Color(0xFFD81B60)), null, "/support"
                ),
            )
        }


//        val featuress = remember {
//            listOf(
//                Feature(
//                    1, "Weather", Icons.Default.Cloud,
//                    listOf(Color(0xFF42A5F5), Color(0xFF1E88E5)), "Live", "/weather"
//                ),
//                Feature(
//                    2, "Soil Test", Icons.Default.Grass,
//                    listOf(Color(0xFF66BB6A), Color(0xFF43A047)), null, "/soil-test"
//                ),
//
//                Feature(
//                    4, "AI Crop Advisor", Icons.Default.AutoAwesome,
//                    listOf(Color(0xFFAB47BC), Color(0xFF8E24AA)), "Smart", "/ai-advisor"
//                ),
//                Feature(
//                    3, "Soil Report", Icons.Default.Description,
//                    listOf(Color(0xFFFFA726), Color(0xFFFB8C00)), "3 New", "/soil-report"
//                ),
//                Feature(
//                    6, "Market Price", Icons.Default.TrendingUp,
//                    listOf(Color(0xFF26A69A), Color(0xFF00897B)), "Updated", "/market"
//                ),
//
//
//                Feature(
//                    7, "Camera", Icons.Default.CameraAlt,
//                    listOf(Color(0xFF5C6BC0), Color(0xFF3949AB)), null, "/camera"
//                ),
//                Feature(
//                    8, "News", Icons.Default.Article,
//                    listOf(Color(0xFFEF5350), Color(0xFFE53935)), "5 New", "/news"
//                ),
//                Feature(
//                    5, "Support", Icons.Default.SupportAgent,
//                    listOf(Color(0xFFEC407A), Color(0xFFD81B60)), null, "/support"
//                ),
//            )
//        }


        val authState = authViewModel.authState.observeAsState()
        LaunchedEffect(authState.value) {
            when (authState.value) {//authScreen
                is AuthState.Unauthenticated -> navController.navigate("loginRoutes")
                else -> Unit
            }
        }


        LaunchedEffect(Unit) {
            val savedLanguage = languageManager.getLanguage()
            viewModel.initializeLanguage(savedLanguage)
        }

        LaunchedEffect(viewModel.selectedLanguage.value) {
            languageManager.setLanguage(viewModel.selectedLanguage.value.code)
        }


        key(viewModel.recompositionTrigger.value) {
            Column(modifier = Modifier.fillMaxSize()) {
                TopAppBar(
                    colors = topAppBarColors(
                        // containerColor = MaterialTheme.colorScheme.primaryContainer,
                        containerColor = Color(0xFF43A047),
                        titleContentColor = Color(0xFF000000),
                    ),

                    title = {
                        Row {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = LocalizationHelper.getAppName(viewModel.selectedLanguage.value.code),
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Medium,
                                style = MaterialTheme.typography.titleLarge,


                                )
                        }
                    },
                    actions = {
                        LanguageSelector(viewModel)
                    }
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(features) { it ->

                        FeatureCard(
                            feature = it,
                            onClick = { navController.navigate(it.route) },
                            languageCode = viewModel.selectedLanguage.value.code

                        )


                    }


                }
            }

        }
    }


}


data class DrawerItems(
    val id: Int,
    val textKey: Int,
    val icon: ImageVector,
    val badgeCount: Int,
    val hasBadge: Boolean,
    val route: String,
)

@Composable
fun LanguageSelector(viewModel: multilingualViewModel) {
    Box {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clickable { viewModel.toggleLanguageDropdown() },
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(viewModel.selectedLanguage.value.flag)
            Text(viewModel.selectedLanguage.value.name)
            Icon(
                imageVector = if (viewModel.showLanguageDropdown.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = viewModel.showLanguageDropdown.value,
            onDismissRequest = { viewModel.closeLanguageDropdown() }
        ) {
            Language.Companion.availableLanguages.forEach { language ->
                DropdownMenuItem(
                    text = { Text("${language.flag} ${language.name}") },
                    onClick = { viewModel.selectLanguage(language) }
                )
            }
        }
    }
}


@Composable
fun FeatureCard(feature: Feature, onClick: () -> Unit, languageCode: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            focusedElevation = 20.dp
        )

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = feature.gradient.map { Color(it.value) }
                    )
                )
                .padding(16.dp)
        ) {

            feature.badgeKey?.let { badgeKey ->
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White.copy(alpha = 0.3f)
                ) {
                    Text(
                        text = LocalizationHelper.getBadgeText(languageCode, badgeKey),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = feature.icon,
                    contentDescription = LocalizationHelper.getFeatureName(
                        languageCode,
                        feature.nameKey
                    ),
                    modifier = Modifier.size(40.dp),
                    tint = Color.White.copy(alpha = 0.9f)
                )
                Text(
                    text = LocalizationHelper.getFeatureName(languageCode, feature.nameKey),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis

                )


            }


        }

    }
}