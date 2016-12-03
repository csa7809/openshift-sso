<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>OAuth Server Index</title>
    <link rel="stylesheet" href="webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>
</head>
<body>
<div class="container">
    <h1 class="page-header">OAuth Server</h1>
    <div class="row">
        <div class="col-md-12">
            Logged in as: <b><span sec:authentication="name"></span></b><br/>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h2>Approvals</h2>

            <p>
                If you revoke the approval for one scope of a client all tokens for that client will be removed as well.
            </p>
            <table class="table table-bordered">
                <tr>
                    <th>Client</th>
                    <th>Scope</th>
                    <th>Expires at</th>
                    <th>Actions</th>
                </tr>
                <#list approvals as approval>
                <tr>
                    <td>${approval.clientId}</td>
                    <td>${approval.scope}</td>
                    <td>${approval.expiresAt}</td>
                    <td>
                        <form action="/approval/revoke" method="POST">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="userId" value="${approval.userId}"/>
                            <input type="hidden" name="clientId" value="${approval.clientId}"/>
                            <input type="hidden" name="scope" value="${approval.scope}"/>
                            <input type="hidden" name="expiresAt" value="${approval.expiresAt?string["yyyy-MM-dd HH:mm:ss"]}"/>
                            <input type="hidden" name="status" value="${approval.status}"/>
                            <input type="hidden" name="lastUpdatedAt" value="${approval.lastUpdatedAt?string['yyyy-MM-dd HH:mm:ss']}"/>
                            <button type="submit" class="btn btn-default btn-xs">
                                <span class="glyphicon glyphicon-trash"></span>
                            </button>
                        </form>
                    </td>
                </tr>
                </#list>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-10 col-md-offset-1" sec:authorize="hasRole('ROLE_OAUTH_ADMIN')">
            <h2>Clients</h2>
            <table class="table table-bordered">
                <tr>
                    <th>Client ID</th>
                    <th>Resource IDs</th>
                    <th>Scopes</th>
                    <th>Grant Types</th>
                    <th>Roles</th>
                    <th>Actions</th>
                </tr>
                <#list clientDetails as client>
                <tr >
                   	<td>${client.clientId}</td>
                    <td>${client.resourceIds?join("<br/>")}</td>
                    <td>${client.scope?join(", ")}</td>
                    <td>${client.authorizedGrantTypes?join("<br/>")}</td>
                    <td>${client.authorities?join("<br/>")}</td>
                    <td>
                        <a class="btn btn-default btn-xs" href="/clients/form?client=${client.clientId}">
                            <span class="glyphicon glyphicon-edit"></span>
                        </a>
                        <a class="btn btn-default btn-xs" href="/clients/${client.clientId}/delete">
                            <span class="glyphicon glyphicon-trash"></span>
                        </a>
                    </td>
                </tr>
                </#list>
                <tr>
                    <td colspan="6">
                        <a class="btn btn-default btn-xs pull-right" href="/clients/form">
                            <span class="glyphicon glyphicon-plus"></span>
                        </a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>