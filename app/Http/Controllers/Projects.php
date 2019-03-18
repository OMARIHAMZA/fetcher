<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Company;
use App\Project;

class projects extends Controller
{
    //
    public function __construct()
    {
        $this->middleware('auth');
    }
    public function add(Request $request){
        $this->validate($request,[
            'company_id' => 'required|integer',
            'name' => 'required|string',
            'description' => 'required|string',
        ]);

        $company = Company::findOrFail($request->input('company_id'));

        $this->authorize('update',$company);

        $attr = $request->only(['name','description']);

        $project = new Project($attr);

        $project->add($company);


        return response()->json([
            'success'=>true,
            'message'=>"Project Added Successfully!"
        ]);
    }
}
