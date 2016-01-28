            <div class="col-md-9">

                <div class="row">

<% 

ArrayList<Movie> movies =  (ArrayList<Movie>)session.getAttribute("moviesToShow"); 

if(movies!= null){
	
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

    <% } 
    
    
}%>  

                </div>

            </div>