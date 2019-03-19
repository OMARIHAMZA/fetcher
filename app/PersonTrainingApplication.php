<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class PersonTrainingApplication extends OpportunityApplication
{
    //
    /**
     * @return BelongsTo
     */
    public function opportunity()
    {
        return $this->belongsTo('App\TrainingOpportunity','training_opportunity_id');
    }
}
