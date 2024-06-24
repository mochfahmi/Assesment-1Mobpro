package org.d3if3134.assesment1mobpro.ui.theme.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3134.assesment1mobpro.R
import org.d3if3134.assesment1mobpro.navigation.Screen
import org.d3if3134.assesment1mobpro.ui.theme.Assesment1MobproTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(modifier: Modifier) {
    var merek by rememberSaveable { mutableStateOf("") }
    var merkError by rememberSaveable { mutableStateOf(false) }
    var jenis by rememberSaveable { mutableStateOf("") }
    var jenisError by rememberSaveable { mutableStateOf(false) }
    var Pilihan1 by rememberSaveable { mutableStateOf(false) }
    var Pilihan2 by rememberSaveable { mutableStateOf(false) }
    var Pilihan3 by rememberSaveable { mutableStateOf(false) }
    var Pilihan4 by rememberSaveable { mutableStateOf(false) }
    var Pilihan5 by rememberSaveable { mutableStateOf(false) }
    var Pilihan6 by rememberSaveable { mutableStateOf(false) }
    var Pilihan7 by rememberSaveable { mutableStateOf(false) }
    var Pilihan8 by rememberSaveable { mutableStateOf(false) }

    var isExpanded by remember {
        mutableStateOf(false)
    }
    var isInputValid by rememberSaveable { mutableStateOf(true) }

    val radioOptions = listOf(
        stringResource(id = R.string.ban_racing),
        stringResource(id = R.string.ban_standar)
    )
    val context = LocalContext.current



    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = R.string.app_intro),
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown untuk merek
        Box(contentAlignment = Alignment.Center) {
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = {isExpanded = it})
            {
                TextField(
                    value = merek,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {},
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Corsa")}, onClick = {
                        merek = "Corsa"
                        isExpanded = false
                    })
                    DropdownMenuItem(text = { Text(text = "Pirelli")}, onClick = {
                        merek = "Pirelli"
                        isExpanded = false
                    })
                }
            }
        }

        // Supporting text jika dropdown kosong
        if (merkError) {
            Text(
                text = stringResource(id = R.string.error_merk),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Text(text = stringResource(id = R.string.pilih_jenis), style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp))

        // Radio buttons untuk tipe ban
        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
            verticalAlignment =  Alignment.CenterVertically
        ) {
            radioOptions.forEach { text ->
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = jenis == text, // Menggunakan jenis sebagai pembanding
                            onClick = {
                                jenis = text // Mengatur jenis berdasarkan text yang dipilih
                            },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                ) {
                    RadioButton(selected = jenis == text, onClick = null)
                    Text(text = text)
                }
            }
        }

        if (jenisError) {
            Text(
                text = stringResource(id = R.string.error_merk),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }


        // Button menampilkan pilihan
        Button(
            onClick = {
                merkError = (merek == "")
                jenisError = (jenis == "")
                // Reset input validation
                isInputValid = true

                // Validate input
                if (merek.isEmpty() || jenis.isEmpty()) {
                    isInputValid = false
                } else {
                    // Menampilkan pilihan sesuai inputan
//                    PILIHAN 2
                    Pilihan1 = (merek.lowercase() == "corsa" && jenis.lowercase() == "racing")
                    Pilihan5 = (merek.lowercase() == "corsa" && jenis.lowercase() == "balap")
//                    PILIHAN 2
                    Pilihan2 = (merek.lowercase() == "corsa" && jenis.lowercase() == "standard")
                    Pilihan6 = (merek.lowercase() == "corsa" && jenis.lowercase() == "standar")
//                    PILIHAN 3
                    Pilihan3 = (merek.lowercase() == "pirelli" && jenis.lowercase() == "racing")
                    Pilihan7 = (merek.lowercase() == "pirelli" && jenis.lowercase() == "balap")
//                    PILIHAN 4
                    Pilihan4 = (merek.lowercase() == "pirelli" && jenis.lowercase() == "standard")
                    Pilihan8 = (merek.lowercase() == "pirelli" && jenis.lowercase() == "standar")
                }
            },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(text = stringResource(R.string.cari_ban))
        }
        // Show input validation message if input is not valid
        if (!isInputValid) {
            Text(
                text = "Input tidak valid, mohon isi kedua inputan.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Recommendations section
        if (Pilihan1) {
            CorsaRacing()
        }
        if (Pilihan2) {
            CorsaStandar()
        }
        if (Pilihan3) {
            PirelliRacing()
        }
        if (Pilihan4) {
            PirelliStandar()
        }

    }
}

@Composable
fun ErrorHint(isError: Boolean){
    if (isError){
        Text(text = stringResource(R.string.invalid_input))
    }
}

private fun shareData(context: Context, massage: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, massage)
    }
    if(shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

@Composable
fun CorsaRacing() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 50.dp)
    ) {
        Text(text = stringResource(id = R.string.corsa_racing),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.corsa),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = stringResource(id = R.string.isi_corsa_racing),
            modifier = Modifier.padding(top = 40.dp))
    }
    Button(
        onClick = {
            shareData(
                context = context,
                massage = context.getString(R.string.bagikan).uppercase())
        },
        modifier = Modifier.padding(top = 8.dp),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(text = stringResource(R.string.bagikan))
    }
}

@Composable
fun CorsaStandar() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 50.dp)
    ) {
        Text(text = stringResource(id = R.string.corsa_standar),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.corsa),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = stringResource(id = R.string.isi_corsa_standar),
            modifier = Modifier.padding(top = 40.dp))
    }
    Button(
        onClick = {
            shareData(
                context = context,
                massage = context.getString(R.string.bagikan).uppercase())
        },
        modifier = Modifier.padding(top = 8.dp),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(text = stringResource(R.string.bagikan))
    }
}

@Composable
fun PirelliRacing() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 50.dp)
    ) {
        Text(text = stringResource(id = R.string.Pirelli_racing),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.pirelli),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .padding(20.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = stringResource(id = R.string.isi_Pirelli_racing),
            modifier = Modifier.padding(top = 40.dp))
    }
    Button(
        onClick = {
            shareData(
                context = context,
                massage = context.getString(R.string.bagikan).uppercase())
        },
        modifier = Modifier.padding(top = 8.dp),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(text = stringResource(R.string.bagikan))
    }
}

@Composable
fun PirelliStandar() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 50.dp)
    ) {
        Text(text = stringResource(id = R.string.Pirelli_standar),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.pirelli),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .padding(20.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = stringResource(id = R.string.isi_Pirelli_standar),
            modifier = Modifier.padding(top = 40.dp))
    }
    Button(
        onClick = {
            shareData(
                context = context,
                massage = context.getString(R.string.bagikan).uppercase())
        },
        modifier = Modifier.padding(top = 8.dp),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(text = stringResource(R.string.bagikan))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    Assesment1MobproTheme {
        MainScreen(rememberNavController())
    }
}