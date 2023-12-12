<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;
use App\Models\Tables;

class Turns extends Model
{
    use HasFactory;
    protected $table = 'turns';
    protected $primaryKey = 'turnID'; // Indicamos que es la clave primaria
    public $incrementing = true; // Indicamos que es autoincremental
    public $timestamps = false;

    protected $fillable = [
        'meal',
        'turn_hour',
    ];

    public function tables(): BelongsToMany
    {
        return $this->belongsToMany(Tables::class, 'turns_tables', 'idturn', 'idtable');
    }
}
