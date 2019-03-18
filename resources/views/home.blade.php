@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">Dashboard</div>

                <div class="panel-body">
                    @if (session('status'))
                        <div class="alert alert-success">
                            {{ session('status') }}
                        </div>
                    @endif
                    You are logged in!
                </div>
            </div>
            @if (Auth::user()->isCompany())
                <div class="panel panel-default">
                    <div class="panel-heading">Company Info</div>

                    <div class="panel-body">
                        <form method="post" action="{{route('update_company',['id' => $company_id])}}">
                            {{csrf_field()}}
                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" class="form-control" name="name" id="name" value="{{old('name') ? old('name') : $name}}">
                                <small class="text-danger">{{$errors->first('name')}}</small>
                            </div>
                            <div class="form-group">
                                <label for="official_email">Official Email</label>
                                <input type="email" class="form-control" name="official_email" id="official_email" value="{{old('official_email') ? old('official_email') : $official_email}}">
                                <small class="text-danger">{{$errors->first('official_email')}}</small>
                            </div>
                            <div class="form-group">
                                <label for="website">Website</label>
                                <input type="url" class="form-control" id="website" name="website" value="{{old('website') ? old('website') : $website}}">
                                <small class="text-danger">{{$errors->first('website')}}</small>
                            </div>
                            <div class="form-group">
                                <label for="main_address">Main Address</label>
                                <textarea class="form-control" id="main_address" name="main_address" rows="3">{{old('main_address') ? old('main_address') : $main_address}}</textarea>
                                <small class="text-danger">{{$errors->first('main_address')}}</small>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            @endif
        </div>
    </div>
</div>
@endsection
