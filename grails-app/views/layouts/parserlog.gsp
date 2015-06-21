<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> 
<%@ page import="grails.util.Holders as cm" %>

<html lang="en"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
  		
                <link rel="stylesheet" type="text/css" href="${cm.config.bootstrap.mincss}" media="screen" />
                <script src="${cm.config.bootstrap.minjs}"></script>
                <script src="${cm.config.bootstrap.jqueryminjs}"></script>
		<g:layoutHead/>
	</head>
	<body>
           <div class="panel panel-primary" style="width: 500px; margin-left: 50px; margin-top: 50px;"> 
               <div class="panel-heading">Parser logs, only 20 last proccess</div>
               <div class="panel-body">
                    
               </div>


                
           </div> 
	</body>
</html>
