package com.liu;

/**
 * @description:
 * @author: Lqh
 * @time: 2019/8/28 17:10
 */

/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/21731027/backgroundimage-in-landscape-and-cover-whole-pdf-with-itextsharp
 */


import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.test.annotations.type.SampleTest;

import org.junit.experimental.categories.Category;

import java.io.File;


public class BackgroundImage  {
    public static final String DEST = "./target/test/resources/sandbox/images/background_image.pdf";
    public static final String IMAGE = "./src/test/resources/img/berlin2013.jpg";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new BackgroundImage().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        // Note that it is not necessary to create new PageSize object,
        // but for testing reasons (connected to parallelization) we call constructor here
        PageSize pageSize = new PageSize(PageSize.A4).rotate();
        Document doc = new Document(pdfDoc, pageSize);
        PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
        canvas.addImage(ImageDataFactory.create(IMAGE), pageSize, false);
        doc.add(new Paragraph("Berlin!"));
        doc.close();
    }
}

