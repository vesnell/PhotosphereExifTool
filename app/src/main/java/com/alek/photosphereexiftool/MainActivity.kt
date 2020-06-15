package com.alek.photosphereexiftool

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.content_gallery.*
import java.io.File

class MainActivity : AppCompatActivity() {

    companion object {
        private const val GALLERY_IMAGE_REQ_CODE = 102
    }

    private var mGalleryFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            Log.e("TAG", "Path:${ImagePicker.getFilePath(data)}")
            // File object will not be null for RESULT_OK
            val file = ImagePicker.getFile(data)!!
            when (requestCode) {
                GALLERY_IMAGE_REQ_CODE -> {
                    mGalleryFile = file
                    imgGallery.setLocalImage(file)
                }
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.task_cancelled, Toast.LENGTH_SHORT).show()
        }
    }

    fun pickGalleryImage(view: View) {
        ImagePicker.with(this)
            // User can only select image from Gallery
            .galleryOnly()

            .galleryMimeTypes(  //no gif images at all
                mimeTypes = arrayOf(
                    "image/png",
                    "image/jpg",
                    "image/jpeg"
                )
            )
            // Image resolution will be less than 1080 x 1920
            .maxResultSize(1080, 1920)
            .start(GALLERY_IMAGE_REQ_CODE)
    }
}