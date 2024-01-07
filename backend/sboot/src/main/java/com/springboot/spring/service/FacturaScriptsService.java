package com.springboot.spring.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import org.springframework.http.HttpMethod;
// Logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Jackson para procesar la respuesta de FacturaScripts
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.web.client.RestClientException;
import com.springboot.spring.model.Factura;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import com.springboot.spring.model.RespuestaFsFacturasCli;

@Service
public class FacturaScriptsService {

    // LOGS
    private static final Logger logger = LoggerFactory.getLogger(FacturaScriptsService.class);

    
    // FacturaScripts API 3 - Crear cliente
    public void altaCliente(String token, String codcliente, String cifnif, String nombrecliente, String surname, String email, String tlf, String username) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Token", token);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("codcliente", codcliente);
        map.add("cifnif", cifnif);
        map.add("nombre", nombrecliente + " " + surname);
        map.add("email", email);
        map.add("telefono1", tlf);
        map.add("observaciones", "Cliente creado desde la aplicación web de Bellidel "+ "Username: " + username);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String url = "http://bellidel.eu:8081/api/3/clientes";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

    }

    // FacturaScripts API 3 - Crear factura
    public void crearFactura(String token, String codcliente, String cifnif, String nombre, String surname, String fecha, String turnoID, String comensales, String menuID, String bookingID) {
        
        // Convertirmos la fecha para el formato de FacturaScripts
        String fechaFormateada = convertirFecha(fecha, "EEE MMM dd HH:mm:ss zzz yyyy", "dd-MM-yyyy");

        // Creamos observaciones a medida
        String observaciones = "Reserva creada desde la aplicación web de Bellidel. Fecha de reserva: " + fechaFormateada + ". " + "Número de comensales: " + comensales + ". " + "Turno: " + turnoID;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Token", token);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("codcliente", codcliente);
        map.add("cifnif", cifnif);
        map.add("nombrecliente", nombre + " " + surname);
        // map.add("fecha", fechaFormateada); // Se pone automaticamente desde FS al crear la factura, nuestra fecha del día de reserva se pone en descripción
        map.add("observaciones", observaciones);
        map.add("idfactura", bookingID);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String url = "http://bellidel.eu:8081/api/3/facturaclientes";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        
        logger.info("Factura creada en FacturaScripts: " + response.getBody());

        // Obtenemos el ID de la factura creada
        String idFactura = obtenerIdFacturaDeRespuesta(response.getBody());
        logger.info("ID de la factura creada: " + idFactura);

        // Obtenemos los datos del producto FS ////////////////////////////////

        String infoProducto = obtenerInfoProducto(token, menuID);
        logger.info("Información del producto obtenida de FacturaScripts: " + infoProducto);
        String precio = obtenerPrecio(infoProducto);
        String descripcion = obtenerDescripcionProducto(infoProducto);


        //////////////////////////////////////////////////////////////////////

        // Creamos la línea de factura
        crearLineaFactura(token, idFactura, menuID, precio, comensales, descripcion);
        
    }

    // FacturaScripts API 3 - Crear línea de factura
    public void crearLineaFactura(String token, String idFactura, String referenciaProducto, String pvpUnitario, String cantidad, String descripcion) {
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Token", token);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("idfactura", idFactura);
        map.add("referencia", referenciaProducto);
        map.add("pvpunitario", pvpUnitario);
        map.add("cantidad", cantidad);
        map.add("descripcion", descripcion);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String url = "http://bellidel.eu:8081/api/3/lineafacturaclientes";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        
        logger.info("Línea de factura creada en FacturaScripts: " + response.getBody());
        
    }

    // FacturaScripts API 3 - Obtener facturas (pdf) de un cliente
    public List<Factura> obtenerFacturas(String token, String codcliente) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Token", token);

        HttpEntity<String> request = new HttpEntity<>(headers);

        String url = "http://bellidel.eu:8081/api/3/facturaspdf?idcliente=" + codcliente;

        try {
            ResponseEntity<RespuestaFsFacturasCli> response = restTemplate.exchange(
                url, HttpMethod.GET, request, RespuestaFsFacturasCli.class);

            logger.info("Facturas obtenidas de FacturaScripts");
            if (response.getBody() != null) {
                return response.getBody().getFacturas_pdf();
            } else {
                return new ArrayList<>();
            }
        } catch (RestClientException e) {
            logger.error("Error al obtener facturas: " + e.getMessage());
            throw e;
        }
    }




    // Facturascripts API 3 - Obtener información de un producto
    public String obtenerInfoProducto(String token, String idproducto) {
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Token", token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        String url = "http://bellidel.eu:8081/api/3/productos?filter[idproducto]=" + idproducto;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        
        logger.info("Información del producto obtenida de FacturaScripts: " + response.getBody());

        return response.getBody();
        
    }

    // Funciones auxiliares - Convertir formato fecha
    private String convertirFecha(String fechaOriginal, String formatoOriginal, String formatoDeseado) {
        SimpleDateFormat formatoOrigen = new SimpleDateFormat(formatoOriginal);
        SimpleDateFormat formatoDestino = new SimpleDateFormat(formatoDeseado);
        try {
            Date fecha = formatoOrigen.parse(fechaOriginal);
            return formatoDestino.format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    // Funciones auxiliares - Obtener ID de factura de la respuesta de FacturaScripts
    private String obtenerIdFacturaDeRespuesta(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        String idFactura = null;
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataNode = rootNode.path("data");
            logger.info("Obteniendo ID de la factura creada en FacturaScripts" + dataNode);
            idFactura = dataNode.path("idfactura").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing JSON", e);
        }
        
        return idFactura;
    }

    // Funciones auxiliares - Obtener precio
    private String obtenerPrecio(String responseBody) {
        logger.info("Obteniendo precio del producto de FacturaScripts" + responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        String precio = null;
        
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataNode = rootNode.get(0);
            precio = dataNode.path("precio").asText();
            logger.info("Precio del producto: " + dataNode);  
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing JSON", e);
        }
        
        return precio.toString();
    }

    // Funciones auxiliares - Obtener descripción del producto
    private String obtenerDescripcionProducto(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        String descripcion = null;
        
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataNode = rootNode.get(0);
            descripcion = dataNode.path("descripcion").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing JSON", e);
        }
        
        return descripcion;
    }

}