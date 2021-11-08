/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.model;

import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.MemosEntity;
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.entities.UsuariosITEntity;
import org.fusalmo.www.utils.JPAUtil;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Nemy
 */
public class Memos_Model {
    
    public List<AreaEntity> listaAreas(){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("AreaEntity.findAll");
            List<AreaEntity> listarAreas = consulta.getResultList();
            return listarAreas;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public List<EmpleadoEntity> listaEmpleados(){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("EmpleadoEntity.findAll");
            List<EmpleadoEntity> listarEmpleados = consulta.getResultList();
            return listarEmpleados;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public List<RecursosEntity> listaRecursos(){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("RecursosEntity.findAll");
            List<RecursosEntity> listarRecursos = consulta.getResultList();
            return listarRecursos;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public List<EmpleadoEntity> listarEMPArea(String idArea){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("EmpleadoEntity.findByAreaAsignada");
            consulta.setParameter("idAreaAsignada", idArea);
            List<EmpleadoEntity> listarEmpleados = consulta.getResultList();
            
            return listarEmpleados;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public AreaEntity obtenerArea(String idArea){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            AreaEntity area = em.find(AreaEntity.class, idArea);
            
            em.close();
            
            return area;
            
        } catch (Exception e) {
            
            em.close();
            
            return null;
            
        }
        
    }
    
    public UsuariosITEntity obtenerAdminIT(String idEmpleado){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            UsuariosITEntity emp = em.find(UsuariosITEntity.class, idEmpleado);
            
            em.close();
            
            return emp;
            
        } catch (Exception e) {
            
            em.close();
            
            return null;
            
        }
        
    }
    
    public MemosEntity buscarMemoById(String idMemo){
         EntityManager em = JPAUtil.getEntityManager();
        try {
            MemosEntity memo = em.find(MemosEntity.class, idMemo);
            em.close();
            return memo;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
    public OutputStream obtenerPDFFirmadoById(String idMemo){
        EntityManager em = JPAUtil.getEntityManager();
        byte [] temp = null;
        try {
            Query consulta =em.createQuery("SELECT m.pDFFirmado FROM MemosEntity m WHERE m.id = :idMemo").setParameter("idMemo", idMemo);
            temp=(byte[])consulta.getSingleResult();
            em.close();
            InputStream bos= new ByteArrayInputStream(temp);
            int tamInput = bos.available();
            byte [] pdf_firmado= new byte[tamInput];
            bos.read(pdf_firmado, 0, tamInput);
            
            OutputStream archivo = new FileOutputStream("pdf_firmado.pdf");
            archivo.write(pdf_firmado);
            return archivo;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
    
    //Creación de fuentes
    public static final String FONT = "C:\\Users\\Soporte\\Documents\\GitHub\\FUSALMO_Soporte\\build\\web\\resources\\fonts\\arialBold.ttf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font arialFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    
    public void crearPDF(
            String para,
            String adminIT,
            String asunto,
            String date,
            String empleado,
            List<RecursosEntity> recursosSeleccionados
    ){
        
        Document documento;
        File file;
        FileOutputStream archivo;
        Paragraph titulo;
        int num = 0;
        
        try {
            
            //Font arial = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //Se obtiene la fuente
            BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //Se crea el tipo de fuente
            Font arial12 = new Font(bf, 12);
            
            //Se inicializa la instancia de la clase document
            documento = new Document();
            
            //Destino de la creación del archivo y su nombre como ruta absoluta
            file = new File("C:\\Users\\Soporte\\Documents\\GitHub\\FUSALMO_Soporte\\build\\web\\resources\\demo\\prueba.pdf");
            
            //Se crea una instancia de tipo fileOutputStream pasando la ruta
            //absoluta
            archivo = new FileOutputStream(file);
            
            //Verifica si el archivo existe (condicional utilizada después)
            if(file.exists() == true){
                System.out.println("El archivo ya existe");
            }
            
            //Se crea el archivo como escritura
            PdfWriter.getInstance(documento, archivo);
            
            //Se abre el archivo
            documento.open();
            
            //Se crea una variable de tipo image de itext inicializado en null
            Image image = null;
            
            //Se guarda la ruta absoluta de la imagen
            image = Image.getInstance("C:\\Users\\Soporte\\Documents\\GitHub\\FUSALMO_Soporte\\build\\web\\resources\\img\\LogoFusalmo.png");
            
            //Se le da una escala absoluta o estática
            image.scaleAbsolute(150, 100);
            
            //Se posiciona en los ejes x, y
            image.setAbsolutePosition(225, 750);
            //image.setAlignment(1);
            
            //Se agrega la imagen al pdf
            documento.add(image);
            
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("\n"));
            
            documento.add(new Paragraph("MEMORÁNDUM", arial12));
            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("Para: " + para, arial12));
            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("De: " + adminIT, arial12));
            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("Asunto: " + asunto, arial12));
            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("Fecha: " + date, arial12));
            documento.add(Chunk.NEWLINE);
            
            LineSeparator ls = new LineSeparator();
            documento.add(new Chunk(ls));
            
            documento.add(new Paragraph("\n"));
            
            Chunk texto1 = new Chunk(
                    "Por este medio, se efectúa la asignación de equipo "
                            + "informático en calidad de "
            );
            documento.add(texto1);
            
            Chunk tipo = new Chunk("ASIGNACIÓN", arial12);
            documento.add(tipo);
            
            Chunk texto2 = new Chunk(
                    " para el usuario "
            );
            documento.add(texto2);
            
            Chunk emp = new Chunk(empleado, arial12);
            documento.add(emp);
            
            //Creación de espaciado
            documento.add(new Paragraph("\n"));
            
            documento.add(new Paragraph("El recurso a entregar es el siguiente:"));
            
            documento.add(new Paragraph("\n"));
            
            //Creación de la tabla
            PdfPTable tabla = new PdfPTable(new float[]{5f, 10f, 10f, 10f, 10f});
            tabla.setWidthPercentage(100);
            PdfPCell numero = new PdfPCell(new Phrase("Número", arial12));
            PdfPCell tipoEquipo = new PdfPCell(new Phrase("Tipo de equipo", arial12));
            PdfPCell marca = new PdfPCell(new Phrase("Marca", arial12));
            PdfPCell modelo = new PdfPCell(new Phrase("Modelo", arial12));
            PdfPCell serie = new PdfPCell(new Phrase("Número de serie/Etiqueta de servicio", arial12));
            
            //Agrega las celdas a la tabla
            tabla.addCell(numero);
            tabla.addCell(tipoEquipo);
            tabla.addCell(marca);
            tabla.addCell(modelo);
            tabla.addCell(serie);
            
            for (RecursosEntity recursosSeleccionado : recursosSeleccionados) {
                
                num += 1;
                tabla.addCell(String.valueOf(num));
                tabla.addCell(recursosSeleccionado.getIdTipoRecurso().getNombre());
                tabla.addCell(recursosSeleccionado.getMarca());
                tabla.addCell(recursosSeleccionado.getModelo());
                tabla.addCell(recursosSeleccionado.getNumSerie());
                
            }
            
            //Agrega la tabla al documento
            documento.add(tabla);
            
            documento.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
