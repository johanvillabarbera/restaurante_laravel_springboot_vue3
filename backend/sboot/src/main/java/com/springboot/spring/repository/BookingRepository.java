// Booking repository only for save
package com.springboot.spring.repository;

import com.springboot.spring.model.Booking;
// import com.springboot.spring.model.BookingsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Consulta para comprobar si en esa booking_day, turnId y tableID la mesa esta ocupada los datos se lo pasara el controlador con :booking_day, :turnID, :tableID
    @Query(value = "SELECT * FROM bookings WHERE booking_day = :booking_day AND turnID = :turnID AND tableID = :tableID", nativeQuery = true)
    List<Booking> findBookingByBookingDayAndTurnIDAndTableID(@Param("booking_day") Date booking_day, @Param("turnID") Long turnID, @Param("tableID") Long tableID);

    
    @Query(value = "SELECT * FROM bookings WHERE clientID = :clientID", nativeQuery = true)
    List<Booking> findByClientID(@Param("clientID") Long clientID);

    // @Query(value = "SELECT bookings.booking_day, bookings.status, bookings.diners_number, menus.price AS price_menu, menus.price, turns.turn_hour, ROUND(menus.price * bookings.diners_number ) AS price_total
    //                 FROM viberest.bookings
    //                 INNER JOIN viberest.menus ON menus.menuID = bookings.menuID
    //                 INNER JOIN viberest.turns ON turns.turnID = bookings.turnID
    //                 WHERE bookings.clientID = :clientID", nativeQuery = true);
    // List<BookingsUser> findBookingByClientID(@Param("clientID") Long clientID);
}
