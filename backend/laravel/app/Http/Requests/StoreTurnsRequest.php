<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class StoreTurnsRequest extends FormRequest
{

    public function authorize(): bool
    {
        return true;
    }


    public function rules(): array
    {
        return [
            'meal' => 'required|string',
            'turn_hour' => 'required|date_format:H:i:s',
        ];
    }
}
