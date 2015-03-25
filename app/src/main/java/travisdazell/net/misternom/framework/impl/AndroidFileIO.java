package travisdazell.net.misternom.framework.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import travisdazell.net.misternom.framework.FileIO;

/**
 * Created by Travis_Dazell on 3/23/2015.
 */
public class AndroidFileIO implements FileIO {
    Context context; /* we store a reference to the calling Context */
    AssetManager assets; /* we store an AssetManager, which is pulled from the Context */
    String externalStoragePath; /* we store the external storage's path */

    public AndroidFileIO(Context context) {
        this.context = context;
        this.assets = context.getAssets();
        this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    public InputStream readAsset(String fileName) throws IOException {
        return assets.open(fileName);
    }

    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(externalStoragePath + fileName);
    }

    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(externalStoragePath + fileName);
    }

    public SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
