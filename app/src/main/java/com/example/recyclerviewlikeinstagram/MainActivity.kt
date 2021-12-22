package com.example.recyclerviewlikeinstagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RectangleRecyclerView

    private lateinit var rectAdapter: RectangleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoUrls = listOf<String>(
            "https://vod-progressive.akamaized.net/exp=1640173193~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F4455%2F18%2F472276540%2F2103499597.mp4~hmac=cb83fd4e2b3535c18cabbe5dc4334c2e60822ed412779accde385c5ce48a792f/vimeo-prod-skyfire-std-us/01/4455/18/472276540/2103499597.mp4?filename=pexels-ekaterina-bolovtsova-5703372.mp4",
            "https://vod-progressive.akamaized.net/exp=1640173634~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F1297%2F20%2F506486365%2F2323657856.mp4~hmac=ecd61306a30c9553549570c022b612e7879b6ef4a226cebed5e0286b5759f945/vimeo-prod-skyfire-std-us/01/1297/20/506486365/2323657856.mp4?filename=pexels-ekaterina-bolovtsova-6650177.mp4",
            "https://vod-progressive.akamaized.net/exp=1640173725~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F3648%2F24%2F618242352%2F2869653185.mp4~hmac=b1fbe1174e830095091a73537ee97fc0d22616112c8ac662be2835199c6ede32/vimeo-prod-skyfire-std-us/01/3648/24/618242352/2869653185.mp4?filename=pexels-nikolai-lapshin-9724998.mp4",
            "https://vod-progressive.akamaized.net/exp=1640173968~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F3554%2F23%2F592772975%2F2793338102.mp4~hmac=da778ea2b73ba97b04bf6018a0a7262c0d573039db1dea6e5422149ee6dbc223/vimeo-prod-skyfire-std-us/01/3554/23/592772975/2793338102.mp4?filename=pexels-melike-b-9210539.mp4",
            "https://vod-progressive.akamaized.net/exp=1640174173~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F2754%2F18%2F463773123%2F2055347269.mp4~hmac=e4f32a001bacbfe095c001a464a0074cb6978833fdf8bfdf2026d8fff79dd3a7/vimeo-prod-skyfire-std-us/01/2754/18/463773123/2055347269.mp4?filename=pexels-cottonbro-5491482.mp4",
            "https://vod-progressive.akamaized.net/exp=1640174255~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F2712%2F21%2F538563909%2F2552314257.mp4~hmac=837a3c95b543eb69b6183b4470777cb26d679bc9f649939f59b4b573a4011338/vimeo-prod-skyfire-std-us/01/2712/21/538563909/2552314257.mp4?filename=pexels-mart-production-7565441.mp4",
            "https://vod-progressive.akamaized.net/exp=1640174365~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F3783%2F17%2F443919028%2F1944645616.mp4~hmac=6af98c13a23e100ea98bb6517a5238151841e963d89688cbda1a509e147a291a/vimeo-prod-skyfire-std-us/01/3783/17/443919028/1944645616.mp4?filename=production+ID%3A5007874.mp4"
        )

        rectAdapter = RectangleAdapter(videoUrls)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setUpVideoUrls(videoUrls)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rectAdapter
        }
    }
}