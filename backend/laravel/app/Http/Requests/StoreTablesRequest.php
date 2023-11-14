<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class StoreTablesRequest extends FormRequest
{
    public function authorize()
    {
        return true;
    }

    public function rules()
    {
        return [
            'capacity' => 'required|integer|min:1',
            'location' => 'required|string',
            'availability' => 'required|integer|min:0|max:1',
        ];
    }
}