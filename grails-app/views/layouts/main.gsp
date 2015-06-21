<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="grails.util.Holders as cm" %>

<html lang="en"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="PinnaclesportsRobot"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" type="text/css" href="${cm.config.bootstrap.mincss}" media="screen"/>
                <link rel="stylesheet" type="text/css" href="${cm.config.styles.themecss}" media="screen"/>                
                <script src="${cm.config.bootstrap.minjs}"></script>
                <script src="${cm.config.bootstrap.jqueryminjs}"></script>  		
		<g:layoutHead/>
	</head>
        <body>
            <div class="site-wrapper">

              <div class="site-wrapper-inner">

                <div class="cover-container">

                  <div class="masthead clearfix">
                    <div class="inner">
                      
                      <h3 class="masthead-brand">
                          <img style="width: 50px" src="images/robot.png">
                          PinnaclesportsRobot</h3>
                      <nav>
                        <ul class="nav masthead-nav">
                          <li class="active"><a href="#">Home</a></li>
                          <li><a href="dv">Options</a></li>
                          <li><a href="parserlogs">Parser logs</a></li>
                        </ul>
                      </nav>
                    </div>
                  </div>

                  <!--<div class="inner cover">
                    <h1 class="cover-heading">Cover your page.</h1>
                    <p class="lead">Cover is a one-page template for building simple and beautiful home pages. Download, edit the text, and add your own fullscreen background photo to make it your own.</p>
                    <p class="lead">
                      <a href="#" class="btn btn-lg btn-default">Learn more</a>
                    </p>
                  </div>
                   -->
                  <div class="mastfoot">
                    <div class="inner">
                      Developed by <a href="https://github.com/redfastfox">@evgeniysafronov</a>.</p>
                    </div>
                  </div>

                </div>

              </div>

            </div>
        </body>
	
</html>
