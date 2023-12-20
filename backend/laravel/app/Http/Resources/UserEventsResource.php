<?php

namespace App\Http\Resources;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

class UserEventsResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @return array<string, mixed>
     */
    public function toArray(Request $request): array
    {
        return [
            'EventID' => $this->EventID,
            'UserID' => $this->UserID,
            'event_type' => $this->event_type,
            'event_timestamp' => $this->event_timestamp,
            'UserIP' => $this->UserIP,
            'event_details' => $this->event_details,
            'user_agent' => $this->user_agent,
        ];
    }
}
