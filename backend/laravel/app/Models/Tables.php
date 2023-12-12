<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;
use App\Models\Turns;

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

    public function turns(): BelongsToMany
    {
        return $this->belongsToMany(Turns::class, 'turns_tables', 'idtable', 'idturn');
    }
}
