package com.example.kenrig.a10_internalstorage;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "file.txt";

    TextView tvdisplay;
    EditText edittxtdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvdisplay = (TextView) findViewById(R.id.textView);
        edittxtdata = (EditText) findViewById(R.id.editText);
    }

    public void writeInternalStorage(View view)
    {
        String message = edittxtdata.getText().toString();
        try(FileOutputStream fos = openFileOutput(FILE_NAME,MODE_PRIVATE))
        {
            fos.write(message.getBytes());
            edittxtdata.getText().clear();
            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
        }
        catch (FileNotFoundException fex)
        {
            Toast.makeText(getApplicationContext(),"File not found",Toast.LENGTH_SHORT).show();
            fex.printStackTrace();
        }

        catch (IOException ioex)
        {
            Toast.makeText(getApplicationContext(),"IO Exception",Toast.LENGTH_SHORT).show();
            ioex.printStackTrace();
        }

    }

    public void readInternalStorage(View view)
    {
        String message="";
        FileInputStream fis=null;
        InputStreamReader isr=null;
        BufferedReader buffreader=null;
        StringBuffer sb;
        try
        {
            fis = openFileInput(FILE_NAME);
            isr = new InputStreamReader(fis);
            buffreader = new BufferedReader(isr);
            sb = new StringBuffer();

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
        finally {

            try {
                fis.close();
                isr.close();
                buffreader.close();
            } catch (IOException e) {
                Toast.makeText(this, "Exception occured", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
