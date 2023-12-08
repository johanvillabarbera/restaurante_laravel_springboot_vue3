<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class StoreTablesRequest extends FormRequest
{
    public function authorize(): bool
    {
        return true;
    }

    public function rules(): array
    {
        return [
            'capacity' => 'required|integer|min:1',
            'location' => 'required|string',
            'availability' => 'required|integer|min:0|max:1',
            'turns' => 'array:name,turns',
        ];
    }
}