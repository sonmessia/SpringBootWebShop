<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<form action="/admin/user/add" method="post">
		<h1>${param.message }</h1>
		<label>User name: <input name="username" type="text" required/></label>
		<label>Password: <input name="password" type="password" required/></label>
		<label>Full name: <input name="fullname" type="text" required/></label>
		<label>Email: <input name="email" type="email" required/></label>
		<label>Admin: <input name="admin" type="radio" value="true"/></label>
		<button>Add</button>
		<button formaction="/admin/user/edit">Update</button>
</form>
<table class="table">
  <thead>
    <tr>
      <th scope="col">User name</th>
      <th scope="col">password</th>
      <th scope="col">fullname</th>
      <th scope="col">email</th>
      <th scope="col">admin</th>
      <th scope="col">activated</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${users }" var="a">
  		<tr>
	      <td>${a.username }</td>
	      <td>${a.password }</td>
	      <td>${a.fullname }</td>
	      <td>${a.email }</td>
	      <td>${a.admin }</td>
	      <td>${a.activated }</td>
	      <td><a href="/admin/user/delete/${a.username}">delete</a></td>
	    </tr>
  	</c:forEach>
  </tbody>
</table>
</html>