<?php

namespace App\Http\Controllers;
use App\Company;
use App\Category;
use App\JobOpportunity;
use App\Opportunity;
use Illuminate\Http\Request;

abstract class Opportunities extends Controller
{

    protected $opportunity;
    public function __construct()
    {
        $this->middleware('auth');
        $this->opportunity = new JobOpportunity();
    }

    abstract public function add(Request $request);

    public function getByCategory($category_id){

        $category = Category::findOrFail($category_id);

        $data = $this->opportunity->getByCategory($category);

        return response()->json([
            'success'=>true,
            'data'=> $data
        ]);
    }

    public function getByCompany($company_id){

        $company = Company::findOrFail($company_id);

        $data = $this->opportunity->getByCompany($company);

        return response()->json([
            'success'=>true,
            'data'=> $data
        ]);
    }

    abstract public function delete(Request $request);
}
