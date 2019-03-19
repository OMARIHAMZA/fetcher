<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class PersonEvaluation extends Model
{
    //
    public function company(){
        return $this->belongsTo('App\Company','company_id');
    }
    public function person(){
        return $this->belongsTo('App\Person','person_id');
    }

    public function evaluate(Company $company,Person $person,$rating){
        $this->company()->associate($company);
        $this->person()->associate($person);
        $this->rating = $rating;
        $this->save();
    }
}
