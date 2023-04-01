import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {
    public static void generateQRCode(String data, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 350, 350);

        BufferedImage image = new BufferedImage(350, 350, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 350; x++) {
            for (int y = 0; y < 350; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        File qrCode = new File(filePath);
        ImageIO.write(image, "png", qrCode);
    }

    public static void main(String[] args) {
        String data = "Hello Carlos";
        String filePath = "qr-code.png";

        try {
            generateQRCode(data, filePath);
            System.out.println("QR code has been generated successfully!");
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
