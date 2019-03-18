<?php

namespace App\Http\Controllers;

use App\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $data = [];
        if($request->user()->isCompany()){
            $company = $request->user()->company()->first();
            $data['company_id'] = $company['id'];
            $data['user_id'] = $company['user_id'];
            $data['name'] = $company['name'];
            $data['website'] = $company['website'];
            $data['official_email'] = $company['official_email'];
            $data['main_address'] = $company['main_address'];
        }
        return view('home',$data);
    }
}
