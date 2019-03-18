<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Company extends Model
{
    //
    public function user(){
        return $this->belongsTo('App\User','user_id');
    }

    public function branches(){
        return $this->hasMany('App\Branch');
    }
    public function companyPhotos(){
        return $this->hasMany('App\CompanyPhoto');
    }
    public function projects(){
        return $this->hasMany('App\Project');
    }

    public function trainingOpportunities(){
        return $this->hasMany('App\TrainingOpportunity');
    }
    public function jobOpportunities(){
        return $this->hasMany('App\JobOpportunity');
    }

    public function addCompanyPhoto(CompanyPhoto $companyPhoto){
        $this->companyPhotos()->save($companyPhoto);
    }

    public function addBranch(Branch $branch){
        $this->branches()->save($branch);
    }

}
