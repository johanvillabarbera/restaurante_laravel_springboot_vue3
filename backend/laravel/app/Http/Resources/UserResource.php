<?php

namespace App\Http\Resources;

// use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

class UserResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @return array<string, mixed>
     */
    public function toArray($request)
    {
        return [
            'clientID' => $this->clientID,
            'name' => $this->name,
            'surname' => $this->surname,
            'email' => $this->email,
            'password' => $this->password,
            'tlf' => $this->tlf,
            'address' => $this->address,
            'birth_date' => $this->birth_date,
            'IDrol' => $this->IDrol,
            'username' => $this->username,
        ];
    }
}
