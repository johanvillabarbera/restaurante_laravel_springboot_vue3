<?php

namespace App\Http\Controllers;

use App\Models\Menus;
use App\Http\Resources\MenusResource;
use App\Http\Requests\StoreMenusRequest;

class MenusController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return MenusResource::collection(Menus::all());
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
    public function store(StoreMenusRequest $request)
    {
        return MenusResource::make(Menus::create($request->validated()));
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        return MenusResource::collection(Menus::where('menuID', $id)->get());
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
    public function update(StoreMenusRequest $request, string $id)
    {
        $update = Menus::where('menuID', $id)->update($request->validated());
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
    }
}
