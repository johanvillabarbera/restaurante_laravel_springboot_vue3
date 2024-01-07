package com.springboot.spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

import com.springboot.spring.model.Factura;
import com.springboot.spring.model.BookingsUser;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaConFactura {
    private BookingsUser booking;
    private Factura facturas_pdf;
}