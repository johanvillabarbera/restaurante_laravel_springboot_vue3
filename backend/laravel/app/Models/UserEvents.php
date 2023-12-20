<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class UserEvents extends Model
{
    use HasFactory;

    protected $table = 'user_event_table';
    protected $primaryKey = 'EventID';

    protected $fillable = [
        'EventID',
        'UserID',
        'event_type',
        'event_timestamp',
        'UserIP',
        'event_details',
        'user_agent'
    ];

    public $timestamps = false;

}
