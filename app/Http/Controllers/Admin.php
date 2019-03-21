<?php

namespace App\Http\Controllers;

use App\Company;
use App\Person;
use App\User;
use Illuminate\Http\Request;

class Admin extends Controller
{
    //
    public function __construct()
    {
        $this->middleware('cors');
        $this->middleware('auth:api', ['except' => ['companies','people']]);
    }

    public function companies(){
        $data = Company::join('users','companies.user_id','=','users.id')
            ->get();

        return response()->json([
            'success' => true,
            'data' => $data
        ]);
    }

    public function people(){
        $data = Person::join('users','people.user_id','=','users.id')
            ->get();

        return response()->json([
            'success' => true,
            'data' => $data
        ]);
    }

    public function addUser(Request $request){
        $this->validate($request,[
            'name' => 'required|string|max:255',
            'email' => 'required|string|email|max:255|unique:users',
            'mobile' => 'required|string|max:255|unique:users',
            'password' => 'required|string|min:6',
            'type' => 'required|integer|min:2|max:3',
        ]);

        $attr = $request->only(['name', 'email','mobile','type','password']);
        $attr['password'] = bcrypt($attr['password']);

        $user = new User($attr);
        $user->save();

        if($attr['type']==2){
            $company = new Company;
            $company->official_email = $request->input('email');
            $user->company()->save($company);
        }

        if($attr['type']==3){
            $person = new Person();

            $user->person()->save($person);
        }

        return response()->json([
            'success' => true,
            'message' => 'User Created'
        ]);
    }

    public function deleteCompany(Request $request){
        $this->validate($request,[
            'company_id' => 'required|integer',
        ]);

        $company = User::findOrFail($request->input('company_id'));

        $company->delete();

        return response()->json([
            'success' => true,
            'message' => 'Company Deleted'
        ]);
    }

    public function deletePerson(Request $request){
        $this->validate($request,[
            'person_id' => 'required|integer',
        ]);

        $person = User::findOrFail($request->input('person_id'));

        $person->delete();

        return response()->json([
            'success' => true,
            'message' => 'Person Deleted'
        ]);
    }
}
