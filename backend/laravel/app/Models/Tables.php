<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Tables extends Model
{
    use HasFactory;
    protected $table = 'tables';
    protected $primaryKey = 'tableID'; // Indicamos que es la clave primaria
    public $incrementing = true; // Indicamos que es autoincremental
    public $timestamps = false; 

    protected $fillable = [
        'capacity',
        'location',
        'availability',
    ];

}
