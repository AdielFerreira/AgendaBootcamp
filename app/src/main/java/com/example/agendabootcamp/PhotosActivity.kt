package com.example.agendabootcamp

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_photos.*

class PhotosActivity : AppCompatActivity() {

    private var image_uri: Uri? = null

    companion object {
        private val PERMISSION_CODE_IMAGE_PICK = 1000
        private val IMAGE_PICK_CODE = 1001

        private val PERMISSION_CODE_CAMERA = 2000
        private val PERMISSION_CODE_CAMERA_OPEN = 2001

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        pick_button.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED){
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permission,PERMISSION_CODE_IMAGE_PICK)
                }else{
                    pickImageFromGallery()
                }
            }else{
                pickImageFromGallery()
            }
        }
        camera_button.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED
                    || (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED)){

                    val permission = arrayOf(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE_CAMERA)

                }else{
                    openCamera()
                }
            }else{
                openCamera()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {

        when(requestCode){
            PERMISSION_CODE_IMAGE_PICK ->{
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }else{
                    Toast.makeText(this,"Permissao Negada!",Toast.LENGTH_LONG).show()
                }
            }

            PERMISSION_CODE_CAMERA ->{
                if(grantResults.size > 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && (grantResults[1] == PackageManager.PERMISSION_GRANTED) ){
                    openCamera()
                }else{
                    Toast.makeText(this,"Permissao Negada!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type  = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            image_view.setImageURI(data?.data)
        }

        if(resultCode == Activity.RESULT_OK && requestCode == PERMISSION_CODE_CAMERA_OPEN){
            image_view.setImageURI(image_uri)
            Log.i(TAG, "onActivityResult: RESULTADO DA IMAGEM enviada para a imageview")
        }
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE,"nova photo")
        values.put(MediaStore.Images.Media.DESCRIPTION,"nova photo tirada a partir do app bootcamp")

        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)

        val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri)

        startActivityForResult(camera_intent, PERMISSION_CODE_CAMERA_OPEN)
    }
}