<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
class Person extends Model
{
    //
    public function user(){
        return $this->belongsTo('App\User','user_id');
    }

    public function certificates(){
        return $this->hasMany('App\Certificate');
    }

    public function works(){
        return $this->hasMany('App\Work');
    }

    public function employments(){
        return $this->hasMany('App\Employment');
    }
    public function trainings(){
        return $this->hasMany('App\Training');
    }
    public function companyEvaluations(){
        return $this->hasMany('App\CompanyEvaluation');
    }
    public function personEvaluations(){
        return $this->hasMany('App\PersonEvaluation');
    }

    public function personJobApplication(){
        return $this->hasMany('App\PersonJobApplication');
    }

    public function personTrainingApplication(){
        return $this->hasMany('App\PersonTrainingApplication');
    }
}
