<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class Actions extends Controller
{
    //

    public function __construct()
    {
        $this->middleware('auth:api');
    }

    public function updateLocation(Request $request){
        $this->validate($request,[
            'lat' => 'required|numeric',
            'long' => 'required|numeric',
        ]);

        auth()->user()->updateLocation($request->input('lat'),$request->input('long'));

        return response()->json([
            'success' => true,
            'message' => 'Location Updated Successfully'
        ]);
    }
}
