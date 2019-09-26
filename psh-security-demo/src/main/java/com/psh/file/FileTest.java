package com.psh.file;

import java.io.FileInputStream;

/**
 * Created by peiyue.xing on 2019/8/27 17:16
 *
 * @version:
 */
public class FileTest {
    public static void main(String[] args) throws Exception {
        String type = FileTypeUtil.getFileType(new FileInputStream("D:\\var\\icon\\2019-06-15\\789.avi"));
        if(FileType.TXT.getValue().equals(type)){//TXT,DOCX
        }
        if(FileType.XLS_DOC.getValue().equals(type)){//PPT,DOC,XLS
        }
        if(FileType.XLSX_DOCX.getValue().equals(type)){//XLSX
        }
        if(FileType.PDF.getValue().equals(type)){//PDF
        }
        if(FileType.PNG.getValue().equals(type)){//PNG
        }
        if(FileType.JPEG.getValue().equals(type)) {//JPG
        }
    }
}
