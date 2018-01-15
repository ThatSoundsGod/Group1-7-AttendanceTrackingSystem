
Skip to content
This repository

    Pull requests
    Issues
    Marketplace
    Explore

    @ThugLifeChief

291
1,280

    2,025

buckyroberts/Source-Code-from-Tutorials
Code
Issues 16
Pull requests 22
Projects 0
Wiki
Insights
Source-Code-from-Tutorials/Bootstrap/014_loginModal.html
12b9399 on 21 Dec 2015
@buckyroberts buckyroberts Added source code.
54 lines (44 sloc) 1.81 KB
<!DOCTYPE html>
<html lang="en">
<head>
    <title>thenewboston</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

    <h3>Log In Demo</h3>

    <!-- data-toggle lets you display modal without any JavaScript -->
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#popUpWindow">Open Modal</button>

    <div class="modal fade" id="popUpWindow">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- header -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3 class="modal-title">Log In</h3>
                </div>

                <!-- body (form) -->
                <div class="modal-body">
                    <form role="form">
                        <div class="form-group">
                            <input type="email" class="form-control" placeholder="Email">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="Password">
                        </div>
                    </form>
                </div>

                <!-- button -->
                <div class="modal-footer">
                    <button class="btn btn-primary btn-block">Submit</button>
                </div>

            </div>
        </div>
    </div>

</div>

</body>
</html>

    © 2017 GitHub, Inc.
    Terms
    Privacy
    Security
    Status
    Help

    Contact GitHub
    API
    Training
    Shop
    Blog
    About

