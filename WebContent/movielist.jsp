<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@
page import="sources.*, java.util.*"%> 
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>FabFlix</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            
<form class="navbar-form navbar-right" role="search">
  <div class="form-group">
    <input type="text" class="form-control" placeholder="Search">
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form>


       
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <div class="col-md-3">
                <p class="lead">FabFlix</p>

<!--Begin Side Navbar for Market Data Control -->
    <div class="well sidebar-nav" id = "market-data-nav" >

        <ul id="myTab" class="nav nav-list nav-stacked" >
              
            <li id = "arrow-test" class="nav-header" data-toggle="collapse" data-target="#test">Browse By Genre</li>
             
                <ul class="nav nav-list collapse" id="test">

                    <li id = "asr-link2"><a href="ListMovies?action=byGenre&genre=action">Action</a></li>
                    <li id = "asr-link3"><a href="ListMovies?action=byGenre&genre=adventure">Adventure</a></li>
                    <li id = "cgft-link"><a href="ListMovies?action=byGenre&genre=animation">Animation</a></li>
                
                </ul>
              
            <li class="nav-header" data-toggle="collapse" data-target="#test2">Browse By Title</li>
              
                <ul class= "nav nav-list collapse" id="test2">
                    
                    <li id = "it-link"><a href="ListMovies?action=byTitle&Letter=A">A</a></li>
                    <li id = "lapn-link"><a href="ListMovies?action=byTitle&Letter=B">B</a></li>
                    <li id = "lmp-link"><a href="ListMovies?action=byTitle&Letter=C">C</a></li>
                
                </ul>
           
        </ul>
          
    </div><!--/.well -->
         
          

          
   <!-- End Side Navbar -->
            </div>

            <div class="col-md-9">

                <div class="row">

<% ArrayList<Movie> movies =  (ArrayList<Movie>)session.getAttribute("moviesToShow"); 

	for (Movie m: movies){
%>
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="http://placehold.it/320x150" alt="">
                            <div class="caption">
                                <h4 class="pull-right">$24.99</h4>
                                <h4><a href="#"><%= m.title %></a>
                                </h4>
                                <p>Info</a>.</p>
                            </div>
                            <div class="ratings">
                                <p class="pull-right">Add To Cart</p>
                                <p>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                </p>
                            </div>
                        </div>
                    </div>

    <% } %>  

                </div>

            </div>

        </div>

    </div>
    <!-- /.container -->

    <div class="container">

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

<script> $(document).ready(function(){
  
  $(".trade-data").hide();
  $("#trade-data-nav").hide();
  $("#risk-data-nav").hide();
  
  var $currentTable;
 
  
  var tableList = [".asr", ".asr2", ".asr3", ".cgft", ".cgtt", ".dcp", ".dgp", ".gen", ".gc", ".gchi", ".gchr", ".gcrr", ".gcr", ".gcsu", ".gfr", ".ghr", ".gmm", ".grr", ".gr", ".grmt", ".gsu", ".gt", ".it", ".lapn", ".lmp", ".lmpc", ".ma", ".mpart", ".mprod", ".mst", ".mtype", ".meter", ".mc", ".mct", ".md", ".nr", ".por", ".rst", ".rtype", ".sr", ".th", ".uom", ".udc"];
   //Auto hide each table.
  
  for(var i = 0; i < tableList.length; i++)
        $(tableList[i]).hide();
   
  //funct to create new table based on event ID
 /* var newTable = function(event)
  {
    $($currentTable).hide();
    $currentTable = $(dict.event);
    $($currentTable).toggle("slow");
    
  }; */                                
  //Turn into newTable()
  $("#asr-link").click(function()
    {
      $($currentTable).hide();
      $currentTable = $(".asr");
      $($currentTable).toggle("slow");
    }); 
  
  $("#asr-link2").click(function()
    {
      $($currentTable).hide();
      $currentTable = $(".asr2");
      $($currentTable).toggle("slow");
      
    });
  
  $("#asr-link3").click(function()
    {
      $($currentTable).hide();
      $currentTable = $(".asr3");
      $($currentTable).toggle("slow");
      
    });
  $("#cgft-link").click(function()
    {
      $($currentTable).hide();
      $currentTable = $(".cgft");
      $($currentTable).toggle("slow");
      
    });
  $("#cgtt-link").click(function()
    {
      $($currentTable).hide();
      $currentTable = $(".cgtt");
      $($currentTable).toggle("slow");
      
    });
  
  $("#trade-data-dropdown").click(function()
    {
      $("#market-data-nav").hide();
      $("#risk-data-nav").hide();
      $("#trade-data-nav").show();
      
      $(document.getElementByID("top-left-title")).innerHTML ="Trade Data Control";
    });
  $("#market-data-dropdown").click(function()
    {
      $("#trade-data-nav").hide();
      $("#risk-data-nav").hide();
      $("#market-data-nav").show();
    });
  $("#risk-data-dropdown").click(function()
    {
      $("#market-data-nav").hide();
      $("#trade-data-nav").hide();
      $("#risk-data-nav").show();
    });
  
});

</script>
    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>