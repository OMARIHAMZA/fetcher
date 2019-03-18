<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Branch extends Model
{
    protected $fillable = ['address'];
    public function company(){
        return $this->belongsTo('App\Company','company_id');
    }
}
