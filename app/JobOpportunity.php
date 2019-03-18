<?php

namespace App;

use App\Http\Controllers\Opportunities;
use Illuminate\Database\Eloquent\Model;

class JobOpportunity extends Opportunity
{
    protected $fillable = ['title','duration','start','end_of_submission','requirements','number_of_employees','salary','place','days'];

    public function getByCategory(Category $category)
    {
        return $category->jobOpportunities()->join('companies', 'company_id', '=', 'companies.id')->get();
    }

    public function getByCompany(Company $company)
    {
        return $company->jobOpportunities()->join('categories', 'category_id', '=', 'categories.id')->get();
    }
}
