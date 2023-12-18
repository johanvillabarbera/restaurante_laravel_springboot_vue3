<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\TablesController;
use App\Http\Controllers\TurnsController;
use App\Http\Controllers\MenusController;
use App\Http\Controllers\UserController;

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
// Add another middleware to the group:

Route::group(['middleware' => ['cors', 'admin']], function () {
    //Rutas a las que se permitirá acceso
    Route::resource('tables', TablesController::class);
    Route::resource('turns', TurnsController::class);
    Route::resource('menus', MenusController::class);
    Route::resource('users', UserController::class);
    // Route::get('tables/static', [TablesController::class, 'index']);
    Route::get('profile', [UserController::class, 'getUserToken']);
    Route::get('logout', [UserController::class, 'logout']);
    Route::get('isAdmin', [UserController::class, 'isAdmin']);
});

Route::post('login', [UserController::class, 'login']);