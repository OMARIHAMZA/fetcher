<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Category extends Model
{
    //
    protected $fillable = ['name','photo'];
    public function trainingOpportunities(){
        return $this->hasMany('App\TrainingOpportunity');
    }
    public function jobOpportunities(){
        return $this->hasMany('App\JobOpportunity');
    }


}
