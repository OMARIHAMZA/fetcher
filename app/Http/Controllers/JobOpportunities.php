<?php

namespace App\Http\Controllers;
use App\Company;
use App\Category;
use App\JobOpportunity;
use Illuminate\Http\Request;

class JobOpportunities extends Opportunities
{
    //
    public function __construct()
    {
        parent::__construct();
        $this->opportunity = new JobOpportunity();
    }

    public function add(Request $request){
        $this->validate($request,[
            'title' => 'required|string',
            'company_id' => 'required|integer',
            'category_id' => 'required|integer',
            'duration' => 'required|integer',
            'start' => 'required|date',
            'end_of_submission' => 'required|date',
            'requirements' => 'required',
            'number_of_employees' => 'required|integer',
            'place'=> 'required',
            'days'=> 'required',
            'salary'=> 'required|numeric'
        ]);

        $company = Company::findOrFail($request->input('company_id'));
        $category = Category::findOrFail($request->input('category_id'));

        $this->authorize('update',$company);

        $attr = $request->only(['title','duration','start','end_of_submission','requirements','number_of_employees','salary','place','days']);
        $opportunity = new JobOpportunity($attr);

        $opportunity->add($company,$category);


        return response()->json([
            'success'=>true,
            'message'=>"Job Opportunity Added Successfully!"
        ]);
    }


    public function delete(Request $request)
    {
        $this->validate($request,[
            'job_opportunity_id' => 'required|integer'
        ]);

        $jobOpportunity = JobOpportunity::findOrFail($request->input('job_opportunity_id'));

        if($jobOpportunity->company_id != auth()->user()->company()->first()->id){
            throw new UnauthorizedException('Action Unauthorized');
        }

        $jobOpportunity->delete();

        return response()->json([
            'success' => true,
            'message' => 'Deleted Successfully'
        ]);

    }
}
