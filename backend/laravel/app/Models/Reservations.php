<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Reservations extends Model
{
    use HasFactory;
    protected $table = 'bookings';
    protected $primaryKey = 'bookingID'; // Indicamos que es la clave primaria
    public $incrementing = true; // Indicamos que es autoincremental
    public $timestamps = false;

    protected $fillable = [
        'clientID',
        'tableID',
        'turnID',
        'booking_day',
        'diners_number',
        'status',
        'menuID'
    ];
}