package com.springboot.spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

import com.springboot.spring.model.Factura;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RespuestaFsFacturasCli {
    private String msg_status;

    private List<Factura> facturas_pdf;
}