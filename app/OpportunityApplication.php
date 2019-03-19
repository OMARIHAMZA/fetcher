<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

abstract class OpportunityApplication extends Model
{
    //
    /**
     * @return BelongsTo
     */
    abstract public function opportunity();

    public function person(){
        return $this->belongsTo('App\Person','person_id');
    }

    public function apply(Opportunity $opportunity,Person $person){
        $this->person()->associate($person);
        $this->opportunity()->associate($opportunity);
        $this->save();
    }
}
