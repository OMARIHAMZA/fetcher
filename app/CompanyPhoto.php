<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class CompanyPhoto extends Model
{
    protected $fillable = ['photo'];
    public function company(){
        return $this->belongsTo('App\Company','company_id');
    }
}
