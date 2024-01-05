// Booking repository only for save
package com.springboot.spring.repository;
import com.springboot.spring.model.BookingsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingUserRepository extends JpaRepository<BookingsUser, Long> {

    @Query(value = "SELECT bookings.bookingID, bookings.booking_day, bookings.status, bookings.diners_number, menus.price AS price_menu, menus.price, turns.turn_hour, ROUND(menus.price * bookings.diners_number ) AS price_total, menus.name AS name_menu " +
                    "FROM viberest.bookings " +
                    "INNER JOIN viberest.menus ON menus.menuID = bookings.menuID " +
                    "INNER JOIN viberest.turns ON turns.turnID = bookings.turnID " +
                    "WHERE bookings.clientID = :clientID", nativeQuery = true)
    List<BookingsUser> findBookingByClientID(@Param("clientID") Long clientID);
}
