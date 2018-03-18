package com.example.rzknzl.rizkinuzuli_1202154225_modul4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class ListMahasiswaActivity extends AppCompatActivity {

    private ListView mList;
    private Button mLoadButton;
    private ProgressDialog mDialogLoad;
    private ArrayAdapter mNameAdapter;
    private Parcelable mListViewScroll = null;
    private int mDataLoaded;
    private String mNameList[] = {"Natsu","Lucy","Gray","Erza","Mirajane","Gajeel","Juvia",
            "Elfman","Lisanna","Wendy","Mavis","Jelal","Sting","Rogue","Minerva"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        mList = findViewById(R.id.lvwListNama);
        mLoadButton = findViewById(R.id.btnMulai);

        mList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>()));

        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadTask().execute();
            }
        });
    }

    private class LoadTask extends AsyncTask<Void, String, String> {

        @Override
        protected void onPreExecute() {
            mNameAdapter = (ArrayAdapter<String>)mList.getAdapter();

            mDialogLoad = new ProgressDialog(ListMahasiswaActivity.this);
            mDialogLoad.setTitle("Loading ...");
            mDialogLoad.setMax(15);
            mDialogLoad.setProgress(0);
            mDialogLoad.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mDialogLoad.show();
            mDataLoaded = 0;
        }

        @Override
        protected String doInBackground(Void... voids) {
            for (String mNama : mNameList) {
                publishProgress(mNama);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "asad";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mNameAdapter.add(values[0]);
            mDataLoaded++;
            mDialogLoad.setProgress(mDataLoaded);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mDialogLoad.dismiss();
        }
    }
}
