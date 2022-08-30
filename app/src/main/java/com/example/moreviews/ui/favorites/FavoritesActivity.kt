package com.example.moreviews.ui.favorites

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.moreviews.R
import com.example.moreviews.data.FavoriteData
import com.example.moreviews.data.Repository
import com.example.moreviews.databinding.ActivityFavoritesBinding
import com.example.moreviews.databinding.FavoritesItemBinding
import java.io.File
import java.io.FileOutputStream

class FavoritesActivity : AppCompatActivity(), FavoritesViewHolder.OnClickListener {

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var favoritesVPAdapter: FavoritesVPAdapter
    private val repository = Repository.getInstance()
    private var tempImageFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        favoritesVPAdapter = FavoritesVPAdapter(repository.getFavorites(), this)

        initializeViewPager()
    }

    override fun onResume() {
        super.onResume()
        tempImageFile?.let {
            if (it.exists()) {
                it.delete()
            }
            tempImageFile = null
        }
    }

    override fun onShareClick(binding: FavoritesItemBinding, item: FavoriteData) {
        val imgBitmap = getBitmapFromView(binding.ivFavorites)
        tempImageFile = createTempImageFromBitmap(imgBitmap)

        tempImageFile?.let {
            Intent(Intent.ACTION_SEND).apply {
                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(this@FavoritesActivity, "$packageName.fileprovider", it))
                startActivity(Intent.createChooser(this, "Share via..."))
            }
        } ?: Toast.makeText(this, "Oops some error occurred", Toast.LENGTH_SHORT).show()

    }

    private fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(
            view.width,
            view.height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun createTempImageFromBitmap(bitmap: Bitmap): File? {
        try {
            val imgFile = File.createTempFile(
                "image",
                ".jpg",
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            )
            val fileOutputStream = FileOutputStream(imgFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.close()

            return imgFile
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun initializeViewPager() {
        binding.vpFavorites.apply {
            adapter = favoritesVPAdapter
            orientation = ViewPager2.ORIENTATION_VERTICAL
        }
    }

    override fun onLikeClick(binding: FavoritesItemBinding, item: FavoriteData) {
        item.liked = !item.liked
        if (item.liked) {
            binding.ibLike.setImageResource(R.drawable.ic_like)
        } else {
            binding.ibLike.setImageResource(R.drawable.ic_like_outlined)
        }
    }


}