<?php

namespace App\Http\Controllers;

use App\Branch;
use App\Company;
use App\CompanyPhoto;
use App\Employment;
use App\Training;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\App;
use Illuminate\Support\Facades\DB;
use App\Project;

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
            'main_address' => 'required|string',
        ]);

        $company = Company::find($id);

        $this->authorize('update',$company);

        if($request->hasFile('commercial_record')){
            $path = $request->file('commercial_record')->store('CompanyCommercialRecords');
            $company->commercial_record = $path;
        }

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
    public function deleteBranch(Request $request){
        $this->validate($request,[
            'branch_id' => 'required|integer'
        ]);

        $branch = Branch::findOrFail($request->input('branch_id'));

        if($branch->company_id != auth()->user()->company()->first()->id){
            throw new UnauthorizedException('Action Unauthorized');
        }
        $branch->delete();
        return response()->json([
            'success'=>true,
            'message'=>"Branch deleted Successfully!"
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

    public function deleteCompanyPhoto(Request $request){
        $this->validate($request,[
            'photo_id' => 'required|integer'
        ]);

        $companyPhoto = CompanyPhoto::findOrFail($request->input('photo_id'));

        if($companyPhoto->company_id != auth()->user()->company()->first()->id){
            throw new UnauthorizedException('Action Unauthorized');
        }
        $companyPhoto->delete();
        return response()->json([
            'success'=>true,
            'message'=>"Photo deleted Successfully!"
        ]);
    }

    public function deleteProject(Request $request){
        $this->validate($request,[
            'project_id' => 'required|integer'
        ]);

        $project = Project::findOrFail($request->input('project_id'));

        if($project->company_id != auth()->user()->company()->first()->id){
            throw new UnauthorizedException('Action Unauthorized');
        }
        $project->delete();
        return response()->json([
            'success'=>true,
            'message'=>"Project deleted Successfully!"
        ]);
    }

    public function getTrainees($company_id){
        $company = Company::findOrFail($company_id);

        $this->authorize('update',$company);

        $training = new Training();

        $data = $training->getByCompany($company);

        return response()->json([
            'success'=>true,
            'data' =>$data
        ]);
    }
    public function getEmployees($company_id){
        $company = Company::findOrFail($company_id);

        $this->authorize('update',$company);

        $employment = new Employment();

        $data = $employment->getByCompany($company);

        return response()->json([
            'success'=>true,
            'data' =>$data
        ]);
    }


}
