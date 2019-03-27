<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::group([

    'middleware' => 'api',
    'prefix' => 'auth'

], function ($router) {

    Route::post('login', 'AuthController@login');
    Route::post('register', 'AuthController@register');
    Route::post('logout', 'AuthController@logout');
    Route::post('refresh', 'AuthController@refresh');
    Route::post('me', 'AuthController@me');

});

Route::group([

    'middleware' => 'api',
    'prefix' => 'companies'

], function ($router) {

    Route::get('currentCompany', 'Companies@currentCompany');
    Route::get('info/{id}', 'Companies@info');
    Route::get('getTrainees/{company_id}', 'Companies@getTrainees');
    Route::get('getEmployees/{company_id}', 'Companies@getEmployees');

    Route::post('update/{id}', 'Companies@update');
    Route::post('addBranch', 'Companies@addBranch');
    Route::post('addCompanyPhoto', 'Companies@addCompanyPhoto');

    Route::post('deleteBranch', 'Companies@deleteBranch');
    Route::post('deleteCompanyPhoto', 'Companies@deleteCompanyPhoto');
    Route::post('deleteProject', 'Companies@deleteProject');

});

Route::group([

    'middleware' => 'api',
    'prefix' => 'categories'

], function ($router) {

    Route::get('get', 'Categories@get');
    Route::post('add', 'Categories@add');

});

Route::group([

    'middleware' => 'api',
    'prefix' => 'projects'

], function ($router) {

    Route::post('add', 'Projects@add');

});

Route::group([

    'middleware' => 'api',
    'prefix' => 'trainingOpportunities'

], function ($router) {

    Route::post('add', 'TrainingOpportunities@add');
    Route::get('getByCategory/{category_id}','TrainingOpportunities@getByCategory');
    Route::get('getByCompany/{company_id}','TrainingOpportunities@getByCompany');
    Route::post('delete', 'TrainingOpportunities@delete');
});

Route::group([

    'middleware' => 'api',
    'prefix' => 'jobOpportunities'

], function ($router) {
    Route::post('add', 'JobOpportunities@add');
    Route::get('getByCategory/{category_id}', 'JobOpportunities@getByCategory');
    Route::get('getByCompany/{company_id}','JobOpportunities@getByCompany');
    Route::post('delete', 'JobOpportunities@delete');
});

Route::group([

    'middleware' => 'api',
    'prefix' => 'people'

], function ($router) {
    Route::get('currentPerson', 'People@currentPerson');
    Route::get('info/{id}', 'People@info');
    Route::get('getMessages/{person_id}', 'People@getMessages');
    Route::post('edit/{id}', 'People@edit');
    Route::post('addCertificate', 'People@addCertificate');
    Route::post('addWork', 'People@addWork');
    Route::post('deleteCertificate', 'People@deleteCertificate');
    Route::post('deleteWork', 'People@deleteWork');

    Route::get('getTrainers/{person_id}', 'People@getTrainers');
    Route::get('getEmployers/{person_id}', 'People@getEmployers');
});

Route::group([

    'middleware' => 'api',
    'prefix' => 'opportunityApplications'

], function ($router) {

    // Get
    Route::get('getJobApplicationsByOpportunityId/{id}','OpportunityApplications@getJobApplicationsByOpportunityId');
    Route::get('getTrainingApplicationsByOpportunityId/{id}','OpportunityApplications@getTrainingApplicationsByOpportunityId');
    // Apply
    Route::post('applyForTraining', 'OpportunityApplications@applyForTraining');
    Route::post('applyForJob', 'OpportunityApplications@applyForJob');

    // Reject
    Route::post('rejectTrainingApplication', 'OpportunityApplications@rejectTrainingApplication');
    Route::post('rejectJobApplication', 'OpportunityApplications@rejectJobApplication');

    // Accept
    Route::post('acceptTrainingApplication', 'OpportunityApplications@acceptTrainingApplication');
    Route::post('acceptJobApplication', 'OpportunityApplications@acceptJobApplication');
});

Route::group([

    'middleware' => 'api',
    'prefix' => 'evaluations'

], function ($router) {

    // Get
    Route::get('getPersonEvaluations/{id}','Evaluations@getPersonEvaluations');
    Route::get('getCompanyEvaluations/{id}','Evaluations@getCompanyEvaluations');

    Route::post('evaluateCompany', 'Evaluations@evaluateCompany');
    Route::post('evaluatePerson', 'Evaluations@evaluatePerson');
});


Route::group([

    'middleware' => 'api',
    'prefix' => 'admin'

], function ($router) {

    // Get
    Route::get('companies','Admin@companies');
    Route::get('people','Admin@people');


    Route::post('addUser', 'Admin@addUser');
    Route::post('deleteCompany', 'Admin@deleteCompany');
    Route::post('deletePerson', 'Admin@deletePerson');

});