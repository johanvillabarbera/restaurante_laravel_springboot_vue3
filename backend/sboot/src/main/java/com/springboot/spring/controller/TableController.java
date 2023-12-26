package com.springboot.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springboot.spring.model.Tables;
import com.springboot.spring.model.ReservationFiltersView;
import com.springboot.spring.repository.TableRepository;
import com.springboot.spring.model.TableQueryParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;


@CrossOrigin(origins = "http://bellidel.eu:8000")
@RestController
@RequestMapping("/tables")
public class TableController {

    // LOGS
    private static final Logger logger = LoggerFactory.getLogger(TableController.class);

    @Autowired
    TableRepository tableRepository;
    
    // List of tables
    @GetMapping("")
    public ResponseEntity<List<Tables>> getAllTables() {
        try {
            List<Tables> tables = new ArrayList<Tables>();

            tableRepository.findAll().forEach(tables::add);

            if (tables.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private EntityManager entityManager;


    // Obtain tables to filters
    @GetMapping("/filters")
    public ResponseEntity<List<ReservationFiltersView>> getTablesWithFilters(@ModelAttribute TableQueryParam _tableQueryParam) {
        try {

            // CONSULTA BASE
            // SELECT *,
            // CASE 
            //     WHEN capacity = 4 AND (booking_day = '2023-11-23' OR booking_day IS NULL) 
            //     THEN 'Sí' 
            //     ELSE 'No' 
            // END as EsLaBuscada
            // FROM ReservationFilters
            // WHERE turn_hour = '12:00 - 14:00';

            _tableQueryParam.setCapacity(_tableQueryParam.getCapacity());
            _tableQueryParam.setDate(_tableQueryParam.getDate());
            _tableQueryParam.setTurn(_tableQueryParam.getTurn());

            // StringBuilder sqlBuilder = new StringBuilder("SELECT *, CASE WHEN ");

            // if (_tableQueryParam.getCapacity() != null) {
            //      sqlBuilder.append("capacity = ").append(_tableQueryParam.getCapacity());
            //     //sqlBuilder.append("capacity = ").append(4);
            // }

            // if (_tableQueryParam.getDate() != null) {
            //     sqlBuilder.append(" AND ( booking_day != '").append(_tableQueryParam.getDate()).append("' OR booking_day IS NULL ) THEN 'true' ELSE 'false' END as meets_filters, CASE WHEN booking_day = '").append(_tableQueryParam.getDate()).append("' THEN 'true' ELSE 'false' END as estado_reserva");
            // }

            // if (_tableQueryParam.getTurn() != null) {
            //     sqlBuilder.append(" FROM ReservationFilters WHERE turn_hour = '").append(_tableQueryParam.getTurn()).append("' AND (booking_day = '").append(_tableQueryParam.getDate()).append("' OR booking_day != '").append(_tableQueryParam.getDate()).append("' OR booking_day IS NULL )");
            // }

            StringBuilder sqlBuilder = new StringBuilder();

            // Base de la consulta SQL
            sqlBuilder.append("SELECT ")
                .append("t.tableID, ")
                .append("t.capacity, ")
                .append("t.location, ")
                .append("t.availability, ")
                .append("tt.turnID, ")
                .append("tt.turn_hour, ")
                .append("CASE WHEN b.bookingID IS NOT NULL THEN 'true' ELSE 'false' END AS estado_reserva, ")
                .append("CASE WHEN t.capacity = ")
                .append(_tableQueryParam.getCapacity()) 
                .append(" THEN 'true' ELSE 'false' END AS meets_filters ")
                .append("FROM tables t ")
                .append("CROSS JOIN turns tt ")
                .append("LEFT JOIN (SELECT * FROM bookings WHERE booking_day = '")
                .append(_tableQueryParam.getDate()) // Asumiendo que getDate() retorna '2023-12-26'
                .append("') b ON t.tableID = b.tableID AND tt.turnID = b.turnID ")
                .append("WHERE tt.turn_hour = '")
                .append(_tableQueryParam.getTurn()) // Asumiendo que getTurnHour() retorna '12:00 - 14:00'
                .append("' ORDER BY t.tableID");

            // if (_tableQueryParam.getDate() != null) {
            //     sqlBuilder.append(" AND (booking_day = '").append(_tableQueryParam.getDate()).append("' OR booking_day IS NULL)");
            // }
            
            String sql = sqlBuilder.toString();

            logger.info(sql);

            Query query = entityManager.createNativeQuery(sql, ReservationFiltersView.class);

            List<ReservationFiltersView> results = query.getResultList();

            if (results.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(results, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // List One Table By Id
    @GetMapping("/{id}")
    public ResponseEntity<Tables> getTableById(@PathVariable("id") long id) {
        Optional<Tables> tableData = tableRepository.findById(id);

        if (tableData.isPresent()) {
            return new ResponseEntity<>(tableData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}