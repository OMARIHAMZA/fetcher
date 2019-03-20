<?php

namespace App\Http\Controllers;

use App\Company;
use App\CompanyEvaluation;
use App\Person;
use App\PersonEvaluation;
use Illuminate\Http\Request;

class Evaluations extends Controller
{
    //

    public function __construct()
    {
        $this->middleware('auth:api', ['except' => ['getPersonEvaluations','getCompanyEvaluations']]);
    }

    public function evaluateCompany(Request $request){
        $this->validate($request,[
            'person_id' => 'required|integer',
            'company_id' => 'required|integer',
            'rating' => 'required|integer'
        ]);

        $person = Person::findOrFail($request->input('person_id'));
        $this->authorize('update',$person);

        $company = Company::findOrFail($request->input('company_id'));
        $rating = $request->input('rating');

        $companyEvaluation = CompanyEvaluation::where('person_id','=',$request->input('person_id'));

        if(!$companyEvaluation->exists())
            $companyEvaluation = new CompanyEvaluation();
        else{
            $companyEvaluation = $companyEvaluation->first();
        }

        $companyEvaluation->evaluate($company,$person,$rating);

        return response()->json([
            'success' => true,
            'message' => 'Rating added successfully'
        ]);
    }

    public function evaluatePerson(Request $request){
        $this->validate($request,[
            'person_id' => 'required|integer',
            'company_id' => 'required|integer',
            'rating' => 'required|integer'
        ]);

        $company = Company::findOrFail($request->input('company_id'));

        $this->authorize('update',$company);

        $person = Person::findOrFail($request->input('person_id'));
        $rating = $request->input('rating');

        $personEvaluation = PersonEvaluation::where('company_id','=',$request->input('company_id'));

        if(!$personEvaluation->exists())
            $personEvaluation = new PersonEvaluation();
        else{
            $personEvaluation = $personEvaluation->first();
        }

        $personEvaluation->evaluate($company,$person,$rating);

        return response()->json([
            'success' => true,
            'message' => 'Rating added successfully'
        ]);
    }

    public function getPersonEvaluations($id){
        $evaluations = PersonEvaluation::where('person_id','=',$id)->get();

        return response()->json([
            'success' => true,
            'data' => $evaluations
        ]);
    }
    public function getCompanyEvaluations($id){
        $evaluations = CompanyEvaluation::where('company_id','=',$id)->get();

        return response()->json([
            'success' => true,
            'data' => $evaluations
        ]);
    }
}
