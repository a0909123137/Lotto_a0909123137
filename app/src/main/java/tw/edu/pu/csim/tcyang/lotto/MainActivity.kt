package tw.edu.pu.csim.tcyang.lotto

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import tw.edu.pu.csim.tcyang.lotto.ui.theme.LottoTheme
import androidx.compose.runtime.setValue // 引入 setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LottoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Play(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Play(modifier: Modifier = Modifier) {
    var lucky by remember { mutableStateOf((1..100).random()) }
    var touchPosition by remember { mutableStateOf(Offset(0f, 0f)) }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                // 捕捉觸控事件
                detectTapGestures { tapOffset ->
                    // 更新觸控座標
                    touchPosition = tapOffset
                    // 顯示觸控座標
                    Toast.makeText(
                        context,
                        "觸控位置: x = ${tapOffset.x}, y = ${tapOffset.y}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "樂透數字(1-100)為 $lucky"
        )

        Button(
            onClick = { lucky = (1..100).random() }
        ) {
            Text("重新產生樂透碼")
        }

        // 顯示觸控座標
        Text(
            text = "觸控位置：x = ${touchPosition.x}, y = ${touchPosition.y}"
        )
    }
}
