<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class TrainingOpportunity extends Opportunity
{
    protected $fillable = ['title','duration','start','requirements','paid','number_of_trainees','place','subject'];

    public function getByCategory(Category $category)
    {
        return $category->trainingOpportunities()->join('companies', 'company_id', '=', 'companies.id')->get();
    }

    public function getByCompany(Company $company)
    {
        return $company->trainingOpportunities()->join('categories', 'category_id', '=', 'categories.id')->get();
    }
}
