package com.example.joshf.conc;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshf on 2016/10/24.
 */

public final class InternalStorage{
    ArrayList<Player> savedArrayList = null;

    private InternalStorage() {}

    public static void writeObject(Context context, String key, ArrayList<?> object) throws IOException {
/*        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"MyArray.ply");
        Log.e("path", Environment.getExternalStorageDirectory().getAbsolutePath());
        if (!f.exists()) {
            f.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(object);
        fos.close();*/

        //String tempFile = null;
      //  for (Player player : object) {
            FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
       // }
    }

    public static Object  readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
/*        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"MyArray.srl");
        ArrayList<Player> ReturnClass = null;

        ObjectInputStream in=null;

        in = new ObjectInputStream(new FileInputStream(f));
        ReturnClass = (ArrayList<Player>) in.readObject();*/

        FileInputStream fis = context.openFileInput (key);
        ObjectInputStream ois = new ObjectInputStream (fis);
        Object object = ois.readObject ();

        return object;
    }
}