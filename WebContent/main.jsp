<%@ include file="head.jsp" %> 

<body>

	<%@ include file="topNav.jsp" %> 

	
    <!-- Page Content -->
    <div class="container">

        <div class="row">

			<%@ include file="sideNav.jsp" %> 

            <%    
            if ((boolean)session.getAttribute("showAdvancedMenu")){
             //System.out.println("INSIDE SHOW ADVMEN"); 
             %>

                <%@ include file="advancedSearch.jsp" %>

            <% } %>

            <%@ include file="listMovie.jsp" %> 

        </div>

    </div>

    <div class="container">

		<%@ include file="footer.jsp" %> 

    </div>

</body>

<%@ include file="foot.jsp" %> 