<?php

namespace App\Http\Controllers;

use App\Category;
use App\Company;
use App\TrainingOpportunity;
use Illuminate\Http\Request;

class TrainingOpportunities extends Opportunities
{
    //
    public function __construct()
    {
        parent::__construct();
        $this->opportunity = new TrainingOpportunity();
    }


    public function add(Request $request){
        $this->validate($request,[
            'title' => 'required|string',
            'company_id' => 'required|integer',
            'category_id' => 'required|integer',
            'duration' => 'required|integer',
            'start' => 'required|date',
            'requirements' => 'required',
            'number_of_trainees' => 'required|integer',
            'place'=> 'required',
            'subject' => 'required',
            'paid'=> 'required|boolean'
        ]);

        $company = Company::findOrFail($request->input('company_id'));
        $category = Category::findOrFail($request->input('category_id'));

        $this->authorize('update',$company);

        $attr = $request->only(['title','duration','start','requirements','paid','number_of_trainees','place','subject']);
        $opportunity = new TrainingOpportunity($attr);

        $opportunity->add($company,$category);


        return response()->json([
            'success'=>true,
            'message'=>"Training Opportunity Added Successfully!"
        ]);
    }

    public function delete(Request $request)
    {
        $this->validate($request,[
            'training_opportunity_id' => 'required|integer'
        ]);

        $trainignOpportunity = TrainingOpportunity::findOrFail($request->input('training_opportunity_id'));

        if($trainignOpportunity->company_id != auth()->user()->company()->first()->id){
            throw new UnauthorizedException('Action Unauthorized');
        }

        $trainignOpportunity->delete();

        return response()->json([
            'success' => true,
            'message' => 'Deleted Successfully'
        ]);
    }
}
