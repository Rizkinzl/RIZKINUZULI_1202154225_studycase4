package com.example.rzknzl.rizkinuzuli_1202154225_modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CariGambarActivity extends AppCompatActivity {

    //Deklarasi Variabel
    EditText mImageUrl;
    Button mLoadImage;
    ImageView mImage;
    ProgressDialog mDialogLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);
        setTitle("Cari Gambar");

        //Referencing variable id pada layout
        mImageUrl = findViewById(R.id.edtUrlGambar);
        mLoadImage = findViewById(R.id.btnKlikUrl);
        mImage = findViewById(R.id.gbrUrl);
        mLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load(); 
            }
        });

    }

    private void load() {
        String URLGambar = mImageUrl.getText().toString();
        new LoadImage().execute(URLGambar);
    }

    private class LoadImage extends AsyncTask<String, Integer, Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialogLoad = new ProgressDialog(CariGambarActivity.this);
            mDialogLoad.setMessage("Loading ...");
            mDialogLoad.setMax(100);
            mDialogLoad.incrementProgressBy(1);
            mDialogLoad.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                // Me-Load gambar dari url
                URL url = new URL(params[0]);
                // menjadikan input dari url ke bitmap
                bitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
            } catch (IOException e) {
                Log.e("doInBackground() - ", e.getMessage());
            }
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            try {
                Thread.sleep(1000);
                mDialogLoad.setMessage("Fetching...");
                mDialogLoad.incrementProgressBy(values[0]);
                mDialogLoad.setProgress(values[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mImage.setImageBitmap(bitmap);
            mDialogLoad.dismiss();
        }
    }
}
