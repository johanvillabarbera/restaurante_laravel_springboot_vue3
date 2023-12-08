<?php

namespace App\Http\Controllers;

use App\Models\Tables;
use App\Models\Turns;
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
        $data = $request->except(['turns']);

        if ($request->turns !== null) {
            $turns_id = [];
            foreach ($request->turns as $c) {
                array_push($turns_id, $c['turnID']);
            }
        }

        $update = Tables::where('tableID', $id)->update($data);
        if ($update == 1){
            if (count($turns_id) > 0) {
                $mesa = Tables::where('tableID', $id)->firstOrFail();
                $mesa->turns()->detach();
                $mesa->turns()->sync($turns_id);
            }

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
