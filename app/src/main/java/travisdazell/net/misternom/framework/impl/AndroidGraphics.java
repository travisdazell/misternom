package travisdazell.net.misternom.framework.impl;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;

import travisdazell.net.misternom.framework.Graphics;
import travisdazell.net.misternom.framework.Pixmap;

/**
 * Created by Travis_Dazell on 3/25/2015.
 */
public class AndroidGraphics implements Graphics {
    AssetManager assets;
    Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }

    /**
     * Loads a bitmap from an asset file using the specified PixmapFormat
     * @param fileName
     * @param format
     * @return
     */
    public Pixmap newPixmap(String fileName, PixmapFormat format) {
        // translate the PixmapFormat into one of the constants of the android.Graphics.Bitmap.Config class
        Config config = null;
        if (format == PixmapFormat.ARGB565) {
            config = Bitmap.Config.RGB_565;
        }
        else if (format == PixmapFormat.ARGB4444) {
            config = Bitmap.Config.ARGB_4444;
        }
        else {
            config = Bitmap.Config.ARGB_8888;
        }

        // load the Bitmap from the asset via the BitmapFactory
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null) {
                throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {}
            }
        }

        // set the color format of the resulting bitmap (note that Android doesn't guarantee to return
        // the same color format that we requested in the BitmapFactory options, that's why we get it here
        if (bitmap.getConfig() == Config.RGB_565) {
            format = PixmapFormat.ARGB565;
        }
        else if (bitmap.getConfig() == Config.ARGB_4444) {
            format = PixmapFormat.ARGB4444;
        }
        else {
            format = PixmapFormat.RGB8888;
        }

        // return the new Pixmap
        return new AndroidPixmap(bitmap, format);
    }

    /**
     * Extracts the red, green, and blue components of the specified 32-bit ARGB color
     * Then we clear our artificial framebuffer with the color that's specified
     * @param color
     */
    public void clear(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
    }

    /**
     * Draws an individual pixel of our framebuffer with the specified color
     * @param x
     * @param y
     * @param color
     */
    public void drawPixel(int x, int y, int color) {
        paint.setColor(color);
        canvas.drawPoint(x, y, paint);
    }

    /**
     * Draws a line of the specified color
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @param color
     */
    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + width - 1, paint);
    }

    /**
     * Draws a portion of the specified Pixmap
     * @param pixmap
     * @param x
     * @param y
     * @param srcX
     * @param srcY
     * @param srcWidth
     * @param srcHeight
     */
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight - 1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth - 1;
        dstRect.bottom = y + srcHeight - 1;

        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect, null);
    }

    public void drawPixmap(Pixmap pixmap, int x, int y) {
        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, x, y, null);
    }

    /**
     * Returns the width of the frambuffer
     * @return
     */
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    /**
     * Returns the height of the framebuffer
     * @return
     */
    public int getHeight() {
        return frameBuffer.getHeight();
    }
}
