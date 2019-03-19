<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Work extends Model
{
    //

    protected $fillable = ['name','description','start','end'];

    public function person(){
        return $this->belongsTo('App\Person','person_id');
    }
}
