package com.akashevpavel.implicitintents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.app.ShareCompat

class MainActivity : AppCompatActivity() {
    private lateinit var mWebSiteEditText: EditText
    private lateinit var mLocationEditText: EditText
    private lateinit var mShareEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mWebSiteEditText = findViewById(R.id.website_edittext)
        mLocationEditText = findViewById(R.id.location_edittext)
        mShareEditText = findViewById(R.id.share_edittext)
    }

    fun openWebsite(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mWebSiteEditText.text.toString()))
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this!")
        }
    }
    fun openLocation(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + mLocationEditText.text.toString()))
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this!")
        }
    }
    fun shareText(view: View) {

        ShareCompat.IntentBuilder
            .from(this)
            .setType("text/plain")
            .setChooserTitle(R.string.share_text_with)
            .setText(mShareEditText.text.toString())
            .startChooser()
    }

    fun takePicture(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this!")
        }
    }
}