import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class TextureQuiltLogic {

    private BufferedImage sourceTexture;
    private int patchSize = 50;     
    private int outputScale = 2;    
    private final Random rng = new Random();

    
    public void setPatchSize(int size) {
        if (size <= 0) throw new IllegalArgumentException("patchSize must be > 0");
        this.patchSize = size;
    }
    public void setOutputScale(int scale) {
        if (scale <= 0) throw new IllegalArgumentException("outputScale must be > 0");
        this.outputScale = scale;
    }

   
    public void loadTexture(String filePath) throws IOException {
        sourceTexture = ImageIO.read(new File(filePath));
        if (sourceTexture == null) throw new IOException("Unsupported image: " + filePath);
    }
    public void saveResult(BufferedImage img, String outPath) throws IOException {
        String ext = outPath.lastIndexOf('.') > 0 ? outPath.substring(outPath.lastIndexOf('.') + 1) : "png";
        if (!ImageIO.write(img, ext, new File(outPath))) throw new IOException("No writer for: " + ext);
    }

    
    public BufferedImage generateQuiltedTexture() {
        if (sourceTexture == null) throw new IllegalStateException("Texture image not loaded.");

        final int srcW = sourceTexture.getWidth(), srcH = sourceTexture.getHeight();
        final int outW = srcW * outputScale, outH = srcH * outputScale;

        BufferedImage out = new BufferedImage(outW, outH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = out.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        for (int y = 0; y < outH; y += patchSize) {
            for (int x = 0; x < outW; x += patchSize) {
                int rx = rng.nextInt(Math.max(1, srcW - Math.min(patchSize, srcW)));
                int ry = rng.nextInt(Math.max(1, srcH - Math.min(patchSize, srcH)));
                int sw = Math.min(patchSize, srcW - rx);
                int sh = Math.min(patchSize, srcH - ry);
                int dw = Math.min(patchSize, outW - x);
                int dh = Math.min(patchSize, outH - y);
                BufferedImage patch = sourceTexture.getSubimage(rx, ry, sw, sh);
                g.drawImage(patch, x, y, dw, dh, null);
            }
        }
        g.dispose();
        return out;
    }

    
    public BufferedImage executeQuilting() { return generateQuiltedTexture(); }
}






