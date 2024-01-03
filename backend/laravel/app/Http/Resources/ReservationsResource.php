<?php

namespace App\Http\Resources;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

class ReservationsResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @return array<string, mixed>
     */
    public function toArray($request)
    {
        return [
            'bookingID' => $this->bookingID,
            'clientID' => $this->clientID,
            'tableID' => $this->tableID,
            'turnID' => $this->turnID,
            'booking_day' => $this->booking_day,
            'diners_number' => $this->diners_number,
            'status' => $this->status,
            'menuID' => $this->menuID
        ];
    }
}
