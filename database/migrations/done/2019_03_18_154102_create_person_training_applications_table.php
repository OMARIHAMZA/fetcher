<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePersonTrainingApplicationsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('person_training_applications', function (Blueprint $table) {
            $table->increments('id');
            $table->unsignedInteger('training_opportunity_id');
            $table->unsignedInteger('person_id');

            $table->foreign('training_opportunity_id')->references('id')->on('training_opportunities');
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
        Schema::dropIfExists('person_training_applications');
    }
}
