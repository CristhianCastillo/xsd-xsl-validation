package com.ptesa.xsdxslvalidation.model;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class Util {
    public static final String QR_CODE = "https://muisca.dian.gov.co/WebFacturaelectronica/paginas/VerificarFacturaElectronicaExterno.faces?TipoDocumento=1NroDocumento=PRUE980078932NITFacturador=890101815NumIdentAdquiriente=900108281Cufe=bf8b43bc09bdf01204dd2f1dcae2b51ea02e849f29257140fc00dc9b65aa953e";

    public static final String CUFE_DEFAULT = "248de1b0d000fe17d56b975118513374258793cbcadb453627a2c7491885c5a226f3ef2e152bb6843934d7fc0f12a01e";

    public static final String ALGORITHM_DEFAULT = "CUFE-SHA384";

    public static final String PREFIJO_FACTURA = "SETP";

    public static final String NUMERO_MATRICULA_MERCANTIL = "10181";

    public static String helloWorld() {
        return "Hello Worldaaaaaa";
    }

    public static String getQRCode() {
        return "https://muisca.dian.gov.co/WebFacturaelectronica/paginas/VerificarFacturaElectronicaExterno.faces?TipoDocumento=1NroDocumento=PRUE980078932NITFacturador=890101815NumIdentAdquiriente=900108281Cufe=bf8b43bc09bdf01204dd2f1dcae2b51ea02e849f29257140fc00dc9b65aa953e";
    }

    public static String getCufe() {
        return "248de1b0d000fe17d56b975118513374258793cbcadb453627a2c7491885c5a226f3ef2e152bb6843934d7fc0f12a01e";
    }

    public static String getCufeAlgorithm() {
        return "CUFE-SHA384";
    }

    public static String getPrefijoFactura() {
        return "SETP";
    }

    public static String getNumeroMatricula() {
        return "10181";
    }

    public static String convertFile(String urlXSLT, String urlXML) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            File stylesheet = new File(urlXSLT);
            File datafile = new File(urlXML);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(datafile);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            StreamSource stylesource = new StreamSource(stylesheet);
            Transformer transformer = tFactory.newTransformer(stylesource);
            DOMSource source = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            String strResult = writer.toString();
            System.out.println(strResult);
            return strResult;
        } catch (TransformerConfigurationException tce) {
            System.out.println("\n** Transformer Factory error");
            System.out.println("   " + tce.getMessage());
            Throwable x = tce;
            if (tce.getException() != null)
                x = tce.getException();
            x.printStackTrace();
            return null;
        } catch (TransformerException te) {
            System.out.println("\n** Transformation error");
            System.out.println("   " + te.getMessage());
            Throwable x = te;
            if (te.getException() != null)
                x = te.getException();
            x.printStackTrace();
            return null;
        } catch (SAXException sxe) {
            Exception x = sxe;
            if (sxe.getException() != null)
                x = sxe.getException();
            x.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}

