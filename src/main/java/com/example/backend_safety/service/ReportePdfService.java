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

            document.add(new Paragraph("REPORTE SG-SST")
                    .setBold()
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Total Actividades: " + data.get("totalActividades")));
            document.add(new Paragraph("Riesgos Altos: " + data.get("riesgosAltos")));
            document.add(new Paragraph("Riesgos Medios: " + data.get("riesgosMedios")));
            document.add(new Paragraph("Riesgos Bajos: " + data.get("riesgosBajos")));

            document.add(new Paragraph("\n"));

            Table table = new Table(3);

            table.addHeaderCell("ID");
            table.addHeaderCell("Nivel");
            table.addHeaderCell("Estado");

            var riesgos = (java.util.List<com.example.backend_safety.model.Riesgo>) data.get("riesgos");

            for (var r : riesgos) {
                table.addCell(String.valueOf(r.getId()));
                table.addCell(r.getNivel());
                table.addCell(r.getEstado());
            }

            document.add(table);

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF", e);
        }

        return out.toByteArray();
    }
}
