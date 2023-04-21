package dex.alpha.dormdynamo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import dex.alpha.dormdynamo.student.StudentDashboard
import dex.alpha.dormdynamo.warden.WardenDashboard


class GlobalLogin : AppCompatActivity() {

    private lateinit var imagePager: ViewPager2
    private lateinit var imageList: List<Int>
    lateinit var btnExit: Button
    lateinit var studentLoginBtn: CardView
    lateinit var wardenLoginBtn: CardView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_login)

        btnExit = findViewById(R.id.exitButton)
        btnExit.setOnClickListener(){
            finish()
        }

        studentLoginBtn = findViewById(R.id.student_login_button)
        studentLoginBtn.setOnClickListener(){
            val intent = Intent(this, StudentDashboard::class.java)
            startActivity(intent)
        }

        wardenLoginBtn = findViewById(R.id.warden_login_button)
        wardenLoginBtn.setOnClickListener(){
            val intent = Intent(this, WardenDashboard::class.java)
            startActivity(intent)
        }

        imagePager = findViewById(R.id.imagePager)

        imageList = listOf(
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3,
            R.drawable.banner4
        )

        val adapter = ImagePagerAdapter(imageList)
        imagePager.adapter = adapter

        startImageSliderTimer()
    }

    private fun startImageSliderTimer() {
        val handler = Handler()
        val updateImageSliderTask = object : Runnable {
            override fun run() {
                val currentPage = imagePager.currentItem
                var nextPage = currentPage + 1
                if (nextPage >= imageList.size) {
                    nextPage = 0
                }
                imagePager.currentItem = nextPage
                handler.postDelayed(this, 4000)
            }
        }
        handler.postDelayed(updateImageSliderTask, 4000)
    }


    class ImagePagerAdapter(private val imageList: List<Int>) :
        RecyclerView.Adapter<ImagePagerAdapter.ImagePagerViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePagerViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image_pager, parent, false)
            return ImagePagerViewHolder(view)
        }

        override fun onBindViewHolder(holder: ImagePagerViewHolder, position: Int) {
            val imageRes = imageList[position]
            holder.bind(imageRes)
        }

        override fun getItemCount(): Int {
            return imageList.size
        }

        inner class ImagePagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.imageView)

            fun bind(imageRes: Int) {
                Glide.with(itemView.context)
                    .load(imageRes)
                    .into(imageView)
            }
        }
    }

}

