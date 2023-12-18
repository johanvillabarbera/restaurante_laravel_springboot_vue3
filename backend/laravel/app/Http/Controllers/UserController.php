<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Resources\UserResource;
use App\Models\User;
use App\Http\Requests\StoreUserRequest;
use App\Http\Requests\LoginUserRequest;


class UserController extends Controller
{
    protected User $user;

    public function __construct(User $user)
    {
        $this->user = $user;
    }

    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return UserResource::collection(User::all());
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
    public function store(StoreUserRequest $request)
    {
        $username_exist = User::where('username', $request->validated()['username'])->get()->count();
        if ($username_exist === 1) {
            return response()->json([
                "Status" => "Username taken"
            ], 400);
        }

        $email_exist = User::where('email', $request->validated()['email'])->get()->count();
        if ($email_exist === 1) {
            return response()->json([
                "Status" => "Email taken"
            ], 400);
        }

        $user = $this->user->create($request->validated());
        return UserResource::make($user);
    }

    public function login(LoginUserRequest $request)
    {
        $user = User::where('username', $request->validated()['username'])->first();
        if (!$user) {
            return response()->json([
                "error" => "Unauthorized"
            ], 401);
        }
// Token is true but i need the jwt token
        $token = auth('api')->attempt($request->only(['username', 'password']));
        //$token = auth('api')->attempt($request->validated());
        //$token = Auth()->login($user);

        //$token = auth()->tokenById(15);
        if (!$token) {
            return response()->json([
                "token" => $token,
                "error" => "Unauthorized"
            ], 401);
        }

        if ($user->IDrol != 2) {
            return response()->json([
                "error" => "Unauthorized"
            ], 401);
        }
        //$newToken = auth()->refresh();
        return response()->json(['token' => $token, 'user' => $user]);
    } //login

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

    public function logout()
    {
        try {
            if (auth('api')->user() === null) {
                return response()->json(['error' => 'logout error'], 500);
            }
            auth('api')->logout();
            return response()->json(["Message" => "Logout correctly"]);
        } catch (\Throwable $th) {
            return response()->json(['error' => 'logout error'], 500);
        }
    }

    public function getUserToken()
    {
        try {
            return UserResource::make(auth('api')->user());
        } catch (\Throwable $th) {
            return response()->json(['error' => 'get user error'], 401);
        }
    }

    /**
     * Refresh a token.
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function refresh()
    {
        return $this->respondWithToken(auth()->refresh());
    }

    /**
     * Get the token array structure.
     *
     * @param  string $token
     *
     * @return \Illuminate\Http\JsonResponse
     */
    protected function respondWithToken($token)
    {
        return response()->json([
            'access_token' => $token,
            'token_type' => 'bearer',
            'expires_in' => auth()->factory()->getTTL() * 60
        ]);
    }

    public function isAdmin()
    {
        try {
            if (auth('api')->user() == null || auth('api')->user()->IDrol != 2) {
                return response()->json([
                    "error" => "Unauthorized"
                ], 403);
            }
            return response()->json([
                "msg" => "You are and admin"
            ], 200);
        } catch (\Throwable $th) {
            return response()->json([
                "error" => "Unauthorized"
            ], 403);
        }
    }
    
}
