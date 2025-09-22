package tw.edu.pu.csim.tcyang.lotto

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.min
import tw.edu.pu.csim.tcyang.lotto.ui.theme.LottoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LottoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Play(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Play(modifier: Modifier = Modifier) {
    var lucky by remember { mutableStateOf((1..100).random()) }
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { offset ->
                        // 顯示觸控座標
                        Toast.makeText(
                            context,
                            "觸控座標：X=${offset.x.toInt()}, Y=${offset.y.toInt()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "樂透數字(1~100)為 $lucky",
                modifier = Modifier
                    .padding(16.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                // 短按：減 1
                                lucky = max(1, lucky - 1)
                            },
                            onLongPress = {
                                // 長按：加 1
                                lucky = min(100, lucky + 1)
                            }
                        )
                    }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                lucky = (1..100).random()
            }) {
                Text("重新產生樂透碼")
            }
        }
    }
}