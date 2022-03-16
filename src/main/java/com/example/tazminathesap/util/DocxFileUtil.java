package com.example.tazminathesap.util;

import java.io.File;

import com.example.tazminathesap.model.MaddiTazminat;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import org.springframework.stereotype.Component;

@Component
public class DocxFileUtil {
    private final String FILE_NAME = "src/main/resources/docs/";

    /**
     * Maddi Tazminat bilgilerine göre yeni docx belgesi oluşturan method
     * @param maddiTazminat
     */
    public void returnDocFile(MaddiTazminat maddiTazminat) throws InvalidFormatException{
        try {
            WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
            
            //Main Document
            MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();

            //Create main document part
           // ObjectFactory factory = Context.getWmlObjectFactory();
           // Body body = factory.createBody();
           // Document wmlDocumentEl = factory.createDocument();
            //wmlDocumentEl.setBody(body);


            //Put content in the part
           // mainDocumentPart.setJaxbElement(wmlDocumentEl);

            //Add main document part to the package relationships
            //mainDocumentPart.createParagraphOfText("Maddi Tazminat Hesabı");
            mainDocumentPart.addStyledParagraphOfText("Normal", "Maddi Tazminat Hesabı");
            mainDocumentPart.addParagraphOfText(maddiTazminat.getTazminatRapor().getEkBilgiler().toString());
           // wordPackage.addTargetPart(mainDocumentPart);
            File exportFile = new File(FILE_NAME + "madditazminat" + maddiTazminat.getId() + ".docx");
            wordPackage.save(exportFile);
        } catch (Docx4JException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }
}
