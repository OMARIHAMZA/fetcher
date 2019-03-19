<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Certificate extends Model
{
    //
    protected $fillable = ['name','description'];

    public function person(){
        return $this->belongsTo('App\Person','person_id');
    }


}
