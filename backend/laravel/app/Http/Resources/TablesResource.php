<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\JsonResource;

class TablesResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return array|\Illuminate\Contracts\Support\Arrayable|\JsonSerializable
     */

    public function toArray($request)
    {
        $turns = [];
        foreach ($this->turns as $c) {
            $turn = [
                "turnID" => $c->turnID,
                "meal" => $c->meal,
                "turn_hour" => $c->turn_hour
            ];
            array_push($turns, $turn);
        }

        return [
            'tableID' => $this->tableID,
            'capacity' => $this->capacity,
            'location' => $this->location,
            'availability' => $this->availability,
            'turns' => $turns
        ];
    }
}