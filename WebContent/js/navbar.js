/**
 * SCRIPT FOR SIDE BROWSE NAVIGATION BAR
 */


$(document).ready(function(){
  
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