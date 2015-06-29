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
                <script src="${cm.config.corejs.savedv}"></script>
		<g:layoutHead/>
	</head>
	<body>
           <div class="panel panel-primary" style="width: 500px; margin-left: 50px; margin-top: 50px;"> 
               <div class="panel-heading">Options</div>
               <div class="panel-body">
                    <div class="input-group">
                        <input type="text" class="form-control" id="pinnacleLogin" placeholder="Login" value = "${data.pinnacleLogin}" aria-describedby="basic-addon2">
                        <span class="input-group-addon" >Login</span>
                        </div><br>
                    <div class="input-group">
                        <input type="text" class="form-control" id="pinnaclePassword" placeholder="Login" value = "${data.pinnaclePassword}" aria-describedby="basic-addon2">
                         <span class="input-group-addon" >Password</span>
                    </div><br>
                    <div class="input-group">
                        <input type="text" class="form-control"  id="pinnacleApiUrl" placeholder="Login" value = "${data.pinnacleApiUrl}" aria-describedby="basic-addon2">
                        <span class="input-group-addon" >API URL</span>
                    </div><br>
                    <div class="input-group">
                        <input type="text" class="form-control" id="pinnacleSportId" placeholder="Login" value = "${data.pinnacleSportId}" aria-describedby="basic-addon2">
                        <span class="input-group-addon" >Sport id</span>
                    </div><br>
                    <div class="input-group">
                        <input type="text" class="form-control" id="pinnacleLeagueIds" placeholder="Login" value = "${data.pinnacleLeagueIds}" aria-describedby="basic-addon2">
                        <span class="input-group-addon" >Leagues ids</span>
                    </div><br>
                    <div class="input-group">
                        <input type="text" class="form-control" id="parserTurn" placeholder="Login" value = "${data.parserTurn}" aria-describedby="basic-addon2">
                        <span class="input-group-addon" >Parser turn</span>
                    </div><br>
                    <div class="input-group">
                        <input type="text" class="form-control"  id="parserTerm" placeholder="Login" value = "${data.parserTerm}" aria-describedby="basic-addon2">
                        <span class="input-group-addon" >Parser term / sec</span>
                    </div><br>
                    <div class="input-group">
                        <input type="text" class="form-control" id="stakeValue" placeholder="Login" value = "${data.stakeValue}" aria-describedby="basic-addon2">
                        <span class="input-group-addon" >Stake value</span>
                    </div><br>
                    <div class="input-group">
                        <input type="text" class="form-control"  id="minMaxStakeValue" placeholder="Login" value = "${data.minMaxStakeValue}" aria-describedby="basic-addon2">
                        <span class="input-group-addon" >Min max stake value</span>
                    </div><br>
                    <button type="button" class="btn btn-primary"  id="btnSaveDV">Save</button>
               </div>


                
           </div> 
	</body>
</html>
