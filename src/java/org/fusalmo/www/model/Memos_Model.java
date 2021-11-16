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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.MemosEntity;
import org.fusalmo.www.entities.PrestamoRecursosEntity;
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.entities.TipoMemoEntity;
import org.fusalmo.www.entities.UsuariosITEntity;
import org.fusalmo.www.utils.JPAUtil;
import org.primefaces.model.DefaultStreamedContent;
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
    
    public List<EmpleadoEntity> obtenerEmpleadosConRecursos(List<EmpleadoEntity> empleados_todos){
        
        EntityManager em = JPAUtil.getEntityManager();
        List<EmpleadoEntity> empleados_con_recursos = new ArrayList<>();
        
        try {
            
            for (EmpleadoEntity empleados_todo : empleados_todos) {
                
                Query consulta = em.createNamedQuery("PrestamoRecursosEntity.findByIdEmpleado");
                consulta.setParameter("id", empleados_todo.getId());
                
                if(consulta.getResultList().isEmpty() == false){
                    
                    empleados_con_recursos.add(empleados_todo);
                    
                }
                
            }
            
            return empleados_con_recursos;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public EmpleadoEntity buscarEmpleadoByID(String idEmpleado){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            EmpleadoEntity empleado = em.find(EmpleadoEntity.class, idEmpleado);
            em.close();
            return empleado;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public List<PrestamoRecursosEntity> listarRecursosPrestadosByID(String idEmpleado){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            System.out.println(idEmpleado);
            EmpleadoEntity emp = buscarEmpleadoByID(idEmpleado);
            System.out.println(emp);
            Query consulta = em.createNamedQuery("PrestamoRecursosEntity.findByIdEmpleado");
            consulta.setParameter("id", emp.getId());
            List<PrestamoRecursosEntity> prestamosEmp = consulta.getResultList();
            System.out.println(prestamosEmp);
            return prestamosEmp;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public List<RecursosEntity> listaRecursos(){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("RecursosEntity.findByIsDeleted");
            consulta.setParameter("isDeleted", Boolean.FALSE);
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
    
    public List<MemosEntity> listaMemosActivos(){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("MemosEntity.findByIsDeleted").setParameter("isDeleted", Boolean.FALSE);
            List<MemosEntity> lista = consulta.getResultList();
            return lista;
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
    
    public TipoMemoEntity obtenerTipoMemo(String idTipo){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            TipoMemoEntity tipo = em.find(TipoMemoEntity.class, idTipo);
            
            em.close();
            
            return tipo;
            
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
    
    public PrestamoRecursosEntity buscarPrestamoRecursoByID(Integer idPrestamo){
         EntityManager em = JPAUtil.getEntityManager();
        try {
            PrestamoRecursosEntity results = em.find(PrestamoRecursosEntity.class, idPrestamo);
            em.close();
            return results;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
    public StreamedContent obtenerPDFFirmadoById(String idMemo){
        EntityManager em = JPAUtil.getEntityManager();
        System.out.println("ID*****"+idMemo);
        try {
            MemosEntity a= em.find(MemosEntity.class, idMemo);
            byte [] temp =a.getPDFFirmado();
            temp.getClass().getResourceAsStream("pDFFirmado");
            InputStream stream= new ByteArrayInputStream(temp);
            StreamedContent archivo = DefaultStreamedContent.builder()
                    .name(a.getId()+"_firmado.pdf")
                    .contentType("aplication/pdf")
                    .stream(
                            ()-> stream
                    )
                    .build();
            
            /*Query consulta =em.createQuery("SELECT m.pDFFirmado FROM MemosEntity m WHERE m.id = :idMemo").setParameter("idMemo", idMemo);
            int tamInput = bos.available();
            byte [] pdf_firmado= new byte[tamInput];
            bos.read(pdf_firmado, 0, tamInput);
            
            OutputStream archivo = new FileOutputStream("pdf_firmado.pdf");
            archivo.write(pdf_firmado);*/
            
            em.close();
            return archivo;
        } catch (Exception e) {
            em.close();
            System.out.println(e);
            return null;
        }
    }
    
    public StreamedContent obtenerPDFById(String idMemo){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            MemosEntity memo = em.find(MemosEntity.class, idMemo);
            byte [] temp = memo.getPdf();
            temp.getClass().getResourceAsStream("PDF");
            InputStream stream= new ByteArrayInputStream(temp);
            
            StreamedContent archivo = DefaultStreamedContent.builder()
                    .name(memo.getId() + ".pdf")
                    .contentType("aplication/pdf")
                    .stream(
                            ()-> stream
                    )
                    .build();
            
            em.close();
            return archivo;
            
        } catch (Exception e) {
            
            em.close();
            System.out.println(e);
            return null;
            
        }
    }
    
    public int modificarMemo( MemosEntity memo ){
        
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        
        try {
            
            tran.begin();
            em.merge(memo);
            tran.commit();
            em.close();
            return 1;
                
        } catch (Exception e) {
            
            em.close();
            return 0;
            
        }
        
    }
    
    public int modificarRecursoEntity( RecursosEntity recurso ){
        
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        
        try {
            
            tran.begin();
            em.merge(recurso);
            tran.commit();
            em.close();
            return 1;
                
        } catch (Exception e) {
            
            em.close();
            return 0;
            
        }
        
    }
    
    public int eliminarMemo(String memo){
        EntityManager em= JPAUtil.getEntityManager();
        EntityTransaction tran =em.getTransaction();
        try{
            MemosEntity temp=em.find(MemosEntity.class, memo);
            temp.setIsDeleted(Boolean.TRUE);
            tran.begin();
            em.persist(temp);
            tran.commit();
            em.close();
            return 1;
        }catch(Exception e){
            em.close();
            System.out.println(e);
            return 0;
        }        
    }
    
    public int eliminarPrestamo(Integer idPrestamo){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        int filasBorradas = 0;
        
        try {
            
            PrestamoRecursosEntity prestamo = em.find(PrestamoRecursosEntity.class, idPrestamo);
            
            if(prestamo != null){
                
                EntityTransaction tra = em.getTransaction();
                tra.begin();
                em.remove(prestamo);
                tra.commit();
                filasBorradas = 1;
                
            }
            
            em.close();
            return filasBorradas;
            
        } catch (Exception e) {
            
            System.out.println(e);
            em.close();
            return 0;
            
        }
        
    }
    
    public int crearMemo(MemosEntity memo){
        
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        
        try {
            
            tran.begin();
            em.persist(memo);
            tran.commit();
            em.close();
            return 1;
            
        } catch (Exception e) {
            
            em.close();
            return 0;
            
        }
        
    }
    
    public int crearPrestamoRecurso(PrestamoRecursosEntity prestamo){
        
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        
        try {
            
            tran.begin();
            em.persist(prestamo);
            tran.commit();
            em.close();
            return 1;
            
        } catch (Exception e) {
            
            em.close();
            return 0;
            
        }
        
    }
    
    //Creación de fuentes
    public static final String FONT = "C:\\Users\\Soporte\\Documents\\NetBeansProjects\\FUSALMO_Soporte\\web\\resources\\fonts\\arialBold.ttf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font arialFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    
    public void crearPDFAsignacion(
            String para,
            String adminIT,
            String asunto,
            String date,
            String empleado,
            List<RecursosEntity> recursosSeleccionados,
            String idMemo
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
            file = new File("C:\\Users\\Soporte\\Documents\\NetBeansProjects\\FUSALMO_Soporte\\web\\resources\\demo\\" + idMemo + ".pdf");
            
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
            image = Image.getInstance("C:\\Users\\Soporte\\Documents\\NetBeansProjects\\FUSALMO_Soporte\\web\\resources\\img\\LogoFusalmo.png");
            
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
    
    public void crearPDFSustitucion(
            String para,
            String adminIT,
            String asunto,
            String date,
            String empleado,
            List<RecursosEntity> recursosSeleccionados,
            String idMemo,
            List<PrestamoRecursosEntity> recursosAsignados
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
            file = new File("C:\\Users\\Soporte\\Documents\\NetBeansProjects\\FUSALMO_Soporte\\web\\resources\\demo\\" + idMemo + ".pdf");
            
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
            image = Image.getInstance("C:\\Users\\Soporte\\Documents\\NetBeansProjects\\FUSALMO_Soporte\\web\\resources\\img\\LogoFusalmo.png");
            
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
                    "Por este medio, se efectúa la asignación "
                            + "informático en calidad de "
            );
            documento.add(texto1);
            
            Chunk tipo = new Chunk("SUSTITUCIÓN", arial12);
            documento.add(tipo);
            
            Chunk texto2 = new Chunk(
                    " del usuario "
            );
            documento.add(texto2);
            
            Chunk emp = new Chunk(empleado, arial12);
            documento.add(emp);
            
            //Creación de espaciado
            documento.add(new Paragraph("\n"));
            
            documento.add(new Paragraph("El equipo que tenia asignado era el siguiente:"));
            
            documento.add(new Paragraph("\n"));
            
            //Creación de la tabla
            PdfPTable recursosAnteriores = new PdfPTable(new float[]{5f, 10f, 10f, 10f, 10f});
            recursosAnteriores.setWidthPercentage(100);
            PdfPCell cellNumero = new PdfPCell(new Phrase("Número", arial12));
            PdfPCell cellTipoEquipo = new PdfPCell(new Phrase("Tipo de equipo", arial12));
            PdfPCell cellMarca = new PdfPCell(new Phrase("Marca", arial12));
            PdfPCell cellModelo = new PdfPCell(new Phrase("Modelo", arial12));
            PdfPCell cellSerie = new PdfPCell(new Phrase("Número de serie/Etiqueta de servicio", arial12));
            
            //Agrega las celdas a la tabla
            recursosAnteriores.addCell(cellNumero);
            recursosAnteriores.addCell(cellTipoEquipo);
            recursosAnteriores.addCell(cellMarca);
            recursosAnteriores.addCell(cellModelo);
            recursosAnteriores.addCell(cellSerie);
            
            for (PrestamoRecursosEntity recursosAsignado : recursosAsignados) {
                
                num += 1;
                recursosAnteriores.addCell(String.valueOf(num));
                recursosAnteriores.addCell(recursosAsignado.getIdRecurso().getIdTipoRecurso().getNombre());
                recursosAnteriores.addCell(recursosAsignado.getIdRecurso().getMarca());
                recursosAnteriores.addCell(recursosAsignado.getIdRecurso().getModelo());
                recursosAnteriores.addCell(recursosAsignado.getIdRecurso().getNumSerie());
                
            }
            
            //Agrega la tabla al documento
            documento.add(recursosAnteriores);
            
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
    
    public void crearPDFPrestamo(
            String para,
            String adminIT,
            String asunto,
            String date,
            String empleado,
            List<RecursosEntity> recursosSeleccionados,
            String idMemo,
            String finalidadDeUso,
            String mesEnero,
            String diasEnero,
            String mesFebrero,
            String diasFebrero,
            String mesMarzo,
            String diasMarzo,
            String mesAbril,
            String diasAbril,
            String mesMayo,
            String diasMayo,
            String mesJunio,
            String diasJunio,
            String mesJulio,
            String diasJulio,
            String mesAgosto,
            String diasAgosto,
            String mesSeptiembre,
            String diasSeptiembre,
            String mesOctubre,
            String diasOctubre,
            String mesNoviembre,
            String diasNoviembre,
            String mesDiciembre,
            String diasDiciembre,
            String horarioAUtilizar
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
            file = new File("C:\\Users\\Soporte\\Documents\\NetBeansProjects\\FUSALMO_Soporte\\web\\resources\\demo\\" + idMemo + ".pdf");
            
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
            image = Image.getInstance("C:\\Users\\Soporte\\Documents\\NetBeansProjects\\FUSALMO_Soporte\\web\\resources\\img\\LogoFusalmo.png");
            
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
            
            Chunk tipo = new Chunk("PRÉSTAMO.", arial12);
            documento.add(tipo);
            
            Chunk texto2 = new Chunk(
                    " Para "
            );
            documento.add(texto2);
            
            Chunk finalidad = new Chunk(finalidadDeUso, arial12);
            documento.add(finalidad);
            
            Chunk empEncargado = new Chunk(
                    " y como empleado encargado del recurso "
            );
            documento.add(empEncargado);
            
            Chunk emp = new Chunk(empleado, arial12);
            documento.add(emp);
            
            documento.add(new Paragraph("\n"));
            
            documento.add(new Paragraph("Los días de uso serán los siguientes:"));
            
            documento.add(new Paragraph("\n"));
            
            if(mesEnero.isEmpty() != true && diasEnero.isEmpty() != true){
                documento.add(new Paragraph(mesEnero));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph(diasEnero));
                documento.add(new Paragraph("\n"));
            }
            if(mesFebrero.isEmpty() != true && diasFebrero.isEmpty() != true){
                documento.add(new Paragraph(mesFebrero));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph(diasFebrero));
                documento.add(new Paragraph("\n"));
            }
            if(mesMarzo.isEmpty() != true && diasMarzo.isEmpty() != true){
                documento.add(new Paragraph(mesMarzo));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph(diasMarzo));
                documento.add(new Paragraph("\n"));
            }
            if(mesAbril.isEmpty() != true && diasAbril.isEmpty() != true){
                documento.add(new Paragraph(mesAbril));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph(diasAbril));
                documento.add(new Paragraph("\n"));
            }
            if(mesMayo.isEmpty() != true && diasMayo.isEmpty() != true){
                documento.add(new Paragraph(mesMayo));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph(diasMayo));
                documento.add(new Paragraph("\n"));
            }
            if(mesJunio.isEmpty() != true && diasJunio.isEmpty() != true){
                documento.add(new Paragraph(mesJunio));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph(diasJunio));
                documento.add(new Paragraph("\n"));
            }
            if(mesJulio.isEmpty() != true && diasJulio.isEmpty() != true){
                documento.add(new Paragraph(mesJulio));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph(diasJulio));
                documento.add(new Paragraph("\n"));
            }
            if(mesAgosto.isEmpty() != true && diasAgosto.isEmpty() != true){
                documento.add(new Paragraph(mesAgosto));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph(diasAgosto));
                documento.add(new Paragraph("\n"));
            }
            if(mesSeptiembre.isEmpty() != true && diasSeptiembre.isEmpty() != true){
                documento.add(new Paragraph(mesSeptiembre));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph(diasSeptiembre));
                documento.add(new Paragraph("\n"));
            }
            if(mesOctubre.isEmpty() != true && diasOctubre.isEmpty() != true){
                documento.add(new Paragraph(mesOctubre));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph(diasOctubre));
                documento.add(new Paragraph("\n"));
            }
            if(mesNoviembre.isEmpty() != true && diasNoviembre.isEmpty() != true){
                documento.add(new Paragraph(mesNoviembre));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph(diasNoviembre));
                documento.add(new Paragraph("\n"));
            }
            if(mesDiciembre.isEmpty() != true && diasDiciembre.isEmpty() != true){
                documento.add(new Paragraph(mesDiciembre));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph(diasDiciembre));
                documento.add(new Paragraph("\n"));
            }
            
            documento.add(new Paragraph("\n"));
            
            documento.add(new Paragraph(horarioAUtilizar, arial12));
            
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
    
    public byte[] convertirPDF(String idMemo){
        
        byte[] content = null;
        Path pdfPath = Paths.get("C:\\Users\\Soporte\\Documents\\NetBeansProjects\\FUSALMO_Soporte\\web\\resources\\demo\\" + idMemo + ".pdf");
        
        try {
            content = Files.readAllBytes(pdfPath);
            return content;
        } catch (IOException ex) {
            Logger.getLogger(Memos_Model.class.getName()).log(Level.SEVERE, null, ex);
            return content;
        }
                
    }
    
    public void borrarPDF(String idMemo){
        
        File file = new File("C:\\Users\\Soporte\\Documents\\NetBeansProjects\\FUSALMO_Soporte\\web\\resources\\demo\\" + idMemo + ".pdf");
        
        if(file.delete()){
            
            System.out.println("Se borró el fichero");
            
        }else{
            
            System.out.println("No se pudo borrar el fichero");
            
        }
        
    }
    
    public String contarPrestamoRecurso(){
        
        String count = "";
        
        //int returnCount;
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("PrestamoRecursosEntity.countAll");
            
            //int resultsNumber = consulta.getFirstResult();
            List results = consulta.getResultList();
            
            //System.out.println(resultsNumber);
            //System.out.println(results);
            //System.out.println(results.size());
            //System.out.println(results.toString() + " Pasado a String");
            
            for (Object result : results) {
                //System.out.println(result.toString());
                count = result.toString();
            }
            
            //System.out.println(count + " Cuenta del resultado");
            
            //returnCount = Integer.parseInt(count);
            
            return count;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public Integer crearIDPrestamoRecurso(){
        
        Integer count = Integer.valueOf(contarPrestamoRecurso());
        String convert_count_string;
        StringBuilder count_str;
        Integer newCount;
        Integer finalID = 0;
        
        int resultLength = String.valueOf(count).length();
        int numberReturn;
        
        System.out.println("El resultLength es: "+resultLength);
        System.out.println(count + " Conteo de tokens");
        
        count = count + 1;
        finalID = checkToken(count);
        return finalID;
        
    }
    
    public int checkToken(int count){
        
        PrestamoRecursosEntity returnPrestamo = buscarPrestamoRecursoByID(count);
        System.out.println(returnPrestamo + " PRIMER PRESTAMO RETORNADO * ** *");
        if(returnPrestamo == null){
            
            return count;
            
        }else {

            while (returnPrestamo != null) {

                count += 1;
                returnPrestamo = buscarPrestamoRecursoByID(count);
                System.out.println(returnPrestamo + " recurso retornado");
                
            }
            
            return count;
            
        } 
    }
    
    public String contarMemos(){
        
        String count = "";
        
        //int returnCount;
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("MemosEntity.countAll");
            
            //int resultsNumber = consulta.getFirstResult();
            List results = consulta.getResultList();
            
            //System.out.println(resultsNumber);
            //System.out.println(results);
            //System.out.println(results.size());
            //System.out.println(results.toString() + " Pasado a String");
            
            for (Object result : results) {
                //System.out.println(result.toString());
                count = result.toString();
            }
            
            //System.out.println(count + " Cuenta del resultado");
            
            //returnCount = Integer.parseInt(count);
            
            return count;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public String crearID(){
        
        StringBuilder idPlantilla = new StringBuilder("ME0000");
        String finalID = "";
        int countResource = Integer.valueOf(contarMemos());
        int resultLength = String.valueOf(countResource).length();
        int numberReturn;
        System.out.println(countResource + " Conteo de memos");
        if(resultLength == 1 && countResource < 10){
            
            if(countResource == 9){
                
                //Borra el caracter 5 y 4 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                
                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);
                
                resultLength = String.valueOf(numberReturn).length();
                
                finalID = idPlantilla + String.valueOf(numberReturn);
                return finalID;
                
            }else{
                
                //Borra el caracter 5 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);

                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);
                
                resultLength = String.valueOf(numberReturn).length();
                
                if(resultLength == 1){
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 2){
                    
                    //Borra el caracter 4 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(4);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 3){
                    
                    //Borra el caracter 4 y 3 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(4);
                    idPlantilla = idPlantilla.deleteCharAt(3);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 4){
                    
                    //Borra el caracter 4, 3 y 2 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(4);
                    idPlantilla = idPlantilla.deleteCharAt(3);
                    idPlantilla = idPlantilla.deleteCharAt(2);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 5){
                    
                    //Borra el caracter 4, 3, 2 y 1 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(4);
                    idPlantilla = idPlantilla.deleteCharAt(3);
                    idPlantilla = idPlantilla.deleteCharAt(2);
                    idPlantilla = idPlantilla.deleteCharAt(1);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                
            }
            
        }
        if(resultLength == 2 && countResource < 100){
            
            if(countResource == 99){
                
                //Borra el caracter 5, 4 y 3 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                idPlantilla = idPlantilla.deleteCharAt(3);
                
                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);
                
                resultLength = String.valueOf(numberReturn).length();
                
                finalID = idPlantilla + String.valueOf(numberReturn);
                return finalID;
                
            }else{
            
                //Borra el caracter 5 y 4 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);

                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);

                resultLength = String.valueOf(numberReturn).length();
                
                if(resultLength == 1){
                    
                    //Agrega el caracter 0 al string
                    idPlantilla = idPlantilla.insert(4, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 2){
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 3){
                    
                    //Borra el caracter 3 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(3);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 4){
                    
                    //Borra el caracter 3 y 2 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(3);
                    idPlantilla = idPlantilla.deleteCharAt(2);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 5){
                    
                    //Borra el caracter 3, 2 y 1 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(3);
                    idPlantilla = idPlantilla.deleteCharAt(2);
                    idPlantilla = idPlantilla.deleteCharAt(1);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                
            }
            
        }
        if(resultLength == 3 && countResource < 1000){
            
            if(countResource == 999){
                
                //Borra el caracter 5, 4, 3 y 2 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                idPlantilla = idPlantilla.deleteCharAt(3);
                idPlantilla = idPlantilla.deleteCharAt(2);
                
                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);
                
                resultLength = String.valueOf(numberReturn).length();
                
                finalID = idPlantilla + String.valueOf(numberReturn);
                return finalID;
                
            }else{
            
                //Borra el caracter 5, 4 y 3 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                idPlantilla = idPlantilla.deleteCharAt(3);

                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);

                resultLength = String.valueOf(numberReturn).length();
                
                if(resultLength == 1){
                    
                    //Agrega el caracter 0 al string
                    idPlantilla = idPlantilla.insert(4, "0");
                    idPlantilla = idPlantilla.insert(3, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 2){
                    
                    idPlantilla = idPlantilla.insert(3, "0");
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 3){
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 4){
                    
                    //Borra el caracter 2 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(2);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 5){
                    
                    //Borra el caracter 2 y 1 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(2);
                    idPlantilla = idPlantilla.deleteCharAt(1);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                
            }
            
        }
        if(resultLength == 4 && countResource < 10000){
            
            if(countResource == 9999){
                
                //Borra el caracter 5, 4, 3, 2 y 1 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                idPlantilla = idPlantilla.deleteCharAt(3);
                idPlantilla = idPlantilla.deleteCharAt(2);
                idPlantilla = idPlantilla.deleteCharAt(1);
                
                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);
                
                resultLength = String.valueOf(numberReturn).length();
                
                finalID = idPlantilla + String.valueOf(numberReturn);
                return finalID;
                
            }else{
            
                //Borra el caracter 5, 4, 3 y 2 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                idPlantilla = idPlantilla.deleteCharAt(3);
                idPlantilla = idPlantilla.deleteCharAt(2);

                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);

                resultLength = String.valueOf(numberReturn).length();
                
                if(resultLength == 1){
                    
                    //Agrega el caracter 0 al string
                    idPlantilla = idPlantilla.insert(4, "0");
                    idPlantilla = idPlantilla.insert(3, "0");
                    idPlantilla = idPlantilla.insert(2, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 2){
                    
                    idPlantilla = idPlantilla.insert(3, "0");
                    idPlantilla = idPlantilla.insert(2, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 3){
                    
                    idPlantilla = idPlantilla.insert(2, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 4){
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 5){
                    
                    //Borra el caracter 2 y 1 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(1);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                
            }
            
        }
        if(resultLength == 4 && countResource < 100000){
            
            if(countResource == 99999){
                
                return null;
                
            }else{
            
                //Borra el caracter 5, 4, 3, 2 y 1 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                idPlantilla = idPlantilla.deleteCharAt(3);
                idPlantilla = idPlantilla.deleteCharAt(2);
                idPlantilla = idPlantilla.deleteCharAt(1);

                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);

                resultLength = String.valueOf(numberReturn).length();
                
                if(resultLength == 1){
                    
                    //Agrega el caracter 0 al string
                    idPlantilla = idPlantilla.insert(4, "0");
                    idPlantilla = idPlantilla.insert(3, "0");
                    idPlantilla = idPlantilla.insert(2, "0");
                    idPlantilla = idPlantilla.insert(1, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 2){
                    
                    idPlantilla = idPlantilla.insert(3, "0");
                    idPlantilla = idPlantilla.insert(2, "0");
                    idPlantilla = idPlantilla.insert(1, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 3){
                    
                    idPlantilla = idPlantilla.insert(2, "0");
                    idPlantilla = idPlantilla.insert(1, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 4){
                    
                    idPlantilla = idPlantilla.insert(1, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 5){
                    
                    //Borra el caracter 2 y 1 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(1);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                
            }
            
        }
        
        return finalID;
    
    }
    
    public int checkRecurso(String finalId, int resultCount, StringBuilder id){
        
        MemosEntity returnMemos = buscarMemoById(finalId);
        System.out.println(returnMemos + " PRIMER MEMO RETORNADO * ** *");
        if(returnMemos == null){
            
            return resultCount;
            
        }else {

            while (returnMemos != null) {

                resultCount += 1;
                
                finalId = id + String.valueOf(resultCount);

                returnMemos = buscarMemoById(finalId);
                System.out.println(returnMemos + " memo retornado");
            }
            
            return resultCount;
            
        }
        
    }
    
}
