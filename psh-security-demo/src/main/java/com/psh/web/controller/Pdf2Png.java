/**
 * @author 夹心 E-mail: yanjiaxin8410@163.com
 * @version 创建时间：2018年5月18日 下午8:57:07
 * 类说明
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class Pdf2Png {

    //可自由确定起始页和终止页
    public static void pdf2png(String fileAddress,String filename,int indexOfStart,int indexOfEnd) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File(fileAddress+"\\"+filename+".pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = indexOfStart; i < indexOfEnd; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 144); // Windows native DPI
                // BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
                ImageIO.write(image, "JPG", new File(fileAddress+"\\"+filename+"_"+(i+1)+".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //转换全部的pdf
    public static void pdf2png(String fileAddress,String filename) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File(fileAddress+"\\"+filename+".pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 300); // Windows native DPI
                // BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
                ImageIO.write(image, "JPG", new File(fileAddress+"\\"+filename+"_"+(i+1)+".jpg"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入需要转换的pdf的地址，例如 E:\\软件\\代码：");
//        String fileAddress = sc.nextLine();
//        System.out.println("请输入需要转换的pdf的名称，不要加.pdf后缀，例如 操作系统概念：");
//        String filename =sc.nextLine();
//        System.out.println("请输入开始转换的页码，从0开始，例如 5：");
//        int indexOfStart=sc.nextInt();
//        System.out.println("请输入停止转换的页码，-1为全部，例如 10：");
//        int indexOfEnd=sc.nextInt();
//        if (indexOfEnd==-1) {
//            pdf2png(fileAddress, filename);
//        }
//        else {
//            pdf2png(fileAddress, filename, indexOfStart, indexOfEnd);
//        }
        pdf2png("D://pdf", "本人人才引进呈报表");

    }

}