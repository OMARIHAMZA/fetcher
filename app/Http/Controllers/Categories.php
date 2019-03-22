<?php

namespace App\Http\Controllers;

use App\Category;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Storage;

class Categories extends Controller
{
    //
    public function get(){
        $category = Category::all();
        return response()->json([
            'success'=>true,
            'data'=> $category
        ]);

    }
    public function add(Request $request){
        $this->validate($request,[
            'photo' => 'required',
            'name' => 'required',
        ]);


        $path = $request->file('photo')->store('CategoriesPhotos');

        $category = new Category();
        $category->photo = $path;
        $category->name = $request->input('name');

        $category->save();

        return response()->json([
            'success'=>true,
            'message'=>"Category Added Successfully!",
            'photo'=>Storage::url($path)
        ]);
    }
}
