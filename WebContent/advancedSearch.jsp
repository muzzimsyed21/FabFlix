<div class="col-md-9">

     <div class="row">
       
   
<div id="searchtools">
  
  <center> <h3>Advanced Search Tools</h3> </center>

  <FORM ACTION="/FabFlix/ListMovies" METHOD="POST">

  <table border="0" align="center">
    
    <tr><td align="right">Title: </td><td><input type="text" name="title" class="input-textbox" placeholder="Enter Title"></td></tr>
    
    <tr><td align="right">Year: </td><td><input type="text" name="year" class="input-textbox" placeholder="Enter Year"></td></tr>

    <tr><td align="right">Director: </td><td><input type="text" name="director" class="input-textbox" placeholder="Enter Director"></td></tr>

    <tr><td align="right">Star: </td><td><input type="text" name="starFirst" class="input-textbox" placeholder="First Name"></td></tr>

    <tr><td align="right">Star: </td><td><input type="text" name="starLast" class="input-textbox" placeholder="Last Name"></td></tr>

    <input type="hidden" name="action" value="byAdvancedSearch">
    <!-- ADD CheckBox For Substring/ Fuzzy Search -->

	</table>
	<br>
	<center>
	
		<button type="submit" class="btn btn-default" value="ListMovies">Submit Query</button>

  	</center>

  	</FORM>
     </div>

   </div>

</div>

<style>
#searchtools {
	color: #FFF; 
    background-color: black;
    border: 1px solid black;
    border-radius: 8px;
    padding: 10px;
    width: 400px;
    margin: 0 auto;
    margin-bottom: 20px;
    font-size: 1.0em;
}

.input-textbox {
	color: #000; 
    width: 70%;
    padding: 7px;
    border: 1px solid black;
    font-family: Arial, Helvetica, sans-serif;
    font-size: 1.0em;
    font-weight: bold; 
    margin: 2px;
    border-radius: 5px; 
}
</style>