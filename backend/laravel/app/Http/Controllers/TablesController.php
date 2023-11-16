<?php

namespace App\Http\Controllers;

use App\Models\Tables;
use App\Http\Resources\TablesResource;
use App\Http\Requests\StoreTablesRequest;

class TablesController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        //
        return TablesResource::collection(Tables::all());
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
    public function store(StoreTablesRequest $request)
    {
        //
        return TablesResource::make(Tables::create($request->validated()));
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        return TablesResource::collection(Tables::where('tableID', $id)->get());
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
    public function update(StoreTablesRequest $request, string $id)
    {
        //
        $update = Tables::where('tableID', $id)->update($request->validated());
        if ($update == 1){
            return response()->json([
                "Message" => "Updated correctly"
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
        $delete = Tables::where('tableID', $id)->delete();
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
