<?php

namespace App\Http\Controllers;

use App\Certificate;
use App\Person;
use App\Work;
use Illuminate\Http\Request;

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
           'photo' => 'required',
           'address' => 'required',
        ]);

        $person = Person::findOrFail($id);

        $this->authorize('update',$person);

        $path = $request->file('photo')->store('PeoplePhotos');

        $person->photo = $path;
        $person->address = $request->input('address');

        $person->save();

        return response()->json([
            'success'=>true,
            'message'=>"Person Edited Successfully!",
            'photo'=>$path
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
