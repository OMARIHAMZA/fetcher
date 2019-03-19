<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class PersonJobApplication extends OpportunityApplication
{

    /**
     * @return BelongsTo
     */
    public function opportunity()
    {
        return $this->belongsTo('App\JobOpportunity','job_opportunity_id');
    }
}
