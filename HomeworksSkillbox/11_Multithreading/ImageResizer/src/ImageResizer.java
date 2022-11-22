import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer implements Runnable{
    private File[] files;
    private int newWidth;
    private String dstFolder;
    private long start;

    public ImageResizer(File[] files, int newWidth, String dstFolder, long start) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try
        {
            for(File file : files)
            {
                BufferedImage image = ImageIO.read(file);
                if(image == null) {
                    continue;
                }

                int newWidth = 300;
                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );

                BufferedImage scaledImage = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_EXACT, newWidth, newHeight);
                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;//

                for (int x = 0; x < newWidth; x++)
                {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep); //тут берутся пиксели
                        scaledImage.setRGB(x, y, rgb); // и вставляются в итоговое изображение
                    }
                }
                int pointIndex = file.getName().lastIndexOf(".") + 1;
                int textLenght = file.getName().length();
                String fileFormat = file.getName().substring(pointIndex, textLenght);
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(scaledImage, fileFormat, newFile);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public int getNewWidth() {
        return newWidth;
    }

    public void setNewWidth(int newWidth) {
        this.newWidth = newWidth;
    }

    public String getDstFolder() {
        return dstFolder;
    }

    public void setDstFolder(String dstFolder) {
        this.dstFolder = dstFolder;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }
}
