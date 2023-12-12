<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\TablesController;
use App\Http\Controllers\TurnsController;
use App\Http\Controllers\MenusController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

// Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
//     return $request->user();
// });
Route::group(['middleware' => ['cors']], function () {
    //Rutas a las que se permitirá acceso
    Route::resource('tables', TablesController::class);
    Route::resource('turns', TurnsController::class);
    Route::resource('menus', MenusController::class);
    // Route::get('tables/static', [TablesController::class, 'index']);
});
