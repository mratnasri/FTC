    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
    <!DOCTYPE HTML>  
    <html>  
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/style.css">
    
    <head>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
    <title><tiles:insertAttribute name="title" ignore="true" /></title>  
    </head>  
    <body>  
            <div><tiles:insertAttribute name="header" /></div>  
            <div><tiles:insertAttribute name="hNavbar" /></div> 
            <div><tiles:insertAttribute name="hNavWelcome" /></div> 
            <div><tiles:insertAttribute name="menu" /></div>
            <div><tiles:insertAttribute name="body" /></div>
           <div><tiles:insertAttribute name="footer" /></div>
      
    </body>  
    </html>  