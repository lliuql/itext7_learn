package com.liu;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.junit.Test;

import java.net.URL;

/**
 * @description:
 * @author: Lqh
 * @time: 2019/8/28 17:01
 */
public class ScoreDemo {
    String dest = "./demo.pdf";
    @Test
    public void drawImage3() throws Exception{
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PageSize ps = PageSize.A4;
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new BGEventHandler());

        PdfPage page = pdf.addNewPage(ps);
        Document document = new Document(pdf);
        document.add(new Paragraph("Hello World!"));
        pdf.addNewPage();
        document.add(new Paragraph("di er 2"));

        pdf.addNewPage();
        document.add(new Paragraph("di er 2"));

// Draw the axes画出坐标系
        pdf.close();
    }


    protected class BGEventHandler implements IEventHandler {
        ImageData imageData = null;
        {
            URL bg_url = this.getClass().getResource("/yanxue_img/img_nice.png");

            imageData = ImageDataFactory.create(bg_url);
        }


        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();

            Rectangle pageSize = page.getPageSize();
            PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

//            //Set background
//            Color limeColor = new DeviceCmyk(0.208f, 0, 0.584f, 0);
//            Color blueColor = new DeviceCmyk(0.445f, 0.0546f, 0, 0.0667f);
//            pdfCanvas.saveState()
//                    .setFillColor(pageNumber % 2 == 1 ? limeColor : blueColor)
//                    .rectangle(pageSize.getLeft(), pageSize.getBottom(), pageSize.getWidth(), pageSize.getHeight())
//                    .fill().restoreState();
//
//            //Add header and footer
//            pdfCanvas.beginText()
//                    .setFontAndSize(helvetica, 9)
//                    .moveText(pageSize.getWidth() / 2 - 60, pageSize.getTop() - 20)
//                    .showText("THE TRUTH IS OUT THERE")
//                    .moveText(60, -pageSize.getTop() + 30)
//                    .showText(String.valueOf(pageNumber))
//                    .endText();
//            Image apple = new Image(ImageDataFactory.create(this.getClass().getResource("/img/ny_times_fb.jpg")));
//            //Add watermark
//            Canvas canvas = new Canvas(pdfCanvas, pdfDoc, page.getPageSize());
//            canvas.add(apple);
//            canvas.setFontColor(Color.WHITE);
//            canvas.setProperty(Property.FONT_SIZE, 60);
//            canvas.setProperty(Property.FONT, helveticaBold);
//            canvas.showTextAligned(new Paragraph("CONFIDENTIAL"), 298, 421, pdfDoc.getPageNumber(page),
//                    TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);

            pdfCanvas.addImage(imageData, pageSize, false);
            pdfCanvas.release();
        }
    }
}
