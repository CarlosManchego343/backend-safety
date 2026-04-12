package com.example.backend_safety.service;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@Service
public class ReportePdfService {

	public byte[] generarPdf(Map<String, Object> data) {

	    ByteArrayOutputStream out = new ByteArrayOutputStream();

	    try {
	        PdfWriter writer = new PdfWriter(out);
	        PdfDocument pdf = new PdfDocument(writer);
	        Document document = new Document(pdf);

	        // 🎨 Título
	        Paragraph titulo = new Paragraph("REPORTE SG-SST")
	                .setBold()
	                .setFontSize(20)
	                .setTextAlignment(TextAlignment.CENTER);

	        document.add(titulo);
	        document.add(new Paragraph("\n"));

	        // 📊 Resumen
	        document.add(new Paragraph("Resumen General")
	                .setBold()
	                .setFontSize(14));

	        document.add(new Paragraph("Total Actividades: " + data.get("totalActividades")));
	        document.add(new Paragraph("Riesgos Altos: " + data.get("riesgosAltos")));
	        document.add(new Paragraph("Riesgos Medios: " + data.get("riesgosMedios")));
	        document.add(new Paragraph("Riesgos Bajos: " + data.get("riesgosBajos")));

	        document.add(new Paragraph("\n"));

	        // 📋 Tabla estilizada
	        Table table = new Table(new float[]{2, 2, 2});
	        table.setWidth(100);

	        table.addHeaderCell(new Cell().add(new Paragraph("ID").setBold()));
	        table.addHeaderCell(new Cell().add(new Paragraph("Nivel").setBold()));
	        table.addHeaderCell(new Cell().add(new Paragraph("Estado").setBold()));

	        var riesgos = (java.util.List<com.example.backend_safety.model.Riesgo>) data.get("riesgos");

	        for (var r : riesgos) {

	            table.addCell(String.valueOf(r.getId()));

	            // 🎨 Color según nivel
	            String nivel = r.getNivel();
	            Cell nivelCell = new Cell().add(new Paragraph(nivel));

	            if ("ALTO".equals(nivel)) {
	                nivelCell.setBackgroundColor(com.itextpdf.kernel.colors.ColorConstants.RED);
	            } else if ("MEDIO".equals(nivel)) {
	                nivelCell.setBackgroundColor(com.itextpdf.kernel.colors.ColorConstants.YELLOW);
	            } else {
	                nivelCell.setBackgroundColor(com.itextpdf.kernel.colors.ColorConstants.GREEN);
	            }

	            table.addCell(nivelCell);
	            table.addCell(r.getEstado());
	        }

	        document.add(table);

	        // 📌 Footer
	        document.add(new Paragraph("\n"));
	        document.add(new Paragraph("Sistema SG-SST - Reporte generado automáticamente")
	                .setFontSize(10)
	                .setTextAlignment(TextAlignment.CENTER));

	        document.close();

	    } catch (Exception e) {
	        throw new RuntimeException("Error generando PDF", e);
	    }

	    return out.toByteArray();
	}
}
