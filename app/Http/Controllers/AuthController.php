<?php

namespace App\Http\Controllers;

use App\Person;
use App\User;
use http\Env\Response;
use Illuminate\Support\Facades\Auth;
use App\Http\Controllers\Controller;
use App\Company;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class AuthController extends Controller
{
    /**
     * Create a new AuthController instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('cors');
        $this->middleware('auth:api', ['except' => ['login','register']]);
    }

    /**
     * Get a JWT via given credentials.
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function login(Request $request)
    {
        $credentials = array_merge(request(['email', 'password']),['status' => 1]);
        if (! $token = auth()->attempt($credentials)) {
            $credentials = request(['email', 'password']);
            if ($second_token = auth()->attempt($credentials)) {
                return response()->json(['success'=> false ,'error' => 'Pending Activation'], 401);
            }
            return response()->json(['success'=> false ,'error' => 'Unauthorized'], 401);
        }

        return $this->respondWithToken($token);
    }

    public function register(Request $request){

        $this->validate($request,[
            'name' => 'required|string|max:255',
            'email' => 'required|string|email|max:255|unique:users',
            'mobile' => 'required|string|max:255|unique:users',
            'password' => 'required|string|min:6',
            'type' => 'required|integer|min:2|max:3',
        ]);

        $attr = $request->only(['name', 'email','mobile','type','password']);
        $attr['password'] = bcrypt($attr['password']);

        $user = new \App\User($attr);
        $user->save();

        if($attr['type']==2){
            $company = new Company;
            if($request->hasFile('commercial_record')){
                $path = $request->file('commercial_record')->store('CompanyCommercialRecords');
                $company->commercial_record = $path;
            }
            $user->company()->save($company);
        }

        if($attr['type']==3){
            $person = new Person();
            if($request->hasFile('id_photo')){
                $id_photo = $request->file('id_photo')->store('PeopleIds');
                $person->id_photo = $id_photo;
            }

            $user->person()->save($person);
        }

        /*$token = auth()->login($user);

        return $this->respondWithToken($token);*/

        return response()->json(['success'=> true ,'message' => 'Pending Activation']);
    }

    /**
     * Get the authenticated User.
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function me()
    {
        return response()->json(
            [
                'success'=> true,
                'user' => auth()->user()
            ]
        );
    }

    /**
     * Log the user out (Invalidate the token).
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function logout()
    {
        auth()->logout();

        return response()->json(['success'=> true,'message' => 'Successfully logged out']);
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
            'success' => true,
            'access_token' => $token,
            'token_type' => 'bearer',
            'expires_in' => auth()->factory()->getTTL() * 60
        ]);
    }
}