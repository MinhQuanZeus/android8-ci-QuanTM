package utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Created by QuanT on 3/6/2017.
 */
public class AFrameOnImage {
    private boolean enablePaintRect = false;

    private int[] DimsBounds = new int[4];

    public AFrameOnImage(int xOnImage, int yOnImage, int w, int h) {
        DimsBounds[0] = xOnImage;
        DimsBounds[1] = yOnImage;
        DimsBounds[2] = w;
        DimsBounds[3] = h;
    }

    public void VisibleRectDebug(boolean enable) {
        enablePaintRect = enable;
    }

    public int[] GetBounds() {
        return DimsBounds;
    }

    public void Paint(int x, int y, BufferedImage image, Graphics g, int anchor, float rotation) {


        BufferedImage imageDraw = image.getSubimage(DimsBounds[0], DimsBounds[1], DimsBounds[2], DimsBounds[3]);

        AffineTransform tx = new AffineTransform();
        tx.rotate(rotation, imageDraw.getWidth() / 2, imageDraw.getHeight() / 2);

        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        imageDraw = op.filter(imageDraw, null);


        g.drawImage(imageDraw, x, y, null);

        if (enablePaintRect) PaintBound(g);
    }

    private void PaintBound(Graphics g) {

    }
}
