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

            StringBuilder sqlBuilder = new StringBuilder("SELECT *, CASE WHEN ");

            if (_tableQueryParam.getCapacity() != null) {
                sqlBuilder.append("capacity = ").append(_tableQueryParam.getCapacity());
            }

            if (_tableQueryParam.getDate() != null) {
                sqlBuilder.append(" AND ( booking_day = ").append(_tableQueryParam.getDate()).append(" OR booking_day IS NULL ) THEN 'true' ELSE 'false' END as meets_filters FROM ReservationFilters ");
            }

            if (_tableQueryParam.getTurn() != null) {
                sqlBuilder.append(" WHERE turn_hour = '").append(_tableQueryParam.getTurn()).append("'");
            }

            sqlBuilder.append(" ORDER BY tableID");

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