<?php

namespace App\Http\Controllers;

use App\Models\Reservations;
use App\Http\Resources\ReservationsResource;
use App\Http\Requests\StoreReservationsRequest;

class ReservationsController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        //
        // return ReservationsResource::collection(Reservations::all());
        return ReservationsResource::collection(Reservations::where('status', 0)->get());
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
    public function store(StoreReservationsRequest $request)
    {
        return ReservationsResource::make(Reservations::create($request->validated()));
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        return ReservationsResource::collection(Reservations::where('bookingID', $id)->get());
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
    public function update(StoreReservationsRequest $request, string $id)
    {
        $update = Reservations::where('bookingID', $id)->update($request->validated());
        if ($update == 1) {
            return response()->json([
                "Message" => "Update Correctly"
            ]);
        } else {
            return response()->json([
                "Status" => "Not found"
            ], 404);
        }
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        //

        $table = Tables::find($id);
        $delete = Tables::where('tableID', $id)->delete();

        $table->turns()->detach();
        if ($delete == 1){
            return response()->json([
                "Message" => "Deleted correctly"
            ]);
        } else {
            return response()->json([
                "Status" => "Not found"
            ], 404);
        }
    }
}
