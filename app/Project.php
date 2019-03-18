<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Project extends Model
{
    protected $fillable = ['name','description'];
    public function company(){
        return $this->belongsTo('App\Company','company_id');
    }

    public function add(Company $company){
        $this->company()->associate($company);
        $this->save();
    }
}
