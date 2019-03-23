<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class TrainingOpportunity extends Opportunity
{
    protected $fillable = ['title','duration','start','requirements','paid','number_of_trainees','place','subject'];

    public function getByCategory(Category $category)
    {
        return $category->trainingOpportunities()->join('companies', 'company_id', '=', 'companies.id')->get(
            array_merge(['training_opportunities.id as training_opportunity_id',
                'companies.id as company_id','companies.name as company_name'], $this->fillable)
        );
    }

    public function getByCompany(Company $company)
    {
        return $company->trainingOpportunities()->join('categories', 'category_id', '=', 'categories.id')->get(
            array_merge(['training_opportunities.id as training_opportunity_id',
                'categories.id as category_id','categories.name as category_name'], $this->fillable)
        );
    }
}
