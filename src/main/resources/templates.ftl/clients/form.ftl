<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Client edit/add</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>
    <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script>
        $(function() {
            $('.glyphicon-question-sign').tooltip();
        });
    </script>
</head>
<body>
<div class="container">
	<#if (clientId)?? >
    <h1 class="page-header">Edit client <span> ${clientId}</span></h1>
    <#else>
    <h1 class="page-header">Add client</h1>
    </#if>
    <div class="row">
        <div class="col-xs-12 col-md-12">
        	<#if (clientId)?? >
            <div class="alert alert-warning">Editing a client will delete all user approvals and active tokens.</div>
            </#if>
            <form action="/clients/edit" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <!-- signalize new client -->
                <#if (clientId)??>
                <#else>
                <input type="hidden" name="newClient" value="true"/>
				</#if>
                <div class="col-md-4">
                    <div class="form-group">
                        <label for="clientId">Client ID</label>
                        <input class="form-control" id="clientId" type="text" name="clientId" value="${clientDetails.clientId!""}"/>
                    </div>
                    <div class="form-group">
                        <label for="clientSecret">Client Secret
                            <span data-toggle="tooltip" data-placement="bottom" class="glyphicon glyphicon-question-sign"
                                  title="Used for the grants client_secret, authorization_code, refresh_token. Will not be updated if left empty."></span>
                        </label>
                        <input class="form-control" id="clientSecret" type="password" name="clientSecret"/>
                    </div>
                    <div class="form-group">
                        <label for="registeredRedirectUri">Registered redirect URIs
                            <span data-toggle="tooltip" data-placement="bottom" class="glyphicon glyphicon-question-sign"
                                  title="Only these URIs can be used as redirect uris in an authorize request. Important so no other malicious client can get access to tokens by fooling the user! Used for implicit, authorization_code and refresh_token grants. Comma separated list."></span>
                        </label>
                        <input class="form-control" id="registeredRedirectUri" name="registeredRedirectUri" type="text"
                               value="${(clientDetails.registeredRedirectUri?join(','))!""}"/>
                    </div>
                    <div class="form-group">
                        <label for="accessTokenValiditySeconds">Access token validity
                            <span data-toggle="tooltip" data-placement="bottom" class="glyphicon glyphicon-question-sign"
                                  title="The validity of an access token in seconds."></span>
                        </label>
                        <input class="form-control" id="accessTokenValiditySeconds" name="accessTokenValiditySeconds" type="text"
                               value="${ (clientDetails.accessTokenValiditySeconds?c)!"" }"/>
                    </div>
                    <div class="form-group">
                        <label for="refreshTokenValiditySeconds">Refresh token validity
                            <span data-toggle="tooltip" data-placement="bottom" class="glyphicon glyphicon-question-sign"
                                  title="The validity of a refresh token in seconds."></span>
                        </label>
                        <input class="form-control" id="refreshTokenValiditySeconds" name="refreshTokenValiditySeconds" type="text"
                               value="${(clientDetails.refreshTokenValiditySeconds?c)!""}"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <a href="/" class="btn ">Back</a>
                    
                </div>

                <div class="col-xs-12 col-md-3">
                    <div class="form-group">
                        <label for="resourceIds">Resource IDs
                            <span data-toggle="tooltip" data-placement="bottom" class="glyphicon glyphicon-question-sign"
                                  title="The resources this client can access, comma separated list."></span>
                        </label>
                        <input class="form-control" id="resourceIds" type="text" name="resourceIds" value="${clientDetails.resourceIds?join(',')}"/>
                    </div>
                    <div class="form-group">
                        <label for="scopes">Scopes
                            <span data-toggle="tooltip" data-placement="bottom" class="glyphicon glyphicon-question-sign"
                                  title="All scopes this client can access. If it is a client with authorization (implicit, authorization_code grant) the user can choose the scopes separately. Comma separated list."></span>
                        </label>
                        <input class="form-control" id="scopes" type="text" name="scope" value="${clientDetails.scope?join(",")}"/>
                    </div>
                    <div class="form-group">
                        <label for="authorities">Authorities
                            <span data-toggle="tooltip" data-placement="bottom" class="glyphicon glyphicon-question-sign"
                                  title="The authorities the client has, important for the client_secret grant. Comma separated list."></span>
                        </label>
                        <input class="form-control" id="authorities" type="text" name="authorities" value="${clientDetails.authorities?join( ',')}"/>
                    </div>
                </div>

                <div class="col-xs-12 col-md-5">
                    <label>Grant Types
                    <span data-toggle="tooltip" data-placement="bottom" class="glyphicon glyphicon-question-sign"
                          title="If you choose none, they default to authorization_code and refresh_token."></span>
                    </label>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                    Description
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul>
                                    <li><b>client_credentials</b>

                                        <p>The client can get an access token without the user authorizing it from the /token endpoint using basic authentication with its id and
                                            secret.
                                            Only for trusted clients. See curl client example. The client secret must be set for this grant type. The admin can specify the roles
                                            the client will have on the resource
                                            servers.</p>

                                        <p>You could imagine a client script with an admin role but only read scope that runs on a trusted server of you and pulls some data
                                            periodically.</p>
                                    </li>
                                    <li><b>implicit</b>

                                        <p>The client gets a token directly after the user authorizes it. Clients using this should have a rather limited scope.</p>

                                        <p>The client uses the /oauth/authorize?client_id=$clientId&amp;response_type=token&amp;redirect_uri=$uri endpoint to get the code. After
                                            the user authorizes the
                                            client the oauth server will return a redirect to $uri with the token in the hash of the redirect location.</p>
                                    </li>
                                    <li><b>authorization_code</b>

                                        <p>The user authorizes the client the first time it wants to access resources. In exchange it gets a code to get tokens from the /token
                                            endpoint. Only for trusted clients. The client must have some storage to keep the token.</p>

                                        <p>The client uses the /oauth/authorize?client_id=$clientId&amp;response_type=code&amp;redirect_uri=$uri endpoint to get the code. After the
                                            user authorizes the client the oauth server will return a redirect to $uri with the code in the hash of the redirect location.</p>

                                        <p>The code can then be used at /oauth/token?code=$code&amp;grant_type=authorization_code to obtain a token with it. The client has
                                            to authenticate itself with basic authentication at this point (with its name and the client secret, even when the client_secret grant
                                            is not used!)</p>
                                    </li>
                                    <li><b>refresh_token</b></li>
                                    <li><b>password</b>

                                        <p>The client asks the user for his credentials and forwards them. Only for clients that you wrote yourself like an official app for a
                                            phone.</p>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="authorizedGrantTypes" value="client_credentials" ${clientDetails.authorizedGrantTypes?seq_contains('client_credentials')?string('checked','')}/>
                            client_credentials
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="authorizedGrantTypes" value="implicit" ${clientDetails.authorizedGrantTypes?seq_contains('implicit')?string('checked','')}/>
                            implicit
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="authorizedGrantTypes" value="authorization_code" ${clientDetails.authorizedGrantTypes?seq_contains('authorization_code')?string('checked','')}/>
                            authorization_code
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="authorizedGrantTypes" value="refresh_token" ${clientDetails.authorizedGrantTypes?seq_contains('refresh_token')?string('checked','')}/>
                            refresh_token
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="authorizedGrantTypes" value="password" ${clientDetails.authorizedGrantTypes?seq_contains('password')?string('checked','')}/>
                            password
                        </label>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>