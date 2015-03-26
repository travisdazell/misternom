package travisdazell.net.misternom.framework.impl;

import android.graphics.Bitmap;

import travisdazell.net.misternom.framework.Graphics;
import travisdazell.net.misternom.framework.Pixmap;

/**
 * Created by Travis_Dazell on 3/25/2015.
 */
public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    Graphics.PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    public int getWidth() {
        return bitmap.getWidth();
    }

    public int getHeight() {
        return bitmap.getHeight();
    }

    public Graphics.PixmapFormat getFormat() {
        return format;
    }

    public void dispose() {
        bitmap.recycle();
    }
}
