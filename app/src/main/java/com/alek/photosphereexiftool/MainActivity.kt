package com.alek.photosphereexiftool

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openImagePicker()
    }

    private fun openImagePicker() {
        ImagePicker.with(this)
                .compress(1024)         //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
                .start { resultCode, data ->
                    when (resultCode) {
                        Activity.RESULT_OK -> {
                            //Image Uri will not be null for RESULT_OK
                            val fileUri = data?.data
                            //imgProfile.setImageURI(fileUri)

                            //You can get File object from intent
                            val file: File = ImagePicker.getFile(data)!!

                            //You can also get File Path from intent
                            val filePath: String = ImagePicker.getFilePath(data)!!
                        }
                        ImagePicker.RESULT_ERROR -> {
                            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(this, R.string.task_cancelled, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
    }
}