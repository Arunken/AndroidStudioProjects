package com.example.kenrig.a9_externalstoragewrite;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView tvdisplay;
    EditText edittxtdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        tvdisplay = (TextView) findViewById(R.id.textView);
        edittxtdata = (EditText) findViewById(R.id.editText);

    }

    public void readExternalStorage(View view)
    {
        File root = Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath(),"mydata");
        File file = new File(dir,"MyMessage.txt");
        String message;
        try
        {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buffreader = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();

            while((message=buffreader.readLine())!=null)
            {
                sb.append(message+"\n");
            }
            tvdisplay.setText(sb.toString());
        }
        catch (FileNotFoundException ex)
        {
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
        }
        catch (IOException exio)
        {
            Toast.makeText(this, "IO Exception", Toast.LENGTH_SHORT).show();
        }

    }

    public  void writeExternalStorage(View view)
    {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state))
        {
            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath()+"/mydata");
            //boolean exists1= root.canWrite();
            //Log.i("dir writable ? :",String.valueOf(exists1));

            if(!dir.exists())
            {
                boolean b =dir.mkdir();
            }

            File file = new File(dir,"MyMessage.txt");
            String message = edittxtdata.getText().toString();
            try(FileOutputStream fop = new FileOutputStream(file))
            {

                fop.write(message.getBytes());
                edittxtdata.setText("");
                Toast.makeText(this, "Message saved", Toast.LENGTH_SHORT).show();
            }
            catch (FileNotFoundException ex)
            {
                ex.printStackTrace();
                Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
            }
            catch (IOException exio)
            {
                exio.printStackTrace();
                Toast.makeText(this, "IO Exception", Toast.LENGTH_SHORT).show();
            }
        }
        else
            {
                Toast.makeText(getApplicationContext(),"SD card not found!",Toast.LENGTH_SHORT).show();
            }

    }
}
