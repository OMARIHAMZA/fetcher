<?php

namespace App\Http\Controllers;

use App\Certificate;
use App\Employment;
use App\Person;
use App\PersonMessage;
use App\Training;
use App\Work;
use Illuminate\Http\Request;
use Illuminate\Validation\UnauthorizedException;

class People extends Controller
{

    public function __construct()
    {
        $this->middleware('auth',['except' => ['info']]);
    }

    public function currentPerson(){
        $this->authorize('isPerson',Person::class);
        $person = auth()->user()->person()->first();

        return response()->json([
            'success'=>true,
            'data'=>$person
        ]);
    }

    public function edit($id,Request $request){
        $this->validate($request,[
           'address' => 'required',
            'field_of_work' => 'required'
        ]);

        $person = Person::findOrFail($id);

        $this->authorize('update',$person);


        if($request->hasFile('photo')){
            $path = $request->file('photo')->store('PeoplePhotos');
            $person->photo = $path;
        }
        if($request->hasFile('id_photo')){
            $id_photo = $request->file('id_photo')->store('PeopleIds');
            $person->id_photo = $id_photo;
        }
        if($request->hasFile('cv')){
            $cv = $request->file('cv')->store('PeopleCvs');
            $person->cv = $cv;
        }

        $person->address = $request->input('address');
        $person->field_of_work = $request->input('field_of_work');

        $person->save();

        return response()->json([
            'success'=>true,
            'message'=>"Person Edited Successfully!"
        ]);
    }

    public function addCertificate(Request $request){
        $this->validate($request,[
            'person_id' => 'required|integer',
            'name' => 'required',
            'description' => 'required'
        ]);

        $person = Person::findOrFail($request->input('person_id'));

        $this->authorize('update',$person);

        $attr = $request->only('name','description');

        $certificate = new Certificate($attr);

        $certificate->person()->associate($person);

        $certificate->save();

        return response()->json([
            'success'=>true,
            'message'=>"Certificate Added Successfully!"
        ]);

    }

    public function deleteCertificate(Request $request){
        $this->validate($request,[
            'certificate_id' => 'required|integer'
        ]);

        $certificate = Certificate::findOrFail($request->input('certificate_id'));

        if($certificate->person_id != auth()->user()->person()->first()->id){
            throw new UnauthorizedException('Action Unauthorized');
        }
        $certificate->delete();
        return response()->json([
            'success'=>true,
            'message'=>"Certificate deleted Successfully!"
        ]);

    }

    public function addWork(Request $request){
        $this->validate($request,[
            'person_id' => 'required|integer',
            'name' => 'required',
            'description' => 'required',
            'start' => 'required|date',
            'end' => 'date'
        ]);

        $person = Person::findOrFail($request->input('person_id'));

        $this->authorize('update',$person);

        $attr = $request->only('name','description','start','end');

        $work = new Work($attr);

        $work->person()->associate($person);

        $work->save();

        return response()->json([
            'success'=>true,
            'message'=>"Work Added Successfully!"
        ]);
    }

    public function deleteWork(Request $request){
        $this->validate($request,[
            'work_id' => 'required|integer'
        ]);

        $work = Work::findOrFail($request->input('work_id'));

        if($work->person_id != auth()->user()->person()->first()->id){
            throw new UnauthorizedException('Action Unauthorized');
        }
        $work->delete();
        return response()->json([
            'success'=>true,
            'message'=>"Work deleted Successfully!"
        ]);
    }

    public function getMessages($person_id){

        $person = Person::findOrFail($person_id);

        $messages = PersonMessage::where('person_id','=',$person_id)
            ->join('companies','company_id','=','companies.id')->get(
                ['person_messages.id as message_id','companies.id as id',
                    'person_messages.message','companies.name','official_email','website','main_address']
            );

        foreach ($messages as $message){
            $message->delete();

            $fordel = PersonMessage::find($message->message_id);

            $fordel->delete();
        }

        return response()->json([
            'success'=>true,
            'data' => $messages
        ]);

    }

    public function getTrainers($person_id){
        $person = Person::findOrFail($person_id);

        $this->authorize('update',$person);

        $training = new Training();

        $data = $training->getByPerson($person);

        return response()->json([
            'success'=>true,
            'data' =>$data
        ]);
    }

    public function getEmployers($person_id){
        $person = Person::findOrFail($person_id);

        $this->authorize('update',$person);

        $employment = new Employment();

        $data = $employment->getByPerson($person);

        return response()->json([
            'success'=>true,
            'data' =>$data
        ]);
    }

    public function info($id){
        $person = $person = Person::findOrFail($id);
        $certificates = $person->certificates()->get();
        $works = $person->works()->get();

        return response()->json([
            'success'=>true,
            'person' => $person,
            'works' => $works,
            'certificates' => $certificates
        ]);
    }
}
