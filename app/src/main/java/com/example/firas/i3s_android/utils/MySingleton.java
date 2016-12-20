package com.example.firas.i3s_android.utils;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_WORLD_READABLE;

/**
 * Created by firas on 11/10/2016.
 */

public class MySingleton {
    private static MySingleton instance;

    public static MySingleton getInstance()
    {
       if(instance==null)
           instance=new MySingleton();
        return  instance;

    }

    public void writeFile(String fileName,Context context, String data)

    { try {
        FileOutputStream fOut = null;

            fOut = context.openFileOutput(fileName,
                    MODE_WORLD_READABLE);

        OutputStreamWriter osw = new OutputStreamWriter(fOut);

        // Write the string to the file
        osw.write(data);

       /* ensure that everything is
        * really written out and close */
        osw.flush();
        osw.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
public  String readFile(String fileName,Context context)

{ String readString=null;
    try{

    FileInputStream fIn =context.openFileInput(fileName);
    InputStreamReader isr = new InputStreamReader(fIn);

        /* Prepare a char-Array that will
         * hold the chars we read back in. */
    char[] inputBuffer = new char[fIn.available()];

    // Fill the Buffer with data from the file
    isr.read(inputBuffer);

    // Transform the chars to a String
   readString = new String(inputBuffer);

    // Check if we read back the same chars that we had written out


    Log.i("File Reading stuff", "success = " + readString.toString());

} catch (IOException ioe)
    {ioe.printStackTrace();}
    return  readString;
}
}
