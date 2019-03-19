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

}
