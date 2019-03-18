<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

abstract class Opportunity extends Model
{
    public function company(){
        return $this->belongsTo('App\Company','company_id');
    }
    public function category(){
        return $this->belongsTo('App\Category','category_id');
    }

    public function add(Company $company,Category $category){
        $this->company()->associate($company);
        $this->category()->associate($category);
        $this->save();
    }

    abstract public function getByCategory(Category $category);

    abstract public function getByCompany(Company $company);
}
