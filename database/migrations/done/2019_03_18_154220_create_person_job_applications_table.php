<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePersonJobApplicationsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('person_job_applications', function (Blueprint $table) {
            $table->increments('id');
            $table->unsignedInteger('job_opportunity_id');
            $table->unsignedInteger('person_id');

            $table->foreign('job_opportunity_id')->references('id')->on('job_opportunities');
            $table->foreign('person_id')->references('id')->on('people');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('person_job_applications');
    }
}
