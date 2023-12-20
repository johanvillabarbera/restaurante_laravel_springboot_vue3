<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Resources\UserEventsResource;
use App\Models\UserEvents;
use Illuminate\Support\Facades\Http;


class UserEventsController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $data = UserEventsResource::collection(UserEvents::all());
        //$data_json = json_decode($data);

        // necesito pasar el array por json a la url bellidel:7200/statisctics 
        $response = Http::post('http://bellidel.eu:7200/predict', $data);
        //$response = Http::get('http://bellidel.eu:7200/');
        
        return json_decode($response->body());
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(string $id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        //
    }
}
