<?php

namespace App\Http\Controllers;

use App\Employment;
use App\JobOpportunity;
use App\Person;
use App\PersonJobApplication;
use App\PersonTrainingApplication;
use App\Training;
use App\TrainingOpportunity;
use Illuminate\Http\Request;

class OpportunityApplications extends Controller
{
    //

    public function __construct()
    {
        $this->middleware('auth');
    }
    public function applyForTraining(Request $request){
        $this->validate($request,[
            'training_opportunity_id' => 'required|integer',
            'person_id' => 'required|integer',
        ]);

        $trainigOpportunity = TrainingOpportunity::findOrFail($request->input('training_opportunity_id'));
        $person = Person::findOrFail($request->input('person_id'));

        $trainignApplication = new PersonTrainingApplication();

        $trainignApplication->apply($trainigOpportunity,$person);

        return response()->json([
            'success' => true,
            'message' => 'Applied Successfully'
        ]);
    }
    public function applyForJob(Request $request){
        $this->validate($request,[
            'job_opportunity_id' => 'required|integer',
            'person_id' => 'required|integer',
        ]);

        $jobOpportunity = JobOpportunity::findOrFail($request->input('job_opportunity_id'));
        $person = Person::findOrFail($request->input('person_id'));

        $jobApplication = new PersonJobApplication();

        $jobApplication->apply($jobOpportunity,$person);

        return response()->json([
            'success' => true,
            'message' => 'Applied Successfully'
        ]);
    }

    public function rejectTrainingApplication(Request $request){
        $this->validate($request,[
            'person_training_application_id' => 'required|integer',
        ]);

        $trainingApplication = PersonTrainingApplication::findOrFail($request->input('person_training_application_id'));

        $trainingApplication->delete();
        return response()->json([
            'success' => true,
            'message' => 'Rejected Successfully'
        ]);
    }

    public function rejectJobApplication(Request $request){
        $this->validate($request,[
            'person_job_application_id' => 'required|integer',
        ]);

        $jobApplication = PersonJobApplication::findOrFail($request->input('person_job_application_id'));

        $jobApplication->delete();
        return response()->json([
            'success' => true,
            'message' => 'Rejected Successfully'
        ]);
    }

    public function acceptTrainingApplication(Request $request){
        $this->validate($request,[
            'person_training_application_id' => 'required|integer',
        ]);


        $trainingApplication = PersonTrainingApplication::findOrFail($request->input('person_training_application_id'));

        $trainingOpportunity = $trainingApplication->opportunity()->first();

        $company = $trainingOpportunity->company()->first();

        $person = $trainingApplication->person()->first();

        $trainig = new Training();

        $trainig->add($company,$person);
        $trainingApplication->delete();

        return response()->json([
            'success' => true,
            'message' => 'Accepted Successfully'
        ]);
    }

    public function acceptJobApplication(Request $request){
        $this->validate($request,[
            'person_job_application_id' => 'required|integer',
        ]);


        $jobApplication = PersonJobApplication::findOrFail($request->input('person_job_application_id'));

        $jobOpportunity = $jobApplication->opportunity()->first();

        $company = $jobOpportunity->company()->first();

        $person = $jobApplication->person()->first();

        $employment = new Employment();

        $employment->add($company,$person);
        $jobApplication->delete();

        return response()->json([
            'success' => true,
            'message' => 'Accepted Successfully'
        ]);
    }

    public function getJobApplicationsByOpportunityId($id){

        $jobOpportunity = JobOpportunity::findOrFail($id);

        if($jobOpportunity->company_id != auth()->user()->company()->first()->id){
            throw new UnauthorizedException('Action Unauthorized');
        }

        $applications = PersonJobApplication::where('job_opportunity_id','=',$id)
            ->join('people','person_id','=','people.id')
            ->join('users','people.user_id','=','users.id')
            ->get(['person_job_applications.id as application_id','people.id as person_id',
                'users.id as user_id','users.name','users.email','users.mobile','people.photo']);

        return response()->json([
            'success' => true,
            'data' => $applications
        ]);
    }

    public function getTrainingApplicationsByOpportunityId($id){

        $trainingOpportunity = TrainingOpportunity::findOrFail($id);

        if($trainingOpportunity->company_id != auth()->user()->company()->first()->id){
            throw new UnauthorizedException('Action Unauthorized');
        }

        $applications = PersonTrainingApplication::where('training_opportunity_id','=',$id)
            ->join('people','person_id','=','people.id')
            ->join('users','people.user_id','=','users.id')
            ->get(['person_training_applications.id as application_id','people.id as person_id',
                'users.id as user_id','users.name','users.email','users.mobile','people.photo']);

        return response()->json([
            'success' => true,
            'data' => $applications
        ]);
    }
}
