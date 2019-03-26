<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Training extends Model
{
    //
    protected $table = 'trainings';

    public function person(){
        return $this->belongsTo('App\Person','person_id');
    }
    public function company(){
        return $this->belongsTo('App\Company','company_id');
    }

    public function add(Company $company,Person $person){
        $this->person()->associate($person);
        $this->company()->associate($company);
        $this->save();
    }

    public function getByCompany(Company $company){
        return $this->join('people','people.id','=','person_id')
            ->join('users','users.id','=','people.user_id')
            ->where('trainings.company_id','=',$company->id)
            ->get(['trainings.id as training_id','trainings.company_id','person_id',
                'users.name','users.mobile','users.email',
                'photo','id_photo','cv','address','field_of_work']);
    }

}
