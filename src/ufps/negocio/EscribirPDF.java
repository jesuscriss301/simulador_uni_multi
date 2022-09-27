package ufps.negocio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import ufps.negocio.Simulador;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
//import itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author jesus
 */
public class EscribirPDF {

    private Simulador s;

    public EscribirPDF() {
    }

    public EscribirPDF(Object[] cadena_procesos) {
        this.s = new Simulador(cadena_procesos);

    }

    private Chunk titulo(String cadena, Font font) {
        Chunk titulo1 = new Chunk(cadena, font);
        Paragraph t1 = new Paragraph(titulo1);
        t1.setAlignment(Element.ALIGN_LEFT);

        return titulo1;
    }

    public void escribir() throws FileNotFoundException, DocumentException, IOException {

        //System.out.println(m.toString());
        Document documento = new Document();
        FileOutputStream ficheroPdf = new FileOutputStream("src/pdf/respuestas.pdf");
        PdfWriter.getInstance(documento, ficheroPdf);
        documento.open();

        Font font = new Font();
        font.setSize(14);

        Paragraph espacio = new Paragraph("\n \n \n");
        //paragraphHello.setFont(font);

        documento.add(titulo("Uniprogramming", font));
        documento.add(espacio);
        PdfPTable tabla1 = new PdfPTable(s.secuenciasCadena());
        tabla1.setWidthPercentage(100);

        for (int i = 0; i < s.secuenciasCadena(); i++) {
            Paragraph columna = new Paragraph(i+"");
            columna.getFont().setStyle(Font.BOLD);
            columna.getFont().setSize(8);
            columna.setAlignment(Element.ALIGN_CENTER); //aquí
            tabla1.addCell(columna);
        }

        char[][] base = s.uniproceso();
        //int i = 0; i < base.length; i++
        for (char[] base1 : base) {
            for (char base11 : base1) {
                PdfPCell cell = new PdfPCell(new Phrase(base11+""));
                if(base11=='R'){
                    cell.setBackgroundColor(BaseColor.GREEN);//cambiamos la propiedad del color
                }
                if(base11=='I'){
                    cell.setBackgroundColor(BaseColor.YELLOW);//cambiamos la propiedad del color
                }
                
                   tabla1.addCell(cell);//añadimos la celda a tabla 
            }
        }

        documento.add(tabla1);
        documento.add(espacio);

        documento.add(titulo("Multiprogramming", font));
        documento.add(espacio);
        int j = s.getProcesoMult();
        PdfPTable tabla2 = new PdfPTable(j);
        tabla1.setWidthPercentage(100);

        for (int i = 0; i < s.getProcesoMult(); i++) {
            Paragraph columna = new Paragraph(i + "");
            columna.getFont().setStyle(Font.BOLD);
            columna.getFont().setSize(8);
            columna.setAlignment(Element.ALIGN_CENTER); //aquí
            tabla2.addCell(columna);
        }

        char[][] base2;
        base2 = s.multiproceso();
        //int i = 0; i < base.length; i++
        for (int a = 0; a < base2.length; a++) {
            for (int b = 0; b < j; b++) {
                PdfPCell cell = new PdfPCell(new Phrase(base2[a][b]+""));
                if(base2[a][b]=='R'){
                    cell.setBackgroundColor(BaseColor.GREEN);//cambiamos la propiedad del color
                }
                if(base2[a][b]=='I'){
                    cell.setBackgroundColor(BaseColor.YELLOW);//cambiamos la propiedad del color
                }
                tabla2.addCell(cell);
                
            }
        }

        documento.add(tabla2);
        documento.add(espacio);

        //Cerramos el documento.
        documento.close();
        File objetofile = new File("src/pdf/respuestas.pdf");
        Desktop.getDesktop().open(objetofile);

    }

}
