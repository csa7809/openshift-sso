<html>
<head>
  <#include "include/head.ftl" />
</head>
<body>

	<#include "include/body_top.ftl">
    <div class="container">	
	<br> <!-- don't forget new line --> 
	<div class="row "> 
		<!-- first part error message --> 
		<#if (param.error)?? >
		<div class="row col-sm-6" > 
			<div class="alert alert-danger alert-dismissible ng-hide" data-ng-show="isError" role="alert" name="errorMessage"> 
				<strong translate="">Invalid credentials, please check your username and password</strong> 
			</div> 
		</div> 
		</#if>
	</div>
	<div class="row">
		<form class="col-sm-6 form-horizontal ng-pristine ng-valid-email ng-invalid ng-invalid-required" role="form" id="myForm" action="login" method="post"> 
			<legend translate="">Please sign in</legend> 
			<div class="form-group"> 
				<label for="inputEmail" class="col-sm-3 control-label"><translate>username</translate> <span class="star">*</span></label> 
				<div class="col-sm-6"> <input placeholder="email@orange.com" class="form-control ng-pristine ng-valid-email ng-invalid ng-invalid-required ng-touched" data-ng-model="user.username" name="username" required="" style="" type="text"> </div>
			</div> 
			<div class="form-group"> 
				<label for="inputPassword" class="col-sm-3 control-label"><translate>password</translate> <span class="star">*</span></label> 
				<div class="col-sm-6"> <input placeholder="your_password" class="form-control ng-pristine ng-invalid ng-invalid-required ng-touched" data-ng-model="user.password" name="password" required="" style="" type="password"> </div> </div> <div class="form-group"> <label for="blank" class="col-sm-2 control-label"></label> <div class="col-sm-9 col-lg-offset-"> <a data-ng-href="#/forgot-password" translate="" href="#/forgot-password">forgot your password?</a> </div> 
			</div> 
			<input type="hidden" id="csrf_token" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="form-group" align="right">
			<button type="submit" class="btn btn-primary ">Submit</button>
			</div>
		</form>
		
		</div> <!-- row -->
	</div><!-- container -->
	<#include "include/body_footer.ftl">
 </body>
 <#include "include/bottom.ftl">
 
</html>