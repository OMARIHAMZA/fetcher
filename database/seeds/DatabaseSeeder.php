<?php

use Illuminate\Database\Seeder;
use App\User;
use Illuminate\Support\Facades\Crypt;
class DatabaseSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        // $this->call(UsersTableSeeder::class);

        $category = new \App\Category(['name'=>'Business','photo'=>'https://image.flaticon.com/icons/svg/912/912316.svg']);
        $category->save();
        $category = new \App\Category(['name'=>'Services','photo'=>'https://image.flaticon.com/icons/svg/771/771601.svg']);
        $category->save();
        $category = new \App\Category(['name'=>'Medic','photo'=>'https://image.flaticon.com/icons/svg/1021/1021799.svg']);
        $category->save();
        $category = new \App\Category(['name'=>'Engineering','photo'=>'https://image.flaticon.com/icons/svg/942/942833.svg']);
        $category->save();
        $category = new \App\Category(['name'=>'Maintenance','photo'=>'https://image.flaticon.com/icons/svg/1098/1098420.svg']);
        $category->save();
        $category = new \App\Category(['name'=>'Office','photo'=>'https://image.flaticon.com/icons/svg/942/942803.svg']);
        $category->save();
        $category = new \App\Category(['name'=>'Security','photo'=>'https://image.flaticon.com/icons/svg/1022/1022382.svg']);
        $category->save();

    }
}
