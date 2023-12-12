<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Menus extends Model
{
    use HasFactory;
    protected $table = 'menus';
    protected $primaryKey = 'menuID'; // Indicamos que es la clave primaria
    public $incrementing = true; // Indicamos que es autoincremental
    public $timestamps = false;

    protected $fillable = [
        'name',
        'description',
        'price'
    ];
}
