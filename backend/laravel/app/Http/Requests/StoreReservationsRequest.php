<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class StoreReservationsRequest extends FormRequest
{
    public function authorize(): bool
    {
        return true;
    }

    public function rules(): array
    {
        return [
            'clientID' => 'sometimes',
            'tableID' => 'sometimes',
            'turnID' => 'sometimes',
            'booking_day' => 'sometimes',
            'diners_number' => 'sometimes',
            'status' => 'sometimes',
            'menuID' => 'sometimes'
        ];
    }
}