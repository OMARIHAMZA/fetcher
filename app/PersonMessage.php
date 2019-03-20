<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class PersonMessage extends Model
{
    //
    protected $fillable = ['message'];
    public function company(){
        return $this->belongsTo('App\Company','company_id');
    }
    public function person(){
        return $this->belongsTo('App\Person','person_id');
    }

    public function add(Person $person,Company $company){
        $this->person()->associate($person);
        $this->company()->associate($company);
        $this->save();
    }


}
