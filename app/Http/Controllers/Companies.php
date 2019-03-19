<?php

namespace App\Http\Controllers;

use App\Branch;
use App\Company;
use App\CompanyPhoto;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\App;
use Illuminate\Support\Facades\DB;

class Companies extends Controller
{
    public function __construct()
    {
        $this->middleware('auth',['except' => ['info']]);
    }
    public function info($id){
        $company = Company::findOrFail($id);
        $branches = $company->branches()->get();
        $photos = $company->companyPhotos()->get();
        $projects = $company->projects()->get();

        return response()->json([
            'success'=>true,
            'company' => $company,
            'branches' => $branches,
            'photos' => $photos,
            'projects'=> $projects
        ]);
    }
    public function currentCompany(){
        $this->authorize('isCompany',Company::class);
        $company = auth()->user()->company()->first();

        return response()->json([
            'success'=>true,
            'data'=>$company
        ]);
    }
    public function update(Request $request, $id){


        $this->validate($request,[
            'name' => 'required|string',
            'website' => 'required',
            'official_email'=>'required|email',
            'main_address' => 'required|string'
        ]);

        $company = Company::find($id);

        $this->authorize('update',$company);

        $company->name = $request->input('name');
        $company->website = $request->input('website');
        $company->official_email = $request->input('official_email');
        $company->main_address = $request->input('main_address');

        $company->save();

        return response()->json([
            'success'=>true,
            'message'=>"Company Info Updated Successfully!"
        ]);
    }

    public function addBranch(Request $request){
        $this->validate($request,[
            'company_id' => 'required|integer',
            'address' => 'required',
        ]);

        $company = Company::findOrFail($request->input('company_id'));

        $this->authorize('update',$company);

        $attr = $request->only(['address']);

        $branch = new Branch($attr);

        $branch->address = $request->input('address');

        $company->addBranch($branch);

        return response()->json([
            'success'=>true,
            'message'=>"Company Branch Added Successfully!"
        ]);
    }

    public function addCompanyPhoto(Request $request){
        $this->validate($request,[
            'company_id' => 'required|integer',
            'photo' => 'required',
        ]);

        $company = Company::findOrFail($request->input('company_id'));

        $this->authorize('update',$company);

        $path = $request->file('photo')->store('CompanyPhotos');

        $companyPhoto = new CompanyPhoto(['photo'=>$path]);

        $company->addCompanyPhoto($companyPhoto);

        return response()->json([
            'success'=>true,
            'message'=>"Company Photo Added Successfully!",
            'photo'=>$path
        ]);
    }

}
