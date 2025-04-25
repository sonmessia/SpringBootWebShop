<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<form action="/admin/category/add" method="post">
		<h1>${param.message }</h1>
		<div class="mb-3 mt-3">
		    <label for="email" class="form-label">Id:</label>
		    <input type="number" class="form-control" id="email" placeholder="" name="id">
		</div>
		<div class="mb-3 mt-3">
		    <label for="name" class="form-label">Name:</label>
		    <input name="name" type="text" id="name" class="form-control" required/>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
		<button formaction="/admin/category/edit" class="btn btn-warning">Update</button>
</form>
<table class="table">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">name</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${categories }" var="a">
  		<tr>
	      <td>${a.id }</td>
	      <td>${a.name }</td>
	      <td><a href="/admin/category/delete/${a.id}">delete</a></td>
	    </tr>
  	</c:forEach>
  </tbody>
</table>
</html>